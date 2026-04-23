package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.config.AppConfig;
import org.example.dto.ClientMessageDto;
import org.example.entity.EmailRequest;
import org.example.enums.EmailTemplate;
import org.example.service.ClientMessageService;
import org.example.service.EmailService;
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

    private final ClientMessageService clientMessageService;

    private final EmailService emailService;

    private final MessageSource messageSource;

    private final AppConfig config;

    @GetMapping("/contacts")
    public String getContacts(Model model) {
        model.addAttribute("clientMessageDto", new ClientMessageDto());
        return "contacts";
    }

    @PostMapping("/contacts/send")
    public String sendMsgToSupport(@Valid @ModelAttribute ClientMessageDto msg,
        BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("scrollToForm", true);
            return "contacts";
        }
        try {
            EmailRequest adminEmail = EmailRequest.builder()
                .to(config.getAdminEmail())
                .subject("Новое сообщение с сайта от пользователя")
                .template(EmailTemplate.CONTACT_FORM)
                .data(msg)
                .build();
            emailService.sendEmail(adminEmail);
            msg.setEmailSent(true);
            clientMessageService.save(msg);
            String message = messageSource.getMessage("send_success", null, LocaleContextHolder.getLocale());
            redirectAttributes.addFlashAttribute("successMessage", message);
        } catch (RuntimeException e) {
            msg.setEmailError("Can`t send msg");
            clientMessageService.save(msg);
            String message = messageSource.getMessage("send_error", null, LocaleContextHolder.getLocale());
            redirectAttributes.addFlashAttribute("errorMessage", message);
        }
        return "redirect:/contacts";
    }
}
