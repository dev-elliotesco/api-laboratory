package com.laboratory.infrastructure.adapters.repository;

import com.laboratory.domain.model.Exam;
import com.laboratory.domain.port.ExamRepository;
import com.laboratory.infrastructure.adapters.entity.ExamEntity;
import com.laboratory.infrastructure.adapters.mapper.ExamMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExamEntityRepositoryAdapter implements ExamRepository {

    private final ExamEntityRepository examEntityRepository;

    private final ExamMapper examMapper;

    @Override
    public Exam save(Exam exam) {
        ExamEntity examEntity = examMapper.toEntityFromModel(exam);
        ExamEntity examEntitySaved = examEntityRepository.save(examEntity);
        return examMapper.toModelFromEntity(examEntitySaved);
    }

    @Override
    public Exam findById(Integer id) {
        ExamEntity foundEntity = examEntityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exam not found with id: " + id));
        return examMapper.toModelFromEntity(foundEntity);
    }

    @Override
    public List<Exam> findAll() {
        return examEntityRepository.findAll().stream()
                .map(examMapper::toModelFromEntity)
                .toList();
    }
    @Override
    public void delete(Integer id) {
        examEntityRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return examEntityRepository.existsById(id);
    }
}
