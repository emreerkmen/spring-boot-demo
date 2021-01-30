package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student emre = new Student(
                    "Emre",
                    "emre.erkmen@hotmail.com",
                    LocalDate.of(2000, JANUARY, 5),
                    21
            );

            Student ozge = new Student(
                    "Özge",
                    "özge.onec@hotmail.com",
                    LocalDate.of(2004, JANUARY, 5),
                    21
            );

            repository.saveAll(List.of(emre,ozge));
        };
    }
}
