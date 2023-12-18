package com.laboratory.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AffiliateRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Lastname is mandatory")
    private String lastname;
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotBlank(message = "Phone is mandatory")
    @Size(min = 10, max = 15, message = "Phone should be between 10 and 15 characters")
    private String phone;
    @NotNull(message = "Age is mandatory")
    private Integer age;
}
