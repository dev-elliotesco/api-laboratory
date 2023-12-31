package com.laboratory.infrastructure.rest.controller;

import com.laboratory.application.usecase.AffiliateUseCase;
import com.laboratory.domain.model.Affiliate;
import com.laboratory.infrastructure.adapters.mapper.AffiliateMapper;
import com.laboratory.infrastructure.rest.controller.AffiliateController;
import com.laboratory.infrastructure.rest.dto.AffiliateRequestDto;
import com.laboratory.infrastructure.rest.dto.AffiliateResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AffiliateControllerTest {
    @InjectMocks
    private AffiliateController affiliateController;

    @Mock
    private AffiliateUseCase affiliateUseCase;

    @Mock
    private AffiliateMapper affiliateMapper;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(affiliateController).build();
    }

    @Test
    void testSave() throws Exception {
        AffiliateRequestDto affiliateRequestDto = AffiliateRequestDto.builder()
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(20)
                .build();
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(20)
                .build();
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(20)
                .build();

        when(affiliateMapper.toModelFromRequestDto(affiliateRequestDto)).thenReturn(affiliate);
        when(affiliateUseCase.save(affiliate)).thenReturn(affiliateResponseDto);

        String requestBody = "{ \"name\": \"Affiliate name\", \"lastname\": \"Affiliate lastname\", " +
                "\"email\": \"Affiliate email\", \"phone\": \"Affiliate phone\", \"age\": 20 }";

        mockMvc.perform(post("/api/affiliate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andExpect(status().isCreated());
    }

    @Test
    void testFindAll() throws Exception {
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(20)
                .build();
        List<AffiliateResponseDto> affiliateResponseDtoList = List.of(affiliateResponseDto);

        when(affiliateUseCase.findAll()).thenReturn(affiliateResponseDtoList);

        mockMvc.perform(get("/api/affiliate")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(20)
                .build();

        when(affiliateUseCase.findById(1)).thenReturn(affiliateResponseDto);

        mockMvc.perform(get("/api/affiliate/1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        AffiliateRequestDto affiliateRequestDto = AffiliateRequestDto.builder()
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(20)
                .build();
        Affiliate affiliate = Affiliate.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(20)
                .build();
        AffiliateResponseDto affiliateResponseDto = AffiliateResponseDto.builder()
                .id(1)
                .name("Affiliate name")
                .lastname("Affiliate lastname")
                .email("Affiliate email")
                .phone("Affiliate phone")
                .age(20)
                .build();

        when(affiliateMapper.toModelFromRequestDto(affiliateRequestDto)).thenReturn(affiliate);
        when(affiliateUseCase.update(1, affiliateRequestDto)).thenReturn(affiliateResponseDto);

        String requestBody = "{ \"name\": \"Affiliate name\", \"lastname\": \"Affiliate lastname\", " +
                "\"email\": \"Affiliate email\", \"phone\": \"Affiliate phone\", \"age\": 20 }";

        mockMvc.perform(put("/api/affiliate/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andExpect(status().isOk());

        verify(affiliateUseCase, times(1)).update(1, affiliateRequestDto);
    }

    @Test
    void testDelete() throws Exception {
        Integer id = 1;

        doNothing().when(affiliateUseCase).delete(id);

        mockMvc.perform(delete("/api/affiliate/"  + id)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        verify(affiliateUseCase, times(1)).delete(id);
    }
}
