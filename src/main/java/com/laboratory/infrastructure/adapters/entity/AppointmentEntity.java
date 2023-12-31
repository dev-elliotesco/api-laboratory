package com.laboratory.infrastructure.adapters.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "APPOINTMENT")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private Integer id;
    @Column(name = "APPOINTMENT_DATE")
    private LocalDate date;
    @Column(name = "APPOINTMENT_HOUR")
    private LocalTime hour;
    @ManyToOne
    @JoinColumn(name = "EXAM_ID")
    private ExamEntity exam;
    @ManyToOne
    @JoinColumn(name = "AFFILIATE_ID")
    private AffiliateEntity affiliate;
}
