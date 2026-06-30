package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.enums.ArticleType;
import org.example.enums.UserRole;
import org.example.model.AppUser;
import org.example.service.UsefulArticleService;
import org.example.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final UserService userService;

    private final UsefulArticleService articleService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String getDashboardPage(Authentication authentication, Model model) {
        AppUser currentUser = (AppUser) authentication.getPrincipal();
        Long masterId = currentUser.getMaster().getId();
        long countOfArticles = articleService.countArticlesForMaster(masterId);
        model.addAttribute("users", userService.getUsersWithBirthdayInThisMonth());
        model.addAttribute("articlesCount", countOfArticles);
        return "dashboard";
    }

    @GetMapping("/profile")
    public String getProfilePge(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof AppUser) {
            AppUser user = (AppUser) authentication.getPrincipal();
            UserDto userDto = userService.findUserById(user.getId());
            model.addAttribute("user", userDto);
        } else {
            return "redirect:/login";
        }
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String getProfileEditPge(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof AppUser) {
            AppUser user = (AppUser) authentication.getPrincipal();
            UserDto userDto = userService.findUserById(user.getId());
            model.addAttribute("user", userDto);
        } else {
            return "redirect:/login";
        }
        return "profile-edit";
    }

    @GetMapping("/profile/reset-password")
    public String getProfileEditPge() {
        return "reset-password";
    }

    @GetMapping("/profile/change-password")
    public String getChangePasswordPge() {
        return "password-change";
    }

    @GetMapping("/employee-info")
    public String getEmployeeInfoPge(@RequestParam(required = false) String searchTerm,
        @RequestParam(required = false) UserRole role,
        @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
        @RequestParam(required = false, defaultValue = "5", name = "size") Integer pageSize,
        @RequestParam(required = false, defaultValue = "false") boolean isBirthday,
        Model model) {
        model.addAttribute("employees", userService.getAllUsers(searchTerm, role, pageNumber, pageSize, isBirthday));
        return "employee-info";
    }

    @GetMapping("/article/create")
    public String getArticleCreatePage(Model model) {
        model.addAttribute("articleType", ArticleType.values());
        return "article-create";
    }

    @GetMapping("/article")
    public String getArticlesPage(Model model) {
        //todo функционал в разработке
        return "in-work";
        // return "article";
    }

    @GetMapping("/tasks")
    public String getTaskPage(Model model) {
        //todo функционал в разработке
        return "in-work";
        // return "tasks";
    }

    @GetMapping("/schedule")
    public String getSchedulePage(Model model) {
        //todo функционал в разработке
        return "in-work";
        // return "schedule";
    }
}
