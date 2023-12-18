package com.laboratory.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExamTest {

    @Test
    void testToString() {
        String exam = Exam.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build().toString();

        String expected = "Exam(id=1, name=Exam name, description=Exam description, appointments=[])";
        assertEquals(expected, exam);
    }

    @Test
    void testHashCode() {
        Exam exam1 = Exam.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        Exam exam2 = Exam.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        assertEquals(exam1.hashCode(), exam2.hashCode());
    }

    @Test
    void testEquals() {
        Exam exam1 = Exam.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        Exam exam2 = Exam.builder()
                .id(1)
                .name("Exam name")
                .description("Exam description")
                .build();

        assertEquals(exam1, exam2);
    }
}
