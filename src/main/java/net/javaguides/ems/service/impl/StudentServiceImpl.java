package net.javaguides.ems.service.impl;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import net.javaguides.ems.dto.StudentDto;
import net.javaguides.ems.entity.Student;
import net.javaguides.ems.exception.ResourceNotFoundException;
import net.javaguides.ems.mapper.StudentMapper;
import net.javaguides.ems.repository.StudentRepository;
import net.javaguides.ems.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private StudentRepository studentRepository;

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student is not exist with given id: " + studentId));

        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(StudentMapper::mapToStudentDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student is not exists with given id: " + studentId));
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());;
        student.setEmail(updatedStudent.getEmail());
        student.setGpa(updatedStudent.getGpa());
        student.setCredits(updatedStudent.getCredits());

        Student updatedStudentObj = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updatedStudentObj);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student is not exists with given id: " + studentId));

        studentRepository.deleteById(studentId);
    }
}
