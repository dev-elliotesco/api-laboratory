package com.laboratory.application.usecase;

import com.laboratory.domain.model.Exam;
import com.laboratory.domain.port.ExamRepository;
import com.laboratory.infrastructure.adapters.mapper.ExamMapper;
import com.laboratory.infrastructure.rest.dto.ExamRequestDto;
import com.laboratory.infrastructure.rest.dto.ExamResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ExamUseCaseTest {
    @InjectMocks
    private ExamUseCase examUseCase;

    @Mock
    private ExamRepository examRepository;

    @Mock
    private ExamMapper examMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
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

        when(examRepository.save(any(Exam.class))).thenReturn(exam);
        when(examMapper.toResponseDtoFromModel(any(Exam.class))).thenReturn(examResponseDto);


        ExamResponseDto result = examUseCase.save(exam);
        verify(examRepository, times(1)).save(exam);
        verify(examMapper, times(1)).toResponseDtoFromModel(exam);

        assertEquals(examResponseDto, result);
    }

    @Test
    void testFindAll() {

        List<Exam> examList = Collections.singletonList(Exam.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build());
        List<ExamResponseDto> examResponseDtoList = Collections.singletonList(ExamResponseDto.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build());

        when(examRepository.findAll()).thenReturn(examList);
        when(examMapper.toResponseDtoFromModel(any(Exam.class))).thenReturn(examResponseDtoList.get(0));


        List<ExamResponseDto> result = examUseCase.findAll();

        verify(examRepository, times(1)).findAll();
        verify(examMapper, times(examList.size())).toResponseDtoFromModel(any(Exam.class));

        assertEquals(examResponseDtoList, result);
    }

    @Test
     void testFindById() {
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

        when(examRepository.findById(any(Integer.class))).thenReturn(exam);
        when(examMapper.toResponseDtoFromModel(any(Exam.class))).thenReturn(examResponseDto);

        ExamResponseDto result = examUseCase.findById(1);

        verify(examRepository, times(1)).findById(1);
        verify(examMapper, times(1)).toResponseDtoFromModel(exam);

        assertEquals(examResponseDto, result);
    }

    @Test
     void testUpdate() {

        Exam existingExam = Exam.builder()
                .id(1)
                .name("Existing name")
                .description("Existing description")
                .build();
        ExamRequestDto examRequestDto = ExamRequestDto.builder()
                .name("New name")
                .description("New description")
                .build();
        Exam updatedExam = Exam.builder()
                .id(1)
                .name(examRequestDto.getName())
                .description(examRequestDto.getDescription())
                .build();
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .name(examRequestDto.getName())
                .description(examRequestDto.getDescription())
                .build();

        when(examRepository.findById(any(Integer.class))).thenReturn(existingExam);
        when(examRepository.save(any(Exam.class))).thenReturn(updatedExam);
        when(examMapper.toResponseDtoFromModel(any(Exam.class))).thenReturn(examResponseDto);

        ExamResponseDto result = examUseCase.update(1, examRequestDto);

        verify(examRepository, times(1)).findById(1);
        verify(examRepository, times(1)).save(updatedExam);
        verify(examMapper, times(1)).toResponseDtoFromModel(updatedExam);

        assertEquals(examResponseDto, result);
    }

    @Test
     void testDelete() {
        Integer id = 1;

        when(examRepository.existsById(id)).thenReturn(true);

        examUseCase.delete(id);

        verify(examRepository, times(1)).existsById(id);
        verify(examRepository, times(1)).delete(id);
    }

    @Test
     void testDeleteThrowsException() {
        when(examRepository.existsById(any(Integer.class))).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> examUseCase.delete(1));

        verify(examRepository, times(1)).existsById(1);
    }
}
