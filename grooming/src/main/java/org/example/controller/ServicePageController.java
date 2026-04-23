package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.AnimalTypeDto;
import org.example.dto.GroomingServiceDto;
import org.example.model.enums.GroomingServiceCategory;
import org.example.service.AnimalTypeService;
import org.example.service.GroomingServiceService;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Locale;

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
        Locale currentLocale = LocaleContextHolder.getLocale();

        List<GroomingServiceDto> services = groomingServiceService.findAll(animalTypeId, category, priceFrom, priceTo);
        List<AnimalTypeDto> animalTypes = animalTypeService.findAll();

        List<String> allCategories = GroomingServiceCategory.geAllForLocale(currentLocale);
        model.addAttribute("services", services);
        model.addAttribute("animalTypes", animalTypes);
        model.addAttribute("allCategories", allCategories);
        return "services";
    }
}
