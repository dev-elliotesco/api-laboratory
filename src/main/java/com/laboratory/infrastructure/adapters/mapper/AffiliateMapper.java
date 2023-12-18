package com.laboratory.infrastructure.adapters.mapper;

import com.laboratory.domain.model.Affiliate;
import com.laboratory.infrastructure.adapters.entity.AffiliateEntity;
import com.laboratory.infrastructure.rest.dto.AffiliateRequestDto;
import com.laboratory.infrastructure.rest.dto.AffiliateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AffiliateMapper {

    public AffiliateEntity toEntityFromModel(Affiliate affiliate) {
        return AffiliateEntity.builder()
                .id(affiliate.getId())
                .name(affiliate.getName())
                .lastname(affiliate.getLastname())
                .email(affiliate.getEmail())
                .phone(affiliate.getPhone())
                .age(affiliate.getAge())
                .build();
    }

    public Affiliate toModelFromEntity(AffiliateEntity affiliateEntity) {
        return Affiliate.builder()
                .id(affiliateEntity.getId())
                .name(affiliateEntity.getName())
                .lastname(affiliateEntity.getLastname())
                .email(affiliateEntity.getEmail())
                .phone(affiliateEntity.getPhone())
                .age(affiliateEntity.getAge())
                .build();
    }
    public Affiliate toModelFromRequestDto(AffiliateRequestDto affiliateRequestDto) {
        return Affiliate.builder()
                .name(affiliateRequestDto.getName())
                .lastname(affiliateRequestDto.getLastname())
                .email(affiliateRequestDto.getEmail())
                .phone(affiliateRequestDto.getPhone())
                .age(affiliateRequestDto.getAge())
                .build();
    }

    public AffiliateResponseDto toResponseDtoFromModel(Affiliate affiliate) {
        return AffiliateResponseDto.builder()
                .id(affiliate.getId())
                .name(affiliate.getName())
                .lastname(affiliate.getLastname())
                .email(affiliate.getEmail())
                .phone(affiliate.getPhone())
                .age(affiliate.getAge())
                .build();
    }
}
