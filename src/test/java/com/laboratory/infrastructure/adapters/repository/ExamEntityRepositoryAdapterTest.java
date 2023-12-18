package com.laboratory.infrastructure.adapters.repository;

import com.laboratory.domain.model.Exam;
import com.laboratory.infrastructure.adapters.entity.ExamEntity;
import com.laboratory.infrastructure.adapters.mapper.ExamMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExamEntityRepositoryAdapterTest {

    @InjectMocks
    private ExamEntityRepositoryAdapter examEntityRepositoryAdapter;
    @Mock
    private ExamEntityRepository examEntityRepository;
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
        ExamEntity examEntity = ExamEntity.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        when(examMapper.toEntityFromModel(exam)).thenReturn(examEntity);
        when(examEntityRepository.save(examEntity)).thenReturn(examEntity);
        when(examMapper.toModelFromEntity(examEntity)).thenReturn(exam);

        Exam result = examEntityRepositoryAdapter.save(exam);

        verify(examEntityRepository, times(1)).save(examEntity);
        assertEquals(exam, result);
    }

    @Test
    void testFindById() {
        Exam exam = Exam.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();
        ExamEntity examEntity = ExamEntity.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        when(examEntityRepository.findById(1)).thenReturn(Optional.of(examEntity));
        when(examMapper.toModelFromEntity(examEntity)).thenReturn(exam);

        Exam result = examEntityRepositoryAdapter.findById(1);

        verify(examEntityRepository, times(1)).findById(1);
        assertEquals(exam, result);
    }

    @Test
    void testFindByIdThrowsException() {
        when(examEntityRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> examEntityRepositoryAdapter.findById(1));

        verify(examEntityRepository, times(1)).findById(1);
    }

    @Test
    void testFindAll() {
        Exam exam = Exam.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();
        ExamEntity examEntity = ExamEntity.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();
        List<ExamEntity> examEntityList = List.of(examEntity);

        when(examEntityRepository.findAll()).thenReturn(examEntityList);
        when(examMapper.toModelFromEntity(examEntity)).thenReturn(exam);

        List<Exam> result = examEntityRepositoryAdapter.findAll();

        verify(examEntityRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals(exam, result.get(0));
    }

    @Test
    void testDelete() {
        Integer id = 1;

        examEntityRepositoryAdapter.delete(id);

        verify(examEntityRepository, times(1)).deleteById(id);
    }

    @Test
    void testExistsById() {
        Integer id = 1;

        when(examEntityRepository.existsById(id)).thenReturn(true);

        boolean exists = examEntityRepositoryAdapter.existsById(id);

        verify(examEntityRepository, times(1)).existsById(id);

        assertTrue(exists);
    }
}
