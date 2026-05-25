package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AnimalTypeDto;
import org.example.dto.GroomingServiceDto;
import org.example.enums.GroomingServiceCategory;
import org.example.service.AnimalTypeService;
import org.example.service.GroomingServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ServicePageController {

    private final GroomingServiceService groomingServiceService;

    private final AnimalTypeService animalTypeService;

    @GetMapping("/services")
    public String getServicesPage(@RequestParam(required = false) Long animalTypeId,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) Integer priceFrom,
        @RequestParam(required = false) Integer priceTo,
        Model model) {

        List<GroomingServiceDto> services = groomingServiceService.findAll(animalTypeId, category, priceFrom, priceTo);
        List<AnimalTypeDto> animalTypes = animalTypeService.findAll();

        List<String> allCategories = GroomingServiceCategory.geAllForLocale();
        model.addAttribute("services", services);
        model.addAttribute("animalTypes", animalTypes);
        model.addAttribute("allCategories", allCategories);
        return "services";
    }
}
