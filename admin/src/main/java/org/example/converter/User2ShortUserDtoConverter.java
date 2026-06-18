package org.example.converter;

import org.example.dto.UserShortDto;
import org.example.model.AppUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class User2ShortUserDtoConverter {
    @Value("${app.employee-photo-path}")
    private String basePhotoPath;

    public UserShortDto convert(AppUser user) {
        return UserShortDto.builder()
            .fullName(user.getFullName())
            .birthday(user.getBirthday())
            .pathToPhoto(basePhotoPath +"/"+ user.getPhotoName())
            .build();
    }
}
