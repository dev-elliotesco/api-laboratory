package com.laboratory.infrastructure.adapters.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AffiliateEntityTest {

    @Test
    void testSetMethods() {
        AffiliateEntity affiliateEntity = AffiliateEntity.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        affiliateEntity.setId(2);
        affiliateEntity.setName("New Affiliate name");
        affiliateEntity.setLastname("New Affiliate lastname");
        affiliateEntity.setEmail("New Affiliate email");
        affiliateEntity.setPhone("New Affiliate phone");
        affiliateEntity.setAge(40);

        assertEquals(2, affiliateEntity.getId());
        assertEquals("New Affiliate name", affiliateEntity.getName());
        assertEquals("New Affiliate lastname", affiliateEntity.getLastname());
        assertEquals("New Affiliate email", affiliateEntity.getEmail());
        assertEquals("New Affiliate phone", affiliateEntity.getPhone());
        assertEquals(40, affiliateEntity.getAge());
    }

    @Test
    void testToString() {
        AffiliateEntity affiliateEntity = AffiliateEntity.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        String expected = "AffiliateEntity(id=1, name=Affiliate name, lastname=Affiliate lastname, " +
                "email=Affiliate email, phone=Affiliate phone, age=30, appointments=[])";

        assertEquals(expected, affiliateEntity.toString());
    }

    @Test
    void testHashCode() {
        AffiliateEntity affiliateEntity1 = AffiliateEntity.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        AffiliateEntity affiliateEntity2 = AffiliateEntity.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        assertEquals(affiliateEntity2.hashCode(), affiliateEntity1.hashCode());
    }

    @Test
    void testEquals() {
        AffiliateEntity affiliateEntity1 = AffiliateEntity.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        AffiliateEntity affiliateEntity2 = AffiliateEntity.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        assertEquals(affiliateEntity1, affiliateEntity2);
    }
}
