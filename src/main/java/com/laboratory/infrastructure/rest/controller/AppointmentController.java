package com.laboratory.infrastructure.rest.controller;

import com.laboratory.application.usecase.AppointmentUseCase;
import com.laboratory.infrastructure.rest.dto.AppointmentRequestDto;
import com.laboratory.infrastructure.rest.dto.AppointmentResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@AllArgsConstructor
public class AppointmentController {

    private final AppointmentUseCase appointmentUseCase;

    @PostMapping
    public ResponseEntity<AppointmentResponseDto> save(@Valid @RequestBody AppointmentRequestDto appointmentRequestDto) {
        AppointmentResponseDto appointment = appointmentUseCase.save(appointmentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> findById(@PathVariable Integer id) {
        AppointmentResponseDto appointment = appointmentUseCase.findById(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponseDto>> findAll() {
        List<AppointmentResponseDto> appointments = appointmentUseCase.findAll();
        return ResponseEntity.ok(appointments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> update(@PathVariable Integer id, @Valid @RequestBody AppointmentRequestDto appointmentRequestDto) {
        AppointmentResponseDto appointment = appointmentUseCase.update(id, appointmentRequestDto);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        appointmentUseCase.delete(id);
        return ResponseEntity.ok("Appointment deleted");
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<AppointmentResponseDto>> findByDate(@PathVariable LocalDate date) {
        List<AppointmentResponseDto> appointments = appointmentUseCase.findByDate(date);
        return ResponseEntity.ok(appointments);
    }
}
