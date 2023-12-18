package com.laboratory.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Affiliate {
        private Integer id;
        private String name;
        private String lastname;
        private String email;
        private String phone;
        private Integer age;
        private final Set<Appointment> appointments = new HashSet<>();
}
