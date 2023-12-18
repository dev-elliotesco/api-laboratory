package com.laboratory.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AffiliateTest {

    @Test
    void testToString() {
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        String expected = "Affiliate(id=1, name=Affiliate name, lastname=Affiliate lastname, " +
                "email=Affiliate email, phone=Affiliate phone, age=30, appointments=[])";
        assertEquals(expected, affiliate.toString());
    }

    @Test
    void testHashCode() {
        Affiliate affiliate1 = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        Affiliate affiliate2 = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        assertEquals(affiliate1.hashCode(), affiliate2.hashCode());
    }

    @Test
    void testEquals() {
        Affiliate affiliate1 = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        Affiliate affiliate2 = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        assertEquals(affiliate1, affiliate2);
    }
}
