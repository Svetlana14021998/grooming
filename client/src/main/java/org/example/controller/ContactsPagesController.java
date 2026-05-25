package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.ClientMessageDto;
import org.example.service.ClientMessageService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ContactsPagesController {

    private final MessageSource messageSource;

    private final ClientMessageService clientMessageService;

    @GetMapping("/contacts")
    public String getContacts(Model model) {
        model.addAttribute("clientMessageDto", new ClientMessageDto());
        return "contacts";
    }

    @PostMapping("/contacts/send")
    public String saveMsgFromUser(@Valid @ModelAttribute ClientMessageDto msg,
        BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("scrollToForm", true);
            return "contacts";
        }
        clientMessageService.save(msg);
        String message = messageSource.getMessage("send_success", null, LocaleContextHolder.getLocale());
        redirectAttributes.addFlashAttribute("successMessage", message);
        return "redirect:/contacts";
    }
}
