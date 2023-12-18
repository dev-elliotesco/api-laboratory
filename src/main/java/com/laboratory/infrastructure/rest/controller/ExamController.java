package com.laboratory.infrastructure.rest.controller;

import com.laboratory.application.usecase.ExamUseCase;
import com.laboratory.domain.model.Exam;
import com.laboratory.infrastructure.adapters.mapper.ExamMapper;
import com.laboratory.infrastructure.rest.dto.ExamRequestDto;
import com.laboratory.infrastructure.rest.dto.ExamResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
@AllArgsConstructor
public class ExamController {

    private final ExamUseCase examUseCase;
    private final ExamMapper examMapper;

    @PostMapping
    public ResponseEntity<ExamResponseDto> save(@Valid @RequestBody ExamRequestDto examRequestDto){
        Exam exam = examMapper.toModelFromRequestDto(examRequestDto);
        ExamResponseDto examSaved = examUseCase.save(exam);
        return ResponseEntity.status(HttpStatus.CREATED).body(examSaved);
    }

    @GetMapping()
    public ResponseEntity<List<ExamResponseDto> > findAll(){
        List<ExamResponseDto> tests = examUseCase.findAll();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResponseDto> findById(@PathVariable Integer id){
        ExamResponseDto test = examUseCase.findById(id);
        return ResponseEntity.ok(test);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamResponseDto> update(@PathVariable Integer id, @Valid @RequestBody ExamRequestDto examRequestDto){
        ExamResponseDto examUpdated = examUseCase.update(id, examRequestDto);
        return ResponseEntity.ok(examUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        examUseCase.delete(id);
        return ResponseEntity.ok("Test deleted");
    }
}
