package com.laboratory.infrastructure.adapters.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExamEntityTest {

    @Test
    void testSetMethods() {
        ExamEntity examEntity = ExamEntity.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        examEntity.setId(2);
        examEntity.setName("New Exam name");
        examEntity.setDescription("New Exam description");

        assertEquals(2, examEntity.getId());
        assertEquals("New Exam name", examEntity.getName());
        assertEquals("New Exam description", examEntity.getDescription());
    }

    @Test
    void testToString() {
        ExamEntity examEntity = ExamEntity.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        String expected = "ExamEntity(id=1, name=Exam name, description=Exam description, appointments=[])";

        assertEquals(expected, examEntity.toString());
    }

    @Test
    void testHashCode() {
        ExamEntity examEntity1 = ExamEntity.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        ExamEntity examEntity2 = ExamEntity.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        assertEquals(examEntity1.hashCode(), examEntity2.hashCode());
    }

    @Test
    void testEquals() {
        ExamEntity examEntity1 = ExamEntity.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        ExamEntity examEntity2 = ExamEntity.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        assertEquals(examEntity1, examEntity2);
    }
}
