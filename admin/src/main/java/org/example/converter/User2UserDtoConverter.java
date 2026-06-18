package org.example.converter;

import org.example.dto.UserDto;
import org.example.enums.UserRole;
import org.example.model.AppUser;
import org.example.model.GroomingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class User2UserDtoConverter {

    @Value("${app.employee-photo-path}")
    private String basePhotoPath;

    public UserDto convert(AppUser user) {
        UserDto.UserDtoBuilder builder = UserDto.builder()
            .fullName(user.getFullName())
            .birthday(user.getBirthday())
            .pathToPhoto(basePhotoPath + "/" + user.getPhotoName())
            .phone(user.getPhone())
            .email(user.getEmail())
            .role(user.getRole().getRoleName())
            .roleCode(user.getRole().getCode())
            .aboutMe(user.getAboutMe())
            .telegram(user.getTelegram())
            .achievements(user.getAchievements())
            .vk(user.getVk())
            .hobby(user.getHobby())
            .education(user.getEducation());
        if (UserRole.MASTER.equals(user.getRole())) {
            List<String> specialisations = user.getMaster().getServices().stream()
                .map(GroomingService::getNameRu)
                .toList();
            builder.specialisations(specialisations);
        }
        return builder.build();
    }

    public AppUser convert(AppUser user, UserDto userDto) {
        String[] partName = userDto.getFullName().split("\\s+");

        user.setLastName(partName[0]);
        user.setFirstName(partName[1]);
        user.setBirthday(userDto.getBirthday());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setAboutMe(userDto.getAboutMe());
        user.setTelegram(userDto.getTelegram());
        user.setAchievements(userDto.getAchievements());
        user.setVk(userDto.getVk());
        user.setHobby(userDto.getHobby());
        user.setEducation(userDto.getEducation());
        //todo по идее другие поля неизменяемые, но при создании пользователя, скорее всего, понадобится добавить их в билдер (роль и специализация)
        return user;
    }
}
