package com.laboratory.infrastructure.adapters.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentEntityTest {

    @Test
    void testSetMethods() {
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(AffiliateEntity.builder().id(1).build())
                .exam(ExamEntity.builder().id(1).build()).build();

        appointmentEntity.setId(2);
        appointmentEntity.setDate(date);
        appointmentEntity.setHour(hour);
        appointmentEntity.setAffiliate(AffiliateEntity.builder().id(2).build());
        appointmentEntity.setExam(ExamEntity.builder().id(2).build());

        assertEquals(2, appointmentEntity.getId());
        assertEquals(date, appointmentEntity.getDate());
        assertEquals(hour, appointmentEntity.getHour());
        assertEquals(AffiliateEntity.builder().id(2).build(), appointmentEntity.getAffiliate());
        assertEquals(ExamEntity.builder().id(2).build(), appointmentEntity.getExam());
    }

    @Test
    void testToString() {
        AffiliateEntity affiliateEntity = AffiliateEntity.builder().id(1).build();
        ExamEntity examEntity = ExamEntity.builder().id(1).build();
        LocalDate date = LocalDate.of(2023, 12, 18);
        LocalTime hour = LocalTime.of(12,0,0);

        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(affiliateEntity)
                .exam(examEntity)
                .build();

        String expected = "AppointmentEntity(id=1, date=2023-12-18, hour=12:00, " +
                "exam=ExamEntity(id=1, name=null, description=null, appointments=[]), " +
                "affiliate=AffiliateEntity(id=1, name=null, lastname=null, email=null, " +
                "phone=null, age=null, appointments=[]))";

        assertEquals(expected, appointmentEntity.toString());
    }

    @Test
    void testHashCode() {
        AffiliateEntity affiliateEntity = AffiliateEntity.builder().id(1).build();
        ExamEntity examEntity = ExamEntity.builder().id(1).build();
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();

        AppointmentEntity appointmentEntity1 = AppointmentEntity.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(affiliateEntity)
                .exam(examEntity)
                .build();

        AppointmentEntity appointmentEntity2 = AppointmentEntity.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(affiliateEntity)
                .exam(examEntity)
                .build();

        assertEquals(appointmentEntity1.hashCode(), appointmentEntity2.hashCode());
    }

    @Test
    void testEquals() {
        AffiliateEntity affiliateEntity = AffiliateEntity.builder().id(1).build();
        ExamEntity examEntity = ExamEntity.builder().id(1).build();
        LocalDate date = LocalDate.now();
        LocalTime hour = LocalTime.now();

        AppointmentEntity appointmentEntity1 = AppointmentEntity.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(affiliateEntity)
                .exam(examEntity)
                .build();

        AppointmentEntity appointmentEntity2 = AppointmentEntity.builder()
                .id(1)
                .date(date)
                .hour(hour)
                .affiliate(affiliateEntity)
                .exam(examEntity)
                .build();

        assertEquals(appointmentEntity1, appointmentEntity2);
    }
}
