package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.example.model.AppUser;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
        @RequestPart("data") UserDto userDto,
        @RequestPart(value = "photo", required = false) MultipartFile photo,
        Authentication authentication) {

        AppUser user = (AppUser) authentication.getPrincipal();
        userService.updateUserFields(user, userDto, photo);
        return ResponseEntity.ok().body("{\"success\": true}");
    }

    @PutMapping("/profile/password")
    public ResponseEntity<?> changePassword(
        @RequestBody Map<String, String> passwordData,
        Authentication authentication) {

        AppUser user = (AppUser) authentication.getPrincipal();
        userService.updateUserPassword(user, passwordData);
        return ResponseEntity.ok().body("Пароль успешно изменен");
    }

    @PostMapping("/profile/password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        userService.resetPassword(username);
        return ResponseEntity.ok().body("Письмо для изменения пароля направлено на Вашу рабочую почту");
    }
}
