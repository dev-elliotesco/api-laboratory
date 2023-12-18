package com.laboratory.infrastructure.rest.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AffiliateResponseDtoTest {

    @Test
    void testSetMethods() {
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        affiliateResponseDto.setId(2);
        affiliateResponseDto.setName("New Affiliate name");
        affiliateResponseDto.setLastname("New Affiliate lastname");
        affiliateResponseDto.setEmail("New Affiliate email");
        affiliateResponseDto.setPhone("New Affiliate phone");
        affiliateResponseDto.setAge(40);

        assertEquals(2, affiliateResponseDto.getId());
        assertEquals("New Affiliate name", affiliateResponseDto.getName());
        assertEquals("New Affiliate lastname", affiliateResponseDto.getLastname());
        assertEquals("New Affiliate email", affiliateResponseDto.getEmail());
        assertEquals("New Affiliate phone", affiliateResponseDto.getPhone());
        assertEquals(40, affiliateResponseDto.getAge());
    }

    @Test
    void testToString() {
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        String expected = "AffiliateResponseDto(id=1, name=Affiliate name, lastname=Affiliate lastname, " +
                "email=Affiliate email, phone=Affiliate phone, age=30)";

        assertEquals(expected, affiliateResponseDto.toString());
    }

    @Test
    void testHashCode() {
        AffiliateResponseDto affiliateResponseDto1 = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        AffiliateResponseDto affiliateResponseDto2 = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        assertEquals(affiliateResponseDto1.hashCode(), affiliateResponseDto2.hashCode());
    }

    @Test
    void testEquals() {
        AffiliateResponseDto affiliateResponseDto1 = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        AffiliateResponseDto affiliateResponseDto2 = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        assertEquals(affiliateResponseDto1, affiliateResponseDto2);
    }
}
