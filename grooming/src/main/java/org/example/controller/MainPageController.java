package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.GroomingServiceDto;
import org.example.service.GroomingServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final GroomingServiceService groomingServiceService;

    @GetMapping("/main")
    public String getMainPage(Model model) {
        List<GroomingServiceDto> services = groomingServiceService.findPopular();
        model.addAttribute("popularServices", services);
        return "mainPage";
    }
}
