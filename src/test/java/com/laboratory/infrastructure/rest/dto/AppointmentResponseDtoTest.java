package com.laboratory.infrastructure.rest.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentResponseDtoTest {

    @Test
    void testSetMethods() {
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();
        AppointmentResponseDto appointmentResponseDto = AppointmentResponseDto.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(AffiliateResponseDto.builder().id(1).build())
                .exam(ExamResponseDto.builder().id(1).build()).build();

        appointmentResponseDto.setId(2);
        appointmentResponseDto.setDate(date);
        appointmentResponseDto.setHour(hour);
        appointmentResponseDto.setAffiliate(AffiliateResponseDto.builder().id(2).build());
        appointmentResponseDto.setExam(ExamResponseDto.builder().id(2).build());

        assertEquals(2, appointmentResponseDto.getId());
        assertEquals(date, appointmentResponseDto.getDate());
        assertEquals(hour, appointmentResponseDto.getHour());
        assertEquals(AffiliateResponseDto.builder().id(2).build(), appointmentResponseDto.getAffiliate());
        assertEquals(ExamResponseDto.builder().id(2).build(), appointmentResponseDto.getExam());
    }

    @Test
    void testToString() {
        LocalDate date = LocalDate.of(2023, 12, 18);
        LocalTime hour = LocalTime.of(12,0,0);
        AppointmentResponseDto appointmentResponseDto = AppointmentResponseDto.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(AffiliateResponseDto.builder().id(1).build())
                .exam(ExamResponseDto.builder().id(1).build()).build();

        String expected = "AppointmentResponseDto(id=1, date=2023-12-18, hour=12:00, " +
                "affiliate=AffiliateResponseDto(id=1, name=null, lastname=null, email=null, " +
                "phone=null, age=null), exam=ExamResponseDto(id=1, name=null, description=null))";

        assertEquals(expected, appointmentResponseDto.toString());
    }

    @Test
    void testHashCode() {
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();
        AppointmentResponseDto appointmentResponseDto1 = AppointmentResponseDto.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(AffiliateResponseDto.builder().id(1).build())
                .exam(ExamResponseDto.builder().id(1).build()).build();

        AppointmentResponseDto appointmentResponseDto2 = AppointmentResponseDto.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(AffiliateResponseDto.builder().id(1).build())
                .exam(ExamResponseDto.builder().id(1).build()).build();

        assertEquals(appointmentResponseDto1.hashCode(), appointmentResponseDto2.hashCode());
    }

    @Test
    void testEquals() {
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();
        AppointmentResponseDto appointmentResponseDto1 = AppointmentResponseDto.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(AffiliateResponseDto.builder().id(1).build())
                .exam(ExamResponseDto.builder().id(1).build()).build();

        AppointmentResponseDto appointmentResponseDto2 = AppointmentResponseDto.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(AffiliateResponseDto.builder().id(1).build())
                .exam(ExamResponseDto.builder().id(1).build()).build();

        assertEquals(appointmentResponseDto1, appointmentResponseDto2);
    }
}
