package com.laboratory.application.usecase;


import com.laboratory.domain.model.Affiliate;
import com.laboratory.domain.model.Appointment;
import com.laboratory.domain.model.Exam;
import com.laboratory.domain.port.AffiliateRepository;
import com.laboratory.domain.port.AppointmentRepository;
import com.laboratory.domain.port.ExamRepository;
import com.laboratory.infrastructure.adapters.mapper.AppointmentMapper;
import com.laboratory.infrastructure.rest.dto.AppointmentRequestDto;
import com.laboratory.infrastructure.rest.dto.AppointmentResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AppointmentUseCase {

    private final ExamRepository examRepository;
    private final AffiliateRepository affiliateRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentResponseDto save(AppointmentRequestDto appointmentRequestDto) {
        Exam exam = examRepository.findById(appointmentRequestDto.getExamId());
        Affiliate affiliate = affiliateRepository.findById(appointmentRequestDto.getAffiliateId());
        Appointment appointment = appointmentMapper.toModelFromRequestDto(appointmentRequestDto, exam,affiliate);
        Appointment appointmentSaved = appointmentRepository.save(appointment);
        return appointmentMapper.toResponseDtoFromModel(appointmentSaved);
    }

    public  AppointmentResponseDto findById(Integer id) {
        Appointment appointment = appointmentRepository.findById(id);
        return appointmentMapper.toResponseDtoFromModel(appointment);
    }

    public List<AppointmentResponseDto> findAll() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentMapper::toResponseDtoFromModel)
                .toList();
    }

    public AppointmentResponseDto update(Integer id, AppointmentRequestDto appointmentRequestDto ) {
        Exam exam = examRepository.findById(appointmentRequestDto.getExamId());
        Affiliate affiliate = affiliateRepository.findById(appointmentRequestDto.getAffiliateId());
        Appointment existingAppointment = appointmentRepository.findById(id);
        existingAppointment.setDate(appointmentRequestDto.getDate());
        existingAppointment.setHour(appointmentRequestDto.getHour());
        existingAppointment.setExam(exam);
        existingAppointment.setAffiliate(affiliate);
        Appointment appointment = appointmentRepository.save(existingAppointment);
        return appointmentMapper.toResponseDtoFromModel(appointment);
    }

    public void delete(Integer id) {

        if(!appointmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Appointment not found with id: " + id);
        }
        appointmentRepository.delete(id);
    }

    public List<AppointmentResponseDto> findByDate(LocalDate date) {
        List<Appointment> appointments = appointmentRepository.findByDate(date);
        return appointments.stream()
                .map(appointmentMapper::toResponseDtoFromModel)
                .toList();
    }
}
