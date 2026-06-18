package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserShortDto {

    private String fullName;

    private LocalDate birthday;

    private String pathToPhoto;

}
