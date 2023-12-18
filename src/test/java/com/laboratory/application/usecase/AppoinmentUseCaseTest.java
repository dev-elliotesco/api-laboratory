package com.laboratory.application.usecase;

import com.laboratory.domain.model.Affiliate;
import com.laboratory.domain.model.Appointment;
import com.laboratory.domain.model.Exam;
import com.laboratory.domain.port.AffiliateRepository;
import com.laboratory.domain.port.AppointmentRepository;
import com.laboratory.domain.port.ExamRepository;
import com.laboratory.infrastructure.adapters.mapper.AppointmentMapper;
import com.laboratory.infrastructure.rest.dto.AffiliateResponseDto;
import com.laboratory.infrastructure.rest.dto.AppointmentRequestDto;
import com.laboratory.infrastructure.rest.dto.AppointmentResponseDto;
import com.laboratory.infrastructure.rest.dto.ExamResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AppoinmentUseCaseTest {

    @InjectMocks
    private AppointmentUseCase appointmentUseCase;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private AffiliateRepository affiliateRepository;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private AppointmentMapper appointmentMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSave() {
        Affiliate affiliate = Affiliate.builder().id(1).build();

        Exam exam = Exam.builder().id(1).build();

        Appointment appointment = Appointment.builder()
                .id(1)
                .affiliate(affiliate)
                .exam(exam)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();
        AppointmentRequestDto appointmentRequestDto = AppointmentRequestDto.builder()
                .affiliateId(1)
                .examId(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();
        AppointmentResponseDto appointmentResponseDto = AppointmentResponseDto.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .affiliate(affiliateResponseDto)
                .exam(examResponseDto)
                .build();

        when(examRepository.findById(any(Integer.class))).thenReturn(exam);
        when(affiliateRepository.findById(any(Integer.class))).thenReturn(affiliate);
        when(appointmentMapper.toModelFromRequestDto(any(AppointmentRequestDto.class), any(Exam.class), any(Affiliate.class))).thenReturn(appointment);
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);
        when(appointmentMapper.toResponseDtoFromModel(any(Appointment.class))).thenReturn(appointmentResponseDto);

        AppointmentResponseDto result = appointmentUseCase.save(appointmentRequestDto);
        verify(examRepository, times(1)).findById(any(Integer.class));
        verify(affiliateRepository, times(1)).findById(any(Integer.class));
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
        verify(appointmentMapper, times(1)).toResponseDtoFromModel(any(Appointment.class));

        assertEquals(appointmentResponseDto, result);
    }

    @Test
    void testFindById() {
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
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .build();
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .build();
        AppointmentResponseDto appointmentResponseDto = AppointmentResponseDto.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .affiliate(affiliateResponseDto)
                .exam(examResponseDto)
                .build();

        when(appointmentRepository.findById(any(Integer.class))).thenReturn(appointment);
        when(appointmentMapper.toResponseDtoFromModel(any(Appointment.class))).thenReturn(appointmentResponseDto);

        AppointmentResponseDto result = appointmentUseCase.findById(1);

        verify(appointmentRepository, times(1)).findById(1);
        verify(appointmentMapper, times(1)).toResponseDtoFromModel(appointment);

        assertEquals(appointmentResponseDto, result);
    }

    @Test
    void testFindAll() {
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .build();
        Exam exam = Exam.builder()
                .id(1)
                .build();
        List<Appointment> appointmentList = List.of(Appointment.builder()
                .id(1)
                .affiliate(affiliate)
                .exam(exam)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build());
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .build();
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .build();
        List<AppointmentResponseDto> appointmentResponseDtoList = List.of(AppointmentResponseDto.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .affiliate(affiliateResponseDto)
                .exam(examResponseDto)
                .build());

        when(appointmentRepository.findAll()).thenReturn(appointmentList);
        when(appointmentMapper.toResponseDtoFromModel(any(Appointment.class))).thenReturn(appointmentResponseDtoList.get(0));

        List<AppointmentResponseDto> result = appointmentUseCase.findAll();

        verify(appointmentRepository, times(1)).findAll();
        verify(appointmentMapper, times(appointmentList.size())).toResponseDtoFromModel(any(Appointment.class));

        assertEquals(appointmentResponseDtoList, result);
    }

    @Test
    void testUpdate() {
        Affiliate affiliate = Affiliate
                .builder()
                .id(1)
                .build();
        Exam exam = Exam.builder()
                .id(1)
                .build();
        Appointment existingAppointment = Appointment.builder()
                .id(1)
                .affiliate(affiliate)
                .exam(exam)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();
        AppointmentRequestDto appointmentRequestDto = AppointmentRequestDto.builder()
                .affiliateId(1)
                .examId(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .build();
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .build();
        AppointmentResponseDto appointmentResponseDto = AppointmentResponseDto.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .affiliate(affiliateResponseDto)
                .exam(examResponseDto)
                .build();

        when(examRepository.findById(any(Integer.class))).thenReturn(exam);
        when(affiliateRepository.findById(any(Integer.class))).thenReturn(affiliate);
        when(appointmentRepository.findById(any(Integer.class))).thenReturn(existingAppointment);
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(existingAppointment);
        when(appointmentMapper.toResponseDtoFromModel(any(Appointment.class))).thenReturn(appointmentResponseDto);

        AppointmentResponseDto result = appointmentUseCase.update(1, appointmentRequestDto);

        verify(examRepository, times(1)).findById(any(Integer.class));
        verify(affiliateRepository, times(1)).findById(any(Integer.class));
        verify(appointmentRepository, times(1)).findById(1);
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
        verify(appointmentMapper, times(1)).toResponseDtoFromModel(any(Appointment.class));

        assertEquals(appointmentResponseDto, result);
    }

    @Test
    void testDelete() {
        when(appointmentRepository.existsById(any(Integer.class))).thenReturn(true);

        appointmentUseCase.delete(1);

        verify(appointmentRepository, times(1)).existsById(1);
        verify(appointmentRepository, times(1)).delete(1);
    }
    @Test
    void testDeleteThrowsException() {
        when(appointmentRepository.existsById(any(Integer.class))).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> appointmentUseCase.delete(1));

        verify(appointmentRepository, times(1)).existsById(1);
    }

    @Test
    void testFindByDate() {
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .build();
        Exam exam = Exam.builder()
                .id(1)
                .build();
        LocalDate date = LocalDate.now();
        List<Appointment> appointmentList = List.of(Appointment.builder()
                .id(1)
                .affiliate(affiliate)
                .exam(exam)
                .date(date)
                .hour(LocalTime.now())
                .build());
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .build();
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .build();
        List<AppointmentResponseDto> appointmentResponseDtoList = List.of(AppointmentResponseDto.builder()
                .id(1)
                .date(date)
                .hour(LocalTime.now())
                .affiliate(affiliateResponseDto)
                .exam(examResponseDto)
                .build());

        when(appointmentRepository.findByDate(any(LocalDate.class))).thenReturn(appointmentList);
        when(appointmentMapper.toResponseDtoFromModel(any(Appointment.class))).thenReturn(appointmentResponseDtoList.get(0));

        List<AppointmentResponseDto> result = appointmentUseCase.findByDate(date);

        verify(appointmentRepository, times(1)).findByDate(date);
        verify(appointmentMapper, times(appointmentList.size())).toResponseDtoFromModel(any(Appointment.class));

        assertEquals(appointmentResponseDtoList, result);
    }
}
