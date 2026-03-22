package com.example.student_management.service;

import com.example.student_management.dto.StudentRequestDTO;
import com.example.student_management.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {
    StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO getStudentByID(Long id);
    List<StudentResponseDTO>  getAllStudents();
    StudentResponseDTO updateStudent(Long id,StudentRequestDTO sto);
    void deleteStudent(Long id);
}
