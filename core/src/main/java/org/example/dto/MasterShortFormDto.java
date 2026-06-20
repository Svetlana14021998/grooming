package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MasterShortFormDto {

    private Long id;

    private String fullName;
}
