package com.laboratory.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class Appointment {
        private Integer id;
        private LocalDate date;
        private LocalTime hour;
        private Exam exam;
        private Affiliate affiliate;
}
