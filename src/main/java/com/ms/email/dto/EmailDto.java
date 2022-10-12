package com.ms.email.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
public class EmailDto {

    @NotBlank
    private String ownerRef;
    @NotBlank

    private String emailFrom;
    @NotBlank

    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
}
