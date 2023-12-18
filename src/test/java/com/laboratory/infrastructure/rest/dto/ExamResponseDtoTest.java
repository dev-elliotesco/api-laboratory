package com.laboratory.infrastructure.rest.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExamResponseDtoTest {

    @Test
    void testSetMethods() {
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        examResponseDto.setId(2);
        examResponseDto.setName("New Exam name");
        examResponseDto.setDescription("New Exam description");

        assertEquals(2, examResponseDto.getId());
        assertEquals("New Exam name", examResponseDto.getName());
        assertEquals("New Exam description", examResponseDto.getDescription());
    }

    @Test
    void testToString() {
        ExamResponseDto examResponseDto = ExamResponseDto.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        String expected = "ExamResponseDto(id=1, name=Exam name, description=Exam description)";

        assertEquals(expected, examResponseDto.toString());
    }

    @Test
    void testHashCode() {
        ExamResponseDto examResponseDto1 = ExamResponseDto.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        ExamResponseDto examResponseDto2 = ExamResponseDto.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        assertEquals(examResponseDto1.hashCode(), examResponseDto2.hashCode());
    }

    @Test
    void testEquals() {
        ExamResponseDto examResponseDto1 = ExamResponseDto.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        ExamResponseDto examResponseDto2 = ExamResponseDto.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        assertEquals(examResponseDto1, examResponseDto2);
    }
}
