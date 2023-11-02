package com.company.bookstoreapp.converter;

import com.company.bookstoreapp.model.dto.response.StudentResponse;
import com.company.bookstoreapp.model.entity.Student;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class StudentResponseConverter implements Function<Student, StudentResponse> {
    @Override
    public StudentResponse apply(Student student) {
        return StudentResponse.builder()
                .age(student.getAge())
                .name(student.getName())
                .build();
    }
}
