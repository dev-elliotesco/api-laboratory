package com.laboratory.infrastructure.rest.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentRequestDtoTest {

    @Test
    void testSetMethods() {
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();
        AppointmentRequestDto appointmentRequestDto = AppointmentRequestDto.builder()
                .date(date)
                .hour(hour)
                .affiliateId(1)
                .examId(1).build();

        appointmentRequestDto.setDate(date);
        appointmentRequestDto.setHour(hour);
        appointmentRequestDto.setAffiliateId(2);
        appointmentRequestDto.setExamId(2);

        assertEquals(date, appointmentRequestDto.getDate());
        assertEquals(hour, appointmentRequestDto.getHour());
        assertEquals(2, appointmentRequestDto.getAffiliateId());
        assertEquals(2, appointmentRequestDto.getAffiliateId());
    }

    @Test
    void testToString() {
        LocalDate date = LocalDate.of(2023, 12, 18);
        LocalTime hour = LocalTime.of(12,0,0);
        AppointmentRequestDto appointmentRequestDto = AppointmentRequestDto.builder()
                .date(date)
                .hour(hour)
                .affiliateId(1)
                .examId(1).build();

        String expected = "AppointmentRequestDto(date=2023-12-18, hour=12:00, affiliateId=1, examId=1)";

        assertEquals(expected, appointmentRequestDto.toString());
    }

    @Test
    void testHashCode() {
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();
        AppointmentRequestDto appointmentRequestDto1 = AppointmentRequestDto.builder()
                .date(date)
                .hour(hour)
                .affiliateId(1)
                .examId(1).build();

        AppointmentRequestDto appointmentRequestDto2 = AppointmentRequestDto.builder()
                .date(date)
                .hour(hour)
                .affiliateId(1)
                .examId(1).build();

        assertEquals(appointmentRequestDto1.hashCode(), appointmentRequestDto2.hashCode());
    }

    @Test
    void testEquals() {
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();
        AppointmentRequestDto appointmentRequestDto1 = AppointmentRequestDto.builder()
                .date(date)
                .hour(hour)
                .affiliateId(1)
                .examId(1).build();

        AppointmentRequestDto appointmentRequestDto2 = AppointmentRequestDto.builder()
                .date(date)
                .hour(hour)
                .affiliateId(1)
                .examId(1).build();

        assertEquals(appointmentRequestDto1, appointmentRequestDto2);
    }
}
