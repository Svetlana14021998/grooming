package org.example.validator;

import org.example.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProfileValidator {

    public static final String REGEX = "^\\+7 \\(\\d{3}\\) \\d{3}-\\d{2}-\\d{2}$";

    public Map<String, String> validate(UserDto userDto) {
        Map<String, String> errors = new HashMap<>();
        if (!userDto.getPhone().matches(REGEX)) {
            errors.put("phone", "Номер телефона должен соответствовать шаблону +7 (xxx) xxx-xx-xx");
        }

        boolean isEducationNullOrEmpty = userDto.getEducation() == null || userDto.getEducation().isEmpty();
        if (isEducationNullOrEmpty) {
            errors.put("education", "Необходимо указать образование");
        }

        boolean isAchievementsNullOrEmpty = userDto.getAchievements() == null || userDto.getAchievements().isEmpty();
        if (isAchievementsNullOrEmpty) {
            errors.put("achievement", "Необходимо указать достижения");
        }

        boolean isAboutMeNullOrEmpty = userDto.getAboutMe() == null || userDto.getAboutMe().isEmpty();
        if (isAboutMeNullOrEmpty) {
            errors.put("aboutMe", "Необходимо указать краткую информацию о себе");
        }

        boolean isHobbyNullOrEmpty = userDto.getHobby() == null || userDto.getHobby().isEmpty();
        if (isHobbyNullOrEmpty) {
            errors.put("hobby", "Необходимо указать хобби");
        }

        boolean isVkNullOrEmpty = userDto.getVk() == null || userDto.getVk().isEmpty();
        boolean isTelegramNullOrEmpty = userDto.getTelegram() == null || userDto.getTelegram().isEmpty();
        if (isVkNullOrEmpty && isTelegramNullOrEmpty) {
            errors.put("social network", "Необходимо хотя бы одну соц сеть");
        }
        return errors;
    }
}
