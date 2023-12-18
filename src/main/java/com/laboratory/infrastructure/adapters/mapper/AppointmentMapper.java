package com.laboratory.infrastructure.adapters.mapper;

import com.laboratory.domain.model.Affiliate;
import com.laboratory.domain.model.Appointment;
import com.laboratory.domain.model.Exam;
import com.laboratory.infrastructure.adapters.entity.AppointmentEntity;
import com.laboratory.infrastructure.rest.dto.AppointmentRequestDto;
import com.laboratory.infrastructure.rest.dto.AppointmentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppointmentMapper {

    private final AffiliateMapper affiliateMapper;
    private final ExamMapper examMapper;
    public AppointmentEntity toEntityFromModel(Appointment appointment) {
        return AppointmentEntity.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .hour(appointment.getHour())
                .affiliate(affiliateMapper.toEntityFromModel(appointment.getAffiliate()))
                .exam(examMapper.toEntityFromModel(appointment.getExam()))
                .build();
    }
    public Appointment toModelFromEntity(AppointmentEntity appointmentEntity) {
        return Appointment.builder()
                .id(appointmentEntity.getId())
                .date(appointmentEntity.getDate())
                .hour(appointmentEntity.getHour())
                .affiliate(affiliateMapper.toModelFromEntity(appointmentEntity.getAffiliate()))
                .exam(examMapper.toModelFromEntity(appointmentEntity.getExam()))
                .build();
    }
    public Appointment toModelFromRequestDto(AppointmentRequestDto appointmentRequestDto, Exam exam, Affiliate affiliate) {
        return Appointment.builder()
                .date(appointmentRequestDto.getDate())
                .hour(appointmentRequestDto.getHour())
                .affiliate(affiliate)
                .exam(exam)
                .build();
    }
    public AppointmentResponseDto toResponseDtoFromModel(Appointment appointment) {
        return AppointmentResponseDto.builder()
                .id(appointment.getId())
                .date(appointment.getDate())
                .hour(appointment.getHour())
                .affiliate(affiliateMapper.toResponseDtoFromModel(appointment.getAffiliate()))
                .exam(examMapper.toResponseDtoFromModel(appointment.getExam()))
                .build();
    }
}