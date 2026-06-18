package org.example.validator;

import org.springframework.stereotype.Component;

@Component
public class PasswordValidator {

    public static final String REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{10,}$";

    public String validate(String password) {
        if (password == null || password.isEmpty()) {
            return "Пароль не может быть пустым";
        }

        if (!password.matches(REGEX)) {
            return "Пароль должен содержать минимум 10 символов, включая хотя бы одну цифру, одну букву и один специальный символ";
        }

        return null;
    }
}
