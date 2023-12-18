package com.laboratory.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentTest {

    @Test
    void testToString() {
        Affiliate affiliate = Affiliate.builder().id(1).build();
        Exam exam = Exam.builder().id(1).build();
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();

        Appointment appointment = Appointment.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(affiliate)
                .exam(exam)
                .build();

        String expected = "Appointment(id=1, date=" + date + ", hour=" + hour + ", " +
                "exam=" + exam + ", affiliate=" + affiliate + ")";
        assertEquals(expected, appointment.toString());
    }

    @Test
    void testHashCode() {
        Affiliate affiliate = Affiliate.builder().id(1).build();
        Exam exam = Exam.builder().id(1).build();
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();

        Appointment appointment1 = Appointment.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(affiliate)
                .exam(exam)
                .build();

        Appointment appointment2 = Appointment.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(affiliate)
                .exam(exam)
                .build();

        assertEquals(appointment1.hashCode(), appointment2.hashCode());
    }

    @Test
    void testEquals() {
        Affiliate affiliate = Affiliate.builder().id(1).build();
        Exam exam = Exam.builder().id(1).build();
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();

        Appointment appointment1 = Appointment.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(affiliate)
                .exam(exam)
                .build();

        Appointment appointment2 = Appointment.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(affiliate)
                .exam(exam)
                .build();

        assertEquals(appointment1, appointment2);
    }
}
