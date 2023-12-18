package com.laboratory.infrastructure.adapters.mapper;

import com.laboratory.domain.model.Affiliate;
import com.laboratory.domain.model.Appointment;
import com.laboratory.domain.model.Exam;
import com.laboratory.infrastructure.adapters.entity.AffiliateEntity;
import com.laboratory.infrastructure.adapters.entity.AppointmentEntity;
import com.laboratory.infrastructure.adapters.entity.ExamEntity;
import com.laboratory.infrastructure.rest.dto.AppointmentRequestDto;
import com.laboratory.infrastructure.rest.dto.AppointmentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentMapperTest {

    private AppointmentMapper appointmentMapper;

    @BeforeEach
    public void setUp() {
        AffiliateMapper affiliateMapper = new AffiliateMapper();
        ExamMapper examMapper = new ExamMapper();
        appointmentMapper = new AppointmentMapper(affiliateMapper, examMapper);
    }

    @Test
    void testToEntityFromModel() {
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .build();
        Exam exam = Exam.builder()
                .id(1)
                .build();
        Appointment appointment = Appointment.builder()
                .id(1)
                .affiliate(affiliate)
                .exam(exam)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();

        var appointmentEntity = appointmentMapper.toEntityFromModel(appointment);

        assertEquals(appointment.getId(), appointmentEntity.getId());
        assertEquals(appointment.getDate(), appointmentEntity.getDate());
        assertEquals(appointment.getHour(), appointmentEntity.getHour());
        assertEquals(appointment.getAffiliate().getId(), appointmentEntity.getAffiliate().getId());
        assertEquals(appointment.getExam().getId(), appointmentEntity.getExam().getId());
    }

    @Test
    void testToModelFromEntity() {
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .affiliate(AffiliateEntity.builder().id(1).build())
                .exam(ExamEntity.builder().id(1).build())
                .build();

        Appointment appointment = appointmentMapper.toModelFromEntity(appointmentEntity);

        assertEquals(appointmentEntity.getId(), appointment.getId());
        assertEquals(appointmentEntity.getDate(), appointment.getDate());
        assertEquals(appointmentEntity.getHour(), appointment.getHour());
        assertEquals(appointmentEntity.getAffiliate().getId(), appointment.getAffiliate().getId());
        assertEquals(appointmentEntity.getExam().getId(), appointment.getExam().getId());
    }

    @Test
    void testToModelFromRequestDto() {
        AppointmentRequestDto appointmentRequestDto = AppointmentRequestDto.builder()
                .affiliateId(1)
                .examId(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();
        Exam exam = Exam.builder()
                .id(1)
                .build();
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .build();

        Appointment appointment = appointmentMapper.toModelFromRequestDto(appointmentRequestDto, exam, affiliate);

        assertEquals(appointmentRequestDto.getDate(), appointment.getDate());
        assertEquals(appointmentRequestDto.getHour(), appointment.getHour());
        assertEquals(appointmentRequestDto.getAffiliateId(), appointment.getAffiliate().getId());
        assertEquals(appointmentRequestDto.getExamId(), appointment.getExam().getId());
    }

    @Test
    void testToResponseDtoFromModel() {
        Affiliate affiliate = Affiliate.builder()
                .id(1).build();
        Exam exam = Exam.builder()
                .id(1).build();
        Appointment appointment = Appointment.builder()
                .id(1)
                .affiliate(affiliate)
                .exam(exam)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();

        AppointmentResponseDto appointmentResponseDto = appointmentMapper.toResponseDtoFromModel(appointment);

        assertEquals(appointment.getId(), appointmentResponseDto.getId());
        assertEquals(appointment.getDate(), appointmentResponseDto.getDate());
        assertEquals(appointment.getHour(), appointmentResponseDto.getHour());
        assertEquals(appointment.getAffiliate().getId(), appointmentResponseDto.getAffiliate().getId());
        assertEquals(appointment.getExam().getId(), appointmentResponseDto.getExam().getId());
    }
}
