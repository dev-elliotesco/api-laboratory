package com.laboratory.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AffiliateResponseDto {
    private Integer id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private Integer age;
}
