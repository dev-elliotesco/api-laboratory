package com.laboratory.infrastructure.adapters.repository;

import com.laboratory.domain.model.Affiliate;
import com.laboratory.domain.port.AffiliateRepository;
import com.laboratory.infrastructure.adapters.entity.AffiliateEntity;
import com.laboratory.infrastructure.adapters.mapper.AffiliateMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AffiliateEntityRepositoryAdapter implements AffiliateRepository {

    private final AffiliateEntityRepository affiliateEntityRepository;
    private final AffiliateMapper affiliateMapper;
    @Override
    public Affiliate save(Affiliate affiliate) {
        AffiliateEntity affiliateEntity = affiliateMapper.toEntityFromModel(affiliate);
        affiliateEntityRepository.save(affiliateEntity);
        return affiliateMapper.toModelFromEntity(affiliateEntity);
    }

    @Override
    public Affiliate findById(Integer id) {
        AffiliateEntity affiliateEntity = affiliateEntityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Affiliate not found with id: " + id));
        return affiliateMapper.toModelFromEntity(affiliateEntity);
    }

    @Override
    public List<Affiliate> findAll() {
        return affiliateEntityRepository.findAll().stream()
                .map(affiliateMapper::toModelFromEntity)
                .toList();
    }

    @Override
    public void delete(Integer id) {
        affiliateEntityRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return affiliateEntityRepository.existsById(id);
    }
}
