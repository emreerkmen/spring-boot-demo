package com.example.demo.student;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static java.time.Month.JANUARY;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class StudentServiceImplIntegrationTest {

    @TestConfiguration
    static class StudentServiceImplIntegrationTestContextConfiguration {

        private final StudentRepository  studentRepository = Mockito.mock(StudentRepository.class);

        @Bean
        public StudentService studentService() {
            return new StudentService(studentRepository);
        }
    }

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Before
    public void setUp() {
        Student emre = new Student(
                "Emre",
                "emre.erkmen@hotmail.com",
                LocalDate.of(2000, JANUARY, 5)
        );

        Optional<Student> emreOptional = Optional.of(new Student(
                "Emre",
                "emre.erkmen@hotmail.com",
                LocalDate.of(2000, JANUARY, 5)
        ));

        Mockito.when(studentRepository.findStudentByEmailClass(emre.getEmail()))
                .thenReturn(emre);

        Mockito.when(studentRepository.findById(emre.getId())).thenReturn(emreOptional);
    }

    @Test
    public void whenValidEmail_thenStudentShouldBeSaved() {
        String name = "emre";
        Long id = (long) 1;
        String email = "emre.erkmen2@hotmail.com";

        Student found = studentService.updateStudent(id, name, email);

        assertEquals(found.getEmail(), email);
    }

}
