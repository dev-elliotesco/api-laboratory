package com.laboratory.infrastructure.adapters.mapper;

import com.laboratory.domain.model.Exam;
import com.laboratory.infrastructure.adapters.entity.ExamEntity;
import com.laboratory.infrastructure.rest.dto.ExamRequestDto;
import com.laboratory.infrastructure.rest.dto.ExamResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExamMapperTest {
    private ExamMapper examMapper;

    @BeforeEach
    public void setUp() {
        examMapper = new ExamMapper();
    }

    @Test
    void testToEntityFromModel() {
        Exam exam = Exam.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        ExamEntity examEntity = examMapper.toEntityFromModel(exam);

        assertEquals(exam.getId(), examEntity.getId());
        assertEquals(exam.getName(), examEntity.getName());
        assertEquals(exam.getDescription(), examEntity.getDescription());
    }

    @Test
    void testToModelFromEntity() {
        ExamEntity examEntity = ExamEntity.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        Exam exam = examMapper.toModelFromEntity(examEntity);

        assertEquals(examEntity.getId(), exam.getId());
        assertEquals(examEntity.getName(), exam.getName());
        assertEquals(examEntity.getDescription(), exam.getDescription());
    }

    @Test
    void testToModelFromRequestDto() {
        ExamRequestDto examRequestDto = ExamRequestDto.builder()
                .name("Exam name")
                .description("Exam description")
                .build();

        Exam exam = examMapper.toModelFromRequestDto(examRequestDto);

        assertEquals(examRequestDto.getName(), exam.getName());
        assertEquals(examRequestDto.getDescription(), exam.getDescription());
    }

    @Test
    void testToResponseDtoFromModel() {
        Exam exam = Exam.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        ExamResponseDto examResponseDto = examMapper.toResponseDtoFromModel(exam);

        assertEquals(exam.getId(), examResponseDto.getId());
        assertEquals(exam.getName(), examResponseDto.getName());
        assertEquals(exam.getDescription(), examResponseDto.getDescription());
    }
}
