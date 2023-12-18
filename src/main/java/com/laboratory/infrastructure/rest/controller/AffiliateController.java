package com.laboratory.infrastructure.rest.controller;

import com.laboratory.application.usecase.AffiliateUseCase;
import com.laboratory.domain.model.Affiliate;
import com.laboratory.infrastructure.adapters.mapper.AffiliateMapper;
import com.laboratory.infrastructure.rest.dto.AffiliateRequestDto;
import com.laboratory.infrastructure.rest.dto.AffiliateResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/affiliate")
@AllArgsConstructor
public class AffiliateController {

    AffiliateUseCase affiliateUseCase;
    AffiliateMapper affiliateMapper;

    @PostMapping
    public ResponseEntity<AffiliateResponseDto> save(@Valid @RequestBody AffiliateRequestDto affiliateRequestDto){
        Affiliate affiliate = affiliateMapper.toModelFromRequestDto(affiliateRequestDto);
        AffiliateResponseDto affiliateResponseDto = affiliateUseCase.save(affiliate);
        return ResponseEntity.status(HttpStatus.CREATED).body(affiliateResponseDto);
    }

    @GetMapping()
    public ResponseEntity<List<AffiliateResponseDto>> findAll(){
        List<AffiliateResponseDto> affiliates = affiliateUseCase.findAll();
        return ResponseEntity.ok(affiliates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AffiliateResponseDto> findById(@PathVariable Integer id){
        AffiliateResponseDto affiliateResponseDto = affiliateUseCase.findById(id);
        return ResponseEntity.ok(affiliateResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AffiliateResponseDto> update(@PathVariable Integer id, @Valid @RequestBody AffiliateRequestDto affiliateRequestDto){
        AffiliateResponseDto affiliateUpdate = affiliateUseCase.update(id, affiliateRequestDto);
        return ResponseEntity.ok(affiliateUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        affiliateUseCase.delete(id);
        return ResponseEntity.ok("Affilliate deleted");
    }
}
