package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.config.AppConfig;
import org.example.converter.User2ShortUserDtoConverter;
import org.example.converter.User2UserDtoConverter;
import org.example.dto.UserDto;
import org.example.dto.UserShortDto;
import org.example.enums.EmailTemplate;
import org.example.enums.UserRole;
import org.example.exception.EntityNotFoundException;
import org.example.exception.IncorrectPasswordException;
import org.example.exception.IncorrectRequestParamException;
import org.example.exception.ValidationException;
import org.example.helper.PasswordGenerator;
import org.example.model.AppUser;
import org.example.repository.UserRepository;
import org.example.repository.spec.UserSpecification;
import org.example.service.ImageService;
import org.example.service.UserService;
import org.example.validator.PasswordValidator;
import org.example.validator.ProfileValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final User2ShortUserDtoConverter user2ShortUserDtoConverter;

    private final User2UserDtoConverter user2UserDtoConverter;

    private final PasswordEncoder encoder;

    private final PasswordValidator validator;

    private final ContactServiceImpl contactService;

    private final AppConfig appConfig;

    private final ProfileValidator profileValidator;

    private final ImageService imageService;

    @Override
    public List<UserShortDto> getUsersWithBirthdayInThisMonth() {
        return userRepository.findUsersWithBirthdayThisMonth().stream()
            .map(user2ShortUserDtoConverter::convert)
            .toList();
    }

    @Override
    public Page<UserDto> getAllUsers(String searchTerm, UserRole role, Integer pageNumber, Integer pageSize, boolean isBirthday) {
        Specification<AppUser> spec = Specification.allOf();
        if (searchTerm != null && !searchTerm.isBlank()) {
            spec = spec.and(UserSpecification.searchByFullName(searchTerm));
        }
        if (role != null) {
            spec = spec.and(UserSpecification.searchByRole(role));
        }

        if (isBirthday) {
            spec = spec.and(UserSpecification.birthdayInCurrentMonth());
        }

        Sort sort = Sort.by("lastName");
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return userRepository.findAll(spec, pageable)
            .map(user2UserDtoConverter::convert);
    }

    @Override
    public UserDto findUserById(Long id) {
        AppUser user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Not found user with id=%d".formatted(id)));
        return user2UserDtoConverter.convert(user);
    }

    @Override
    public void updateUserFields(AppUser user, UserDto dto, MultipartFile photo) {
        Map<String, String> validationResult = profileValidator.validate(dto);
        if (!validationResult.isEmpty()) {
            throw new ValidationException("Некорректные данные при изменении профиля: "
                + String.join(",", validationResult.values()), validationResult);
        }
        user = user2UserDtoConverter.convert(user, dto);
        userRepository.save(user);
        if (photo != null && !photo.isEmpty()) {
            imageService.savePhoto(photo, user.getPhotoName(), "admin", "employees");
        }
    }

    @Override
    public void updateUserPassword(AppUser user, Map<String, String> passwordData) {

        String currentPassword = passwordData.get("currentPassword");
        String newPassword = passwordData.get("newPassword");
        String confirmPassword = passwordData.get("confirmPassword");

        boolean isCurrentPasswordNullOrEmpty = currentPassword == null || currentPassword.isEmpty();
        boolean isNewPasswordNullOrEmpty = newPassword == null || newPassword.isEmpty();
        boolean isConfirmPasswordNullOrEmpty = confirmPassword == null || confirmPassword.isEmpty();
        if (isCurrentPasswordNullOrEmpty || isNewPasswordNullOrEmpty || isConfirmPasswordNullOrEmpty) {
            throw new IncorrectRequestParamException("Не заполнен старый и/или новый пароль");
        }

        if (!newPassword.equals(confirmPassword)) {
            throw new IncorrectPasswordException("Некорректное подтверждение нового пароля");
        }

        AppUser appUser = userRepository.findById(user.getId())
            .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        if (!encoder.matches(currentPassword, appUser.getPassword())) {
            throw new IncorrectPasswordException("Введен некорректный пароль");
        }
        if (currentPassword.equals(newPassword)) {
            throw new IncorrectPasswordException("Новый пароль не должен совпадать со старым");
        }
        String validationResult = validator.validate(newPassword);
        if (validationResult != null) {
            throw new IncorrectPasswordException(validationResult);
        }
        user.setPassword(encoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void resetPassword(String username) {
        AppUser appUser = userRepository.findByUsername(username).orElseThrow(
            () -> new EntityNotFoundException("Пользовать не найден"));
        String newPassword = PasswordGenerator.generate();
        appUser.setPassword(encoder.encode(newPassword));
        userRepository.save(appUser);

        Map<String, Object> data = Map.of(
            "password", newPassword,
            "username", appUser.getFirstName(),
            "loginUrl", "http://localhost:8082/adminapp/login",
            "phone", appConfig.getPhone(),
            "email", appConfig.getEmail());
        contactService.sendEmailToEmployee(appUser.getEmail(), data, EmailTemplate.RESET_PASSWORD);
    }
}
