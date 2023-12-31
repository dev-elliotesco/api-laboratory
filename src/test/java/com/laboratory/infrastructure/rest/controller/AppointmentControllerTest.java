package com.laboratory.infrastructure.rest.controller;

import com.laboratory.application.usecase.AppointmentUseCase;
import com.laboratory.domain.model.Affiliate;
import com.laboratory.domain.model.Appointment;
import com.laboratory.domain.model.Exam;
import com.laboratory.infrastructure.adapters.mapper.AppointmentMapper;
import com.laboratory.infrastructure.rest.controller.AppointmentController;
import com.laboratory.infrastructure.rest.dto.AffiliateResponseDto;
import com.laboratory.infrastructure.rest.dto.AppointmentRequestDto;
import com.laboratory.infrastructure.rest.dto.AppointmentResponseDto;
import com.laboratory.infrastructure.rest.dto.ExamResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AppointmentControllerTest {
    @InjectMocks
    private AppointmentController appointmentController;
    @Mock
    private AppointmentUseCase appointmentUseCase;
    @Mock
    private AppointmentMapper appointmentMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appointmentController).build();
    }

    @Test
    void testSave() throws Exception {
        Exam exam = Exam.builder()
                .id(1).build();
        Affiliate affiliate = Affiliate.builder()
                .id(1).build();
        AppointmentRequestDto appointmentRequestDto = AppointmentRequestDto.builder()
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .examId(1)
                .affiliateId(1)
                .build();
        Appointment appointment = Appointment.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .exam(exam)
                .affiliate(affiliate)
                .build();
        AppointmentResponseDto appointmentResponseDto = AppointmentResponseDto.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .exam(ExamResponseDto.builder()
                        .id(1).build())
                .affiliate(AffiliateResponseDto.builder()
                        .id(1).build())
                .build();

        when(appointmentMapper.toModelFromRequestDto(appointmentRequestDto,exam,affiliate)).thenReturn(appointment);
        when(appointmentUseCase.save(appointmentRequestDto)).thenReturn(appointmentResponseDto);

        String requestBody = "{ \"date\": \"2021-10-10\", \"hour\": \"10:00\", \"examId\": 1, \"affiliateId\": 1 }";

        mockMvc.perform(post("/api/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andExpect(status().isCreated());
    }

    @Test
    void testFindAll() throws Exception {
        AppointmentResponseDto appointmentResponseDto = AppointmentResponseDto.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .exam(ExamResponseDto.builder()
                        .id(1).build())
                .affiliate(AffiliateResponseDto.builder()
                        .id(1).build())
                .build();
        List<AppointmentResponseDto> appointmentResponseDtoList = List.of(appointmentResponseDto);

        when(appointmentUseCase.findAll()).thenReturn(appointmentResponseDtoList);

        mockMvc.perform(get("/api/appointment")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

    }

    @Test
    void testFindById() throws Exception {
        AppointmentResponseDto appointmentResponseDto = AppointmentResponseDto.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .exam(ExamResponseDto.builder()
                        .id(1).build())
                .affiliate(AffiliateResponseDto.builder()
                        .id(1).build())
                .build();

        when(appointmentUseCase.findById(1)).thenReturn(appointmentResponseDto);

        mockMvc.perform(get("/api/appointment/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        Exam exam = Exam.builder()
                .id(1).build();
        Affiliate affiliate = Affiliate.builder()
                .id(1).build();
        AppointmentRequestDto appointmentRequestDto = AppointmentRequestDto.builder()
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .examId(1)
                .affiliateId(1)
                .build();
        Appointment appointment = Appointment.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .exam(exam)
                .affiliate(affiliate)
                .build();
        AppointmentResponseDto appointmentResponseDto = AppointmentResponseDto.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .exam(ExamResponseDto.builder()
                        .id(1).build())
                .affiliate(AffiliateResponseDto.builder()
                        .id(1).build())
                .build();

        when(appointmentMapper.toModelFromRequestDto(appointmentRequestDto,exam,affiliate)).thenReturn(appointment);
        when(appointmentUseCase.update(1,appointmentRequestDto)).thenReturn(appointmentResponseDto);

        String requestBody = "{ \"date\": \"2021-10-10\", \"hour\": \"10:00\", \"examId\": 1, \"affiliateId\": 1 }";

        mockMvc.perform(put("/api/appointment/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andExpect(status().isOk());
    }

    @Test
    void testDelete() throws Exception {
        Integer id = 1;

        doNothing().when(appointmentUseCase).delete(id);

        mockMvc.perform(delete("/api/appointment/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(appointmentUseCase, times(1)).delete(id);
    }

    @Test
    void testFindByDate() throws Exception {
        AppointmentResponseDto appointmentResponseDto = AppointmentResponseDto.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .exam(ExamResponseDto.builder()
                        .id(1).build())
                .affiliate(AffiliateResponseDto.builder()
                        .id(1).build())
                .build();
        List<AppointmentResponseDto> appointmentResponseDtoList = List.of(appointmentResponseDto);

        when(appointmentUseCase.findByDate(LocalDate.now())).thenReturn(appointmentResponseDtoList);

        mockMvc.perform(get("/api/appointment/date/2021-10-10")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}
