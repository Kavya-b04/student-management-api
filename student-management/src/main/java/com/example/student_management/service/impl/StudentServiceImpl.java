package com.example.student_management.service.impl;

import com.example.student_management.dto.StudentRequestDTO;
import com.example.student_management.dto.StudentResponseDTO;
import com.example.student_management.entity.Student;
import com.example.student_management.exception.ResourceNotFoundException;
import com.example.student_management.mapper.StudentMapper;
import com.example.student_management.repository.StudentRepository;
import com.example.student_management.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO dto){
        if(studentRepository.existsByEmail(dto.getEmail())){
            throw new IllegalArgumentException("Email already exists" + dto.getEmail());
        }
        Student student = studentMapper.toEntity(dto);
        Student saved = studentRepository.save(student);
        return studentMapper.toResponseDTO(saved);
    }

    @Override
    public StudentResponseDTO getStudentByID(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + id));
        return studentMapper.toResponseDTO(student);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public StudentResponseDTO updateStudent(Long id,StudentRequestDTO dto){
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student not found with id "+id));
        studentMapper.updateEntityFromDTO(dto,student);
        Student updated = studentRepository.save(student);
        return studentMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteStudent(Long id){
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student not found with id: " + id);

        }
        studentRepository.deleteById(id);
    }

}
