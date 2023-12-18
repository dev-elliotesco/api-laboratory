package com.laboratory.infrastructure.adapters.repository;

import com.laboratory.domain.model.Affiliate;
import com.laboratory.infrastructure.adapters.entity.AffiliateEntity;
import com.laboratory.infrastructure.adapters.mapper.AffiliateMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AffiliateEntityRepositoryAdapterTest {

    @InjectMocks
    private AffiliateEntityRepositoryAdapter affiliateEntityRepositoryAdapter;

    @Mock
    private AffiliateEntityRepository affiliateEntityRepository;

    @Mock
    private AffiliateMapper affiliateMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        AffiliateEntity affiliateEntity = AffiliateEntity.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        when(affiliateMapper.toEntityFromModel(affiliate)).thenReturn(affiliateEntity);
        when(affiliateEntityRepository.save(affiliateEntity)).thenReturn(affiliateEntity);
        when(affiliateMapper.toModelFromEntity(affiliateEntity)).thenReturn(affiliate);

        Affiliate result = affiliateEntityRepositoryAdapter.save(affiliate);

        verify(affiliateEntityRepository, times(1)).save(affiliateEntity);
        assertEquals(affiliate, result);
    }

    @Test
    void testFindById(){
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        AffiliateEntity affiliateEntity = AffiliateEntity.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        when(affiliateEntityRepository.findById(1)).thenReturn(java.util.Optional.of(affiliateEntity));
        when(affiliateMapper.toModelFromEntity(affiliateEntity)).thenReturn(affiliate);

        Affiliate result = affiliateEntityRepositoryAdapter.findById(1);

        verify(affiliateEntityRepository, times(1)).findById(1);
        assertEquals(affiliate, result);
    }

    @Test
    void testFindByIdThrowsException() {
        when(affiliateEntityRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> affiliateEntityRepositoryAdapter.findById(1));

        verify(affiliateEntityRepository, times(1)).findById(1);
    }

    @Test
    void testFindAll(){
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();
        AffiliateEntity affiliateEntity = AffiliateEntity.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        when(affiliateEntityRepository.findAll()).thenReturn(List.of(affiliateEntity));
        when(affiliateMapper.toModelFromEntity(affiliateEntity)).thenReturn(affiliate);

        List<Affiliate> result = affiliateEntityRepositoryAdapter.findAll();

        verify(affiliateEntityRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals(List.of(affiliate), result);
    }

    @Test
    void testDelete() {
        Integer id = 1;

        affiliateEntityRepositoryAdapter.delete(id);

        verify(affiliateEntityRepository, times(1)).deleteById(1);
    }

    @Test
    void testExistsById(){
        Integer id = 1;

        when(affiliateEntityRepository.existsById(id)).thenReturn(true);

        boolean result = affiliateEntityRepositoryAdapter.existsById(id);

        verify(affiliateEntityRepository, times(1)).existsById(id);

        assertTrue(result);
    }
}
