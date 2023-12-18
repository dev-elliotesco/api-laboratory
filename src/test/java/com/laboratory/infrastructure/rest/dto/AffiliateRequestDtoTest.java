package com.laboratory.infrastructure.rest.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AffiliateRequestDtoTest {

    @Test
    void testSetMethods() {
        AffiliateRequestDto affiliateRequestDto = AffiliateRequestDto.builder()
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        affiliateRequestDto.setName("New Affiliate name");
        affiliateRequestDto.setLastname("New Affiliate lastname");
        affiliateRequestDto.setEmail("New Affiliate email");
        affiliateRequestDto.setPhone("New Affiliate phone");
        affiliateRequestDto.setAge(40);

        assertEquals("New Affiliate name", affiliateRequestDto.getName());
        assertEquals("New Affiliate lastname", affiliateRequestDto.getLastname());
        assertEquals("New Affiliate email", affiliateRequestDto.getEmail());
        assertEquals("New Affiliate phone", affiliateRequestDto.getPhone());
        assertEquals(40, affiliateRequestDto.getAge());
    }

    @Test
    void testToString() {
        AffiliateRequestDto affiliateRequestDto = AffiliateRequestDto.builder()
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        String expected = "AffiliateRequestDto(name=Affiliate name, lastname=Affiliate lastname, " +
                "email=Affiliate email, phone=Affiliate phone, age=30)";

        assertEquals(expected, affiliateRequestDto.toString());
    }

    @Test
    void testHashCode() {
        AffiliateRequestDto affiliateRequestDto1 = AffiliateRequestDto.builder()
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        AffiliateRequestDto affiliateRequestDto2 = AffiliateRequestDto.builder()
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        assertEquals(affiliateRequestDto1.hashCode(), affiliateRequestDto2.hashCode());
    }

    @Test
    void testEquals() {
        AffiliateRequestDto affiliateRequestDto1 = AffiliateRequestDto.builder()
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        AffiliateRequestDto affiliateRequestDto2 = AffiliateRequestDto.builder()
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        assertEquals(affiliateRequestDto1, affiliateRequestDto2);
    }
}
