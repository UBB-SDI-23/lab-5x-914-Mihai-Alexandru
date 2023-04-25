package dev.alexmihai.universitycourses;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniversityCoursesApplication {
    @Bean  // This annotation is used to inject the dependency - the ModelMapper object
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(UniversityCoursesApplication.class, args);
    }
}
