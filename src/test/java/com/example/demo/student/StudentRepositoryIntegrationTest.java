package com.example.demo.student;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static java.time.Month.JANUARY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class StudentRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void whenFindStudentByEmail_thenReturnStudent() {
        // given
        Student emre = new Student(
                "Emre",
                "emre.erkmen@hotmail.com",
                LocalDate.of(2000, JANUARY, 5)
        );
        entityManager.persist(emre);
        entityManager.flush();

        // when
        Student found = studentRepository.findStudentByEmailClass(emre.getEmail());

        // then
        assertThat(found.getName())
                .isEqualTo(emre.getName());
    }
}