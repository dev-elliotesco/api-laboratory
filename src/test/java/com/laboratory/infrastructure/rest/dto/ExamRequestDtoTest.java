package com.laboratory.infrastructure.rest.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExamRequestDtoTest {

    @Test
    void testSetMethods() {
        ExamRequestDto examRequestDto = ExamRequestDto.builder()
                .name("Exam name")
                .description("Exam description")
                .build();

        examRequestDto.setName("New Exam name");
        examRequestDto.setDescription("New Exam description");

        assertEquals("New Exam name", examRequestDto.getName());
        assertEquals("New Exam description", examRequestDto.getDescription());
    }

    @Test
    void testToString() {
        ExamRequestDto examRequestDto = ExamRequestDto.builder()
                .name("Exam name")
                .description("Exam description")
                .build();

        String expected = "ExamRequestDto(name=Exam name, description=Exam description)";

        assertEquals(expected, examRequestDto.toString());
    }

    @Test
    void testHashCode() {
        ExamRequestDto examRequestDto1 = ExamRequestDto.builder()
                .name("Exam name")
                .description("Exam description")
                .build();

        ExamRequestDto examRequestDto2 = ExamRequestDto.builder()
                .name("Exam name")
                .description("Exam description")
                .build();

        assertEquals(examRequestDto1.hashCode(), examRequestDto2.hashCode());
    }

    @Test
    void testEquals() {
        ExamRequestDto examRequestDto1 = ExamRequestDto.builder()
                .name("Exam name")
                .description("Exam description")
                .build();

        ExamRequestDto examRequestDto2 = ExamRequestDto.builder()
                .name("Exam name")
                .description("Exam description")
                .build();

        assertEquals(examRequestDto1, examRequestDto2);
    }
}
