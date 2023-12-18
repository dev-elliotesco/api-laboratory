package com.laboratory.infrastructure.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AppointmentResponseDto {
    private Integer id;
    private LocalDate date;
    private LocalTime hour;
    private AffiliateResponseDto affiliate;
    private ExamResponseDto exam;
}
