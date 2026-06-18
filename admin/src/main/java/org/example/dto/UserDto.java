package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserDto {

    private String fullName;
    private LocalDate birthday;
    private String pathToPhoto;
    private String email;
    private String phone;
    private String role;
    private String roleCode;
    private List<String> specialisations;
    private String aboutMe;
    private String achievements;
    private String telegram;
    private String vk;
    private String hobby;
    private String education;
}
