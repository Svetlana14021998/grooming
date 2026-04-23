package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientMessageDto {

    @NotBlank(message = "{name_not_empty}")
    private String name;

    @NotBlank(message = "{email_not_empty}")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "{incorrect_email}")
    private String email;

    @NotBlank(message = "{phone_not_empty}")
    @Pattern(regexp = "^(?=.*\\d)[\\d+\\-()]+$", message = "{incorrect_phone}")
    private String phone;

    @NotBlank(message = "{message_not_empty}")
    private String message;

    private boolean emailSent = false;

    private String emailError;
}
