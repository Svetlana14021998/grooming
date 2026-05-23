package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.MasterDto;
import org.example.service.MasterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MastersPageController {

    private final MasterService masterService;

    @GetMapping("/masters")
    public String getMastersPage(Model model) {
        List<MasterDto> masters = masterService.findAll();
        model.addAttribute("masters", masters);
        return "masters";
    }
}
