package com.laboratory.infrastructure.adapters.repository;

import com.laboratory.infrastructure.adapters.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, Integer> {

    @Query("SELECT a FROM AppointmentEntity a WHERE a.date = :date ORDER BY a.affiliate.id")
    List<AppointmentEntity> findByDate(@Param("date") LocalDate date);
}
