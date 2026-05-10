package org.example.entity;

import lombok.Builder;
import lombok.Data;
import org.example.enums.EmailTemplate;

@Data
@Builder
public class EmailRequest {

    private String to;

    private String subject;

    private EmailTemplate template;

    private Object data;
}
