package com.laboratory.infrastructure.adapters.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "EXAM")
public class ExamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXAM_ID")
    private Integer id;
    @Column(name = "EXAM_NAME")
    private String name;
    @Column(name = "EXAM_DESCRIPTION")
    private String description;
    @OneToMany(mappedBy = "exam")
    private final Set<AppointmentEntity> appointments = new HashSet<>();

}
