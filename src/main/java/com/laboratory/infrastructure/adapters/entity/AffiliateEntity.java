package com.laboratory.infrastructure.adapters.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AFFILIATE")
public class AffiliateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AFFILIATE_ID")
    private Integer id;
    @Column(name = "AFFILIATE_NAME")
    private String name;
    @Column(name = "AFFILIATE_LASTNAME")
    private String lastname;
    @Column(name = "AFFILIATE_EMAIL")
    private String email;
    @Column(name = "AFFILIATE_PHONE")
    private String phone;
    @Column(name = "AFFILIATE_AGE")
    private Integer age;
    @OneToMany(mappedBy = "affiliate")
    private final Set<AppointmentEntity> appointments = new HashSet<>();

}
