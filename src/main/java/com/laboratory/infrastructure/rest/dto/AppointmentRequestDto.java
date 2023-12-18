package com.laboratory.infrastructure.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AppointmentRequestDto {
    @NotNull(message = "Date is mandatory")
    private LocalDate date;
    @NotNull(message = "Hour is mandatory")
    private LocalTime hour;
    @NotNull(message = "Affiliate ID is mandatory")
    private Integer affiliateId;
    @NotNull(message = "Exam ID is mandatory")
    private Integer examId;
}
