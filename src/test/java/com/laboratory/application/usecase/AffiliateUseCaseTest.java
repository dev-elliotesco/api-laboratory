package com.laboratory.application.usecase;

import com.laboratory.domain.model.Affiliate;
import com.laboratory.domain.port.AffiliateRepository;
import com.laboratory.infrastructure.adapters.mapper.AffiliateMapper;
import com.laboratory.infrastructure.rest.dto.AffiliateRequestDto;
import com.laboratory.infrastructure.rest.dto.AffiliateResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AffiliateUseCaseTest {

    @InjectMocks
    private AffiliateUseCase affiliateUseCase;

    @Mock
    private AffiliateRepository affiliateRepository;

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
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        when(affiliateRepository.save(any(Affiliate.class))).thenReturn(affiliate);
        when(affiliateMapper.toResponseDtoFromModel(any(Affiliate.class))).thenReturn(affiliateResponseDto);

        AffiliateResponseDto result = affiliateUseCase.save(affiliate);

        verify(affiliateRepository, times(1)).save(affiliate);
        verify(affiliateMapper, times(1)).toResponseDtoFromModel(affiliate);

        assertEquals(affiliateResponseDto, result);
    }

    @Test
    void testFindAll() {
        List<Affiliate> affiliateList = Collections.singletonList(Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build());
        List<AffiliateResponseDto> affiliateResponseDtoList = Collections.singletonList(AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build());

        when(affiliateRepository.findAll()).thenReturn(affiliateList);
        when(affiliateMapper.toResponseDtoFromModel(any(Affiliate.class))).thenReturn(affiliateResponseDtoList.get(0));

        List<AffiliateResponseDto> result = affiliateUseCase.findAll();

        verify(affiliateRepository, times(1)).findAll();
        verify(affiliateMapper, times(affiliateList.size())).toResponseDtoFromModel(any(Affiliate.class));

        assertEquals(affiliateResponseDtoList, result);
    }

    @Test
    void testFindById() {

        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(30)
                .build();

        when(affiliateRepository.findById(any(Integer.class))).thenReturn(affiliate);
        when(affiliateMapper.toResponseDtoFromModel(any(Affiliate.class))).thenReturn(affiliateResponseDto);

        AffiliateResponseDto result = affiliateUseCase.findById(1);

        verify(affiliateRepository, times(1)).findById(1);
        verify(affiliateMapper, times(1)).toResponseDtoFromModel(affiliate);

        assertEquals(affiliateResponseDto, result);
    }

    @Test
    void testUpdate() {
        Affiliate existingAffiliate = Affiliate.builder()
                .id(1)
                .name("Existing name")
                .lastname("Existing lastname")
                .email("Existing email")
                .phone("Existing phone")
                .age(30)
                .build();
        AffiliateRequestDto affiliateRequestDto = AffiliateRequestDto.builder()
                .name("New name")
                .lastname("New lastname")
                .email("New email")
                .phone("New phone")
                .age(30)
                .build();
        Affiliate updatedAffiliate = Affiliate.builder()
                .id(1)
                .name(affiliateRequestDto.getName())
                .lastname(affiliateRequestDto.getLastname())
                .email(affiliateRequestDto.getEmail())
                .phone(affiliateRequestDto.getPhone())
                .age(affiliateRequestDto.getAge())
                .build();
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .name(affiliateRequestDto.getName())
                .lastname(affiliateRequestDto.getLastname())
                .email(affiliateRequestDto.getEmail())
                .phone(affiliateRequestDto.getPhone())
                .age(affiliateRequestDto.getAge())
                .build();

        when(affiliateRepository.findById(any(Integer.class))).thenReturn(existingAffiliate);
        when(affiliateRepository.save(any(Affiliate.class))).thenReturn(updatedAffiliate);
        when(affiliateMapper.toResponseDtoFromModel(any(Affiliate.class))).thenReturn(affiliateResponseDto);

        AffiliateResponseDto result = affiliateUseCase.update(1, affiliateRequestDto);

        verify(affiliateRepository, times(1)).findById(1);
        verify(affiliateRepository, times(1)).save(updatedAffiliate);
        verify(affiliateMapper, times(1)).toResponseDtoFromModel(updatedAffiliate);

        assertEquals(affiliateResponseDto, result);
    }

    @Test
    void testDelete() {
        when(affiliateRepository.existsById(any(Integer.class))).thenReturn(true);

        affiliateUseCase.delete(1);

        verify(affiliateRepository, times(1)).existsById(1);
        verify(affiliateRepository, times(1)).delete(1);
    }
    @Test
     void testDeleteThrowsException() {
        when(affiliateRepository.existsById(any(Integer.class))).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> affiliateUseCase.delete(1));

        verify(affiliateRepository, times(1)).existsById(1);
    }


}
