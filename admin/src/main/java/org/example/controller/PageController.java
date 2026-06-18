package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.enums.UserRole;
import org.example.model.AppUser;
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

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String getDashboardPage(Model model) {
        model.addAttribute("users", userService.getUsersWithBirthdayInThisMonth());
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
}
