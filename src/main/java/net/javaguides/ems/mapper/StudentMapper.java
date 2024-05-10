package net.javaguides.ems.mapper;

import net.javaguides.ems.dto.StudentDto;
import net.javaguides.ems.entity.Student;

import java.util.Locale;

public class StudentMapper {
    public static StudentDto mapToStudentDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGpa(),
                student.getCredits()
        );
    }

    public static Student mapToStudent(StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail(),
                studentDto.getGpa(),
                studentDto.getCredits()
        );
    }
}
