package org.example.service;

import org.example.dto.UserDto;
import org.example.dto.UserShortDto;
import org.example.enums.UserRole;
import org.example.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserShortDto> getUsersWithBirthdayInThisMonth();

    Page<UserDto> getAllUsers(String searchTerm, UserRole role, Integer pageNumber, Integer pageSize, boolean isBirthday);

    UserDto findUserById(Long id);

    void updateUserFields(AppUser user,UserDto dto, MultipartFile photo);

    void updateUserPassword(AppUser user, Map<String,String> passwordData);

    void resetPassword(String username);
}
