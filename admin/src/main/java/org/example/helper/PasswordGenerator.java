package org.example.helper;

import lombok.experimental.UtilityClass;
import org.example.validator.PasswordValidator;

import java.security.SecureRandom;

@UtilityClass
public class PasswordGenerator {

    private static final String DIGITS = "0123456789";
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String SPECIALS = "!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?";
    private static final String ALL = DIGITS + LETTERS + SPECIALS;

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate() {
        int length = 10;

        String password;
        do {
            password = generateRaw(length);
        } while (!password.matches(PasswordValidator.REGEX));

        return password;
    }

    private static String generateRaw(int length) {
        StringBuilder sb = new StringBuilder();

        sb.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        sb.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        sb.append(SPECIALS.charAt(RANDOM.nextInt(SPECIALS.length())));

        for (int i = 3; i < length; i++) {
            sb.append(ALL.charAt(RANDOM.nextInt(ALL.length())));
        }

        char[] chars = sb.toString().toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = RANDOM.nextInt(chars.length);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return new String(chars);
    }
}
