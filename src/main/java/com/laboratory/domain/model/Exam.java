package com.laboratory.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Exam {
    private Integer id;
    private String name;
    private String description;
    private final Set<Appointment> appointments = new HashSet<>();
}
