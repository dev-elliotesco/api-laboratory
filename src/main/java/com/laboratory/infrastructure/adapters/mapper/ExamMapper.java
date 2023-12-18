package com.laboratory.infrastructure.adapters.mapper;

import com.laboratory.domain.model.Exam;
import com.laboratory.infrastructure.adapters.entity.ExamEntity;
import com.laboratory.infrastructure.rest.dto.ExamRequestDto;
import com.laboratory.infrastructure.rest.dto.ExamResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamMapper {

    public ExamEntity toEntityFromModel(Exam exam) {
        return ExamEntity.builder()
                .id(exam.getId())
                .name(exam.getName())
                .description(exam.getDescription())
                .build();
    }

    public Exam toModelFromEntity(ExamEntity examEntity) {
        return Exam.builder()
                .id(examEntity.getId())
                .name(examEntity.getName())
                .description(examEntity.getDescription())
                .build();
    }

    public Exam toModelFromRequestDto(ExamRequestDto examRequestDto) {
        return Exam.builder()
                .name(examRequestDto.getName())
                .description(examRequestDto.getDescription())
                .build();
    }

    public ExamResponseDto toResponseDtoFromModel(Exam exam) {
        return ExamResponseDto.builder()
                .id(exam.getId())
                .name(exam.getName())
                .description(exam.getDescription())
                .build();
    }

}
