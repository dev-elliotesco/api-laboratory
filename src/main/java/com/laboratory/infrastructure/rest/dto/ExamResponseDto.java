package com.laboratory.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class ExamResponseDto {
    private Integer id;
    private String name;
    private String description;
}
