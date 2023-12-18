package com.laboratory.application.usecase;

import com.laboratory.domain.model.Affiliate;
import com.laboratory.domain.port.AffiliateRepository;
import com.laboratory.infrastructure.adapters.mapper.AffiliateMapper;
import com.laboratory.infrastructure.rest.dto.AffiliateRequestDto;
import com.laboratory.infrastructure.rest.dto.AffiliateResponseDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AffiliateUseCase {

    private final AffiliateRepository affiliateRepository;
    private final AffiliateMapper affiliateMapper;

    public AffiliateResponseDto save(Affiliate affiliate) {
        Affiliate affiliateSaved = affiliateRepository.save(affiliate);
        return affiliateMapper.toResponseDtoFromModel(affiliateSaved);
    }

    public List<AffiliateResponseDto> findAll() {
        List<Affiliate> affiliates = affiliateRepository.findAll();
        return affiliates.stream()
                .map(affiliateMapper::toResponseDtoFromModel)
                .toList();
    }


    public AffiliateResponseDto findById(Integer id) {
        Affiliate affiliate = affiliateRepository.findById(id);
        return affiliateMapper.toResponseDtoFromModel(affiliate);
    }

    public AffiliateResponseDto update(Integer id, AffiliateRequestDto affiliateRequestDto) {
        Affiliate existingAffiliate = affiliateRepository.findById(id);
        existingAffiliate.setName(affiliateRequestDto.getName());
        existingAffiliate.setLastname(affiliateRequestDto.getLastname());
        existingAffiliate.setEmail(affiliateRequestDto.getEmail());
        existingAffiliate.setPhone(affiliateRequestDto.getPhone());
        existingAffiliate.setAge(affiliateRequestDto.getAge());
        Affiliate affiliate = affiliateRepository.save(existingAffiliate);
        return affiliateMapper.toResponseDtoFromModel(affiliate);
    }

    public void delete(Integer id) {
        if (!affiliateRepository.existsById(id)) {
            throw new EntityNotFoundException("Affiliate not found with id: " + id);
        }
        affiliateRepository.delete(id);
    }

}
