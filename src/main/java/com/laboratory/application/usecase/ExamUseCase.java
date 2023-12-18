package com.laboratory.application.usecase;

import com.laboratory.domain.model.Exam;
import com.laboratory.domain.port.ExamRepository;
import com.laboratory.infrastructure.adapters.mapper.ExamMapper;
import com.laboratory.infrastructure.rest.dto.ExamRequestDto;
import com.laboratory.infrastructure.rest.dto.ExamResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamUseCase {
    private final ExamRepository examRepository;
    private final ExamMapper examMapper;

    public ExamResponseDto save(Exam exam) {
        Exam examSaved = examRepository.save(exam);
        return examMapper.toResponseDtoFromModel(examSaved);
    }

    public List<ExamResponseDto> findAll() {
        List<Exam> exams = examRepository.findAll();
        return exams.stream()
                .map(examMapper::toResponseDtoFromModel)
                .toList();
    }

    public ExamResponseDto findById(Integer id) {
        Exam exam = examRepository.findById(id);
        return examMapper.toResponseDtoFromModel(exam);
    }

    public void delete(Integer id) {
        if (!examRepository.existsById(id)) {
            throw new EntityNotFoundException("Test not found with id: " + id);
        }
        examRepository.delete(id);
    }

    public ExamResponseDto update(Integer id, ExamRequestDto examRequestDto) {
        Exam existingExam = examRepository.findById(id);
        existingExam.setName(examRequestDto.getName());
        existingExam.setDescription(examRequestDto.getDescription());
        Exam updateExam = examRepository.save(existingExam);
        return examMapper.toResponseDtoFromModel(updateExam);
    }

}
