package com.laboratory.infrastructure.adapters.repository;

import com.laboratory.infrastructure.adapters.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamEntityRepository extends JpaRepository<ExamEntity, Integer> {

}
