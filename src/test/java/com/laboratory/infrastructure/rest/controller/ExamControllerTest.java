package com.laboratory.infrastructure.rest.controller;

import com.laboratory.application.usecase.ExamUseCase;
import com.laboratory.domain.model.Exam;
import com.laboratory.infrastructure.adapters.mapper.ExamMapper;
import com.laboratory.infrastructure.rest.controller.ExamController;
import com.laboratory.infrastructure.rest.dto.ExamRequestDto;
import com.laboratory.infrastructure.rest.dto.ExamResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExamControllerTest {
    @InjectMocks
    private ExamController examController;
    @Mock
    private ExamUseCase examUseCase;
    @Mock
    private ExamMapper examMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(examController).build();
    }

    @Test
    void testSave() throws Exception {
        ExamRequestDto examRequestDto = ExamRequestDto.builder()
                .name("Exam name")
                .description("Exam description")
                .build();
        Exam exam = Exam.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        when(examMapper.toModelFromRequestDto(examRequestDto)).thenReturn(exam);
        when(examUseCase.save(exam)).thenReturn(examResponseDto);

        String requestBody = "{ \"name\": \"Exam name\", \"description\": \"Exam description\" }";


        mockMvc.perform(post("/api/exam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andExpect(status().isCreated());
    }

    @Test
    void testFindAll() throws Exception {
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();
        List<ExamResponseDto> examResponseDtoList = List.of(examResponseDto);

        when(examUseCase.findAll()).thenReturn(examResponseDtoList);

        mockMvc.perform(get("/api/exam")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(examUseCase, times(1)).findAll();
    }

    @Test
    void testFindById() throws Exception {
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        when(examUseCase.findById(1)).thenReturn(examResponseDto);

        mockMvc.perform(get("/api/exam/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(examUseCase, times(1)).findById(1);
    }

    @Test
    void testUpdate() throws Exception {
        ExamRequestDto examRequestDto = ExamRequestDto.builder()
                .name("Updated Exam name")
                .description("Updated Exam description")
                .build();
        Exam exam = Exam.builder()
                .id(1)
                .name("Updated Exam name")
                .description("Updated Exam description")
                .build();
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .name("Updated Exam name")
                .description("Updated Exam description")
                .build();

        when(examUseCase.update(1, examRequestDto)).thenReturn(examResponseDto);

        String requestBody = "{ \"name\": \"Updated Exam name\", \"description\": \"Updated Exam description\" }";

        mockMvc.perform(put("/api/exam/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());

        verify(examUseCase, times(1)).update(1, examRequestDto);
    }

    @Test
    void testDelete() throws Exception {
        Integer id = 1;

        doNothing().when(examUseCase).delete(id);

        mockMvc.perform(delete("/api/exam/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(examUseCase, times(1)).delete(id);
    }
}
