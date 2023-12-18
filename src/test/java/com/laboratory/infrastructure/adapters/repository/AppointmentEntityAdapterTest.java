package com.laboratory.infrastructure.adapters.repository;

import com.laboratory.domain.model.Appointment;
import com.laboratory.infrastructure.adapters.entity.AppointmentEntity;
import com.laboratory.infrastructure.adapters.mapper.AppointmentMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentEntityAdapterTest {

    @InjectMocks
    private AppointmentEntityRepositoryAdapter appointmentEntityRepositoryAdapter;
    @Mock
    private AppointmentEntityRepository appointmentEntityRepository;
    @Mock
    private AppointmentMapper appointmentMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Appointment appointment = Appointment.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();

        when(appointmentMapper.toEntityFromModel(appointment)).thenReturn(appointmentEntity);
        when(appointmentEntityRepository.save(appointmentEntity)).thenReturn(appointmentEntity);
        when(appointmentMapper.toModelFromEntity(appointmentEntity)).thenReturn(appointment);

        Appointment result = appointmentEntityRepositoryAdapter.save(appointment);

        verify(appointmentMapper, times(1)).toEntityFromModel(appointment);
        verify(appointmentEntityRepository, times(1)).save(appointmentEntity);
        verify(appointmentMapper, times(1)).toModelFromEntity(appointmentEntity);

        assertEquals(appointment, result);
    }

    @Test
    void testFindById() {
        Appointment appointment = Appointment.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();

        when(appointmentEntityRepository.findById(1)).thenReturn(Optional.of(appointmentEntity));
        when(appointmentMapper.toModelFromEntity(appointmentEntity)).thenReturn(appointment);

        Appointment result = appointmentEntityRepositoryAdapter.findById(1);

        verify(appointmentEntityRepository, times(1)).findById(1);
        verify(appointmentMapper, times(1)).toModelFromEntity(appointmentEntity);

        assertEquals(appointment, result);
    }
    @Test
    void testFindByIdThrowsException() {
        when(appointmentEntityRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> appointmentEntityRepositoryAdapter.findById(1));

        verify(appointmentEntityRepository, times(1)).findById(1);
    }

    @Test
    void testFindAll() {
        Appointment appointment = Appointment.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .id(1)
                .date(LocalDate.now())
                .hour(LocalTime.now())
                .build();
        List<AppointmentEntity> appointmentEntityList = List.of(appointmentEntity);

        when(appointmentEntityRepository.findAll()).thenReturn(appointmentEntityList);
        when(appointmentMapper.toModelFromEntity(appointmentEntity)).thenReturn(appointment);

        List<Appointment> result = appointmentEntityRepositoryAdapter.findAll();

        verify(appointmentEntityRepository, times(1)).findAll();
        verify(appointmentMapper, times(appointmentEntityList.size())).toModelFromEntity(any(AppointmentEntity.class));

        assertEquals(1, result.size());
        assertEquals(appointment, result.get(0));
    }

    @Test
    void testDelete() {
        Integer id = 1;

        appointmentEntityRepositoryAdapter.delete(id);

        verify(appointmentEntityRepository, times(1)).deleteById(id);
    }

    @Test
    void testExistsById() {
        Integer id = 1;

        when(appointmentEntityRepository.existsById(id)).thenReturn(true);

        boolean exists = appointmentEntityRepositoryAdapter.existsById(id);

        verify(appointmentEntityRepository, times(1)).existsById(id);

        assertTrue(exists);
    }
    @Test
    void testFindByDate() {
        LocalDate date = LocalDate.now();
        Appointment appointment = Appointment.builder()
                .id(1)
                .date(date)
                .hour(LocalTime.now())
                .build();
        AppointmentEntity appointmentEntity = AppointmentEntity.builder()
                .id(1)
                .date(date)
                .hour(LocalTime.now())
                .build();
        List<AppointmentEntity> appointmentEntityList = List.of(appointmentEntity);

        when(appointmentEntityRepository.findByDate(date)).thenReturn(appointmentEntityList);
        when(appointmentMapper.toModelFromEntity(appointmentEntity)).thenReturn(appointment);

        List<Appointment> result = appointmentEntityRepositoryAdapter.findByDate(date);

        verify(appointmentEntityRepository, times(1)).findByDate(date);
        verify(appointmentMapper, times(appointmentEntityList.size())).toModelFromEntity(any(AppointmentEntity.class));

        assertEquals(1, result.size());
        assertEquals(appointment, result.get(0));
    }

    @Test
    void testFindByDateThrowsException() {
        LocalDate date = LocalDate.now();

        when(appointmentEntityRepository.findByDate(date)).thenReturn(List.of());

        assertThrows(EntityNotFoundException.class, () -> appointmentEntityRepositoryAdapter.findByDate(date));

        verify(appointmentEntityRepository, times(1)).findByDate(date);
    }
}
