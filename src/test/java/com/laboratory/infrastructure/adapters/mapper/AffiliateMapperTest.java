package com.laboratory.infrastructure.adapters.mapper;

import com.laboratory.domain.model.Affiliate;
import com.laboratory.infrastructure.adapters.entity.AffiliateEntity;
import com.laboratory.infrastructure.rest.dto.AffiliateRequestDto;
import com.laboratory.infrastructure.rest.dto.AffiliateResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AffiliateMapperTest {

    private AffiliateMapper affiliateMapper;

    @BeforeEach
    public void setUp() {
        affiliateMapper = new AffiliateMapper();
    }

    @Test
    void testToEntityFromModel() {
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        AffiliateEntity affiliateEntity = affiliateMapper.toEntityFromModel(affiliate);

        assertEquals(affiliate.getId(), affiliateEntity.getId());
        assertEquals(affiliate.getName(), affiliateEntity.getName());
        assertEquals(affiliate.getLastname(), affiliateEntity.getLastname());
        assertEquals(affiliate.getEmail(), affiliateEntity.getEmail());
        assertEquals(affiliate.getPhone(), affiliateEntity.getPhone());
        assertEquals(affiliate.getAge(), affiliateEntity.getAge());
    }

    @Test
    void testToModelFromEntity() {
        AffiliateEntity affiliateEntity = AffiliateEntity.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        Affiliate affiliate = affiliateMapper.toModelFromEntity(affiliateEntity);

        assertEquals(affiliateEntity.getId(), affiliate.getId());
        assertEquals(affiliateEntity.getName(), affiliate.getName());
        assertEquals(affiliateEntity.getLastname(), affiliate.getLastname());
        assertEquals(affiliateEntity.getEmail(), affiliate.getEmail());
        assertEquals(affiliateEntity.getPhone(), affiliate.getPhone());
        assertEquals(affiliateEntity.getAge(), affiliate.getAge());
    }

    @Test
    void testToModelFromRequestDto() {
        AffiliateRequestDto affiliateRequestDto = AffiliateRequestDto.builder()
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        Affiliate affiliate = affiliateMapper.toModelFromRequestDto(affiliateRequestDto);

        assertEquals(affiliateRequestDto.getName(), affiliate.getName());
        assertEquals(affiliateRequestDto.getLastname(), affiliate.getLastname());
        assertEquals(affiliateRequestDto.getEmail(), affiliate.getEmail());
        assertEquals(affiliateRequestDto.getPhone(), affiliate.getPhone());
        assertEquals(affiliateRequestDto.getAge(), affiliate.getAge());
    }

    @Test
    void testToResponseDtoFromModel() {
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        AffiliateResponseDto affiliateResponseDto = affiliateMapper.toResponseDtoFromModel(affiliate);

        assertEquals(affiliate.getId(), affiliateResponseDto.getId());
        assertEquals(affiliate.getName(), affiliateResponseDto.getName());
        assertEquals(affiliate.getLastname(), affiliateResponseDto.getLastname());
        assertEquals(affiliate.getEmail(), affiliateResponseDto.getEmail());
        assertEquals(affiliate.getPhone(), affiliateResponseDto.getPhone());
        assertEquals(affiliate.getAge(), affiliateResponseDto.getAge());
    }
}
