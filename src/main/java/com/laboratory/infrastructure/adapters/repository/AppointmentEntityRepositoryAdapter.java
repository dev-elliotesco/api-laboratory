package com.laboratory.infrastructure.adapters.repository;

import com.laboratory.domain.model.Appointment;
import com.laboratory.domain.port.AppointmentRepository;
import com.laboratory.infrastructure.adapters.entity.AppointmentEntity;
import com.laboratory.infrastructure.adapters.mapper.AppointmentMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AppointmentEntityRepositoryAdapter implements AppointmentRepository {

    private final AppointmentEntityRepository appointmentEntityRepository;
    private final AppointmentMapper appointmentMapper;


    @Override
    public Appointment save(Appointment appointment) {
        AppointmentEntity appointmentEntity = appointmentMapper.toEntityFromModel(appointment);
        appointmentEntityRepository.save(appointmentEntity);
        return appointmentMapper.toModelFromEntity(appointmentEntity);
    }

    @Override
    public Appointment findById(Integer id) {
        AppointmentEntity appointmentEntity = appointmentEntityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appoinment not found with id: " + id));
        return appointmentMapper.toModelFromEntity(appointmentEntity);
    }

    @Override
    public List<Appointment> findAll() {

        return appointmentEntityRepository.findAll().stream()
                .map(appointmentMapper::toModelFromEntity)
                .toList();
    }

    @Override
    public void delete(Integer id) {
        appointmentEntityRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return appointmentEntityRepository.existsById(id);
    }

    @Override
    public List<Appointment> findByDate(LocalDate date) {
        List<AppointmentEntity> appointmentEntities = appointmentEntityRepository.findByDate(date);
        if (appointmentEntities.isEmpty()) {
            throw new EntityNotFoundException("No appointments found with date: " + date);
        }
        return appointmentEntities.stream()
                .map(appointmentMapper::toModelFromEntity)
                .toList();
    }


}
