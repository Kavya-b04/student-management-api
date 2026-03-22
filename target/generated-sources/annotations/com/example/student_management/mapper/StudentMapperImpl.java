package com.example.student_management.mapper;

import com.example.student_management.dto.StudentRequestDTO;
import com.example.student_management.dto.StudentResponseDTO;
import com.example.student_management.entity.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-21T20:59:09+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student toEntity(StudentRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Student.StudentBuilder student = Student.builder();

        student.firstName( dto.getFirstName() );
        student.lastName( dto.getLastName() );
        student.email( dto.getEmail() );
        student.phone( dto.getPhone() );
        student.department( dto.getDepartment() );

        return student.build();
    }

    @Override
    public StudentResponseDTO toResponseDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentResponseDTO.StudentResponseDTOBuilder studentResponseDTO = StudentResponseDTO.builder();

        studentResponseDTO.id( student.getId() );
        studentResponseDTO.firstName( student.getFirstName() );
        studentResponseDTO.lastName( student.getLastName() );
        studentResponseDTO.email( student.getEmail() );
        studentResponseDTO.phone( student.getPhone() );
        studentResponseDTO.department( student.getDepartment() );
        studentResponseDTO.createdAt( student.getCreatedAt() );

        return studentResponseDTO.build();
    }

    @Override
    public void updateEntityFromDTO(StudentRequestDTO dto, Student student) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getFirstName() != null ) {
            student.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            student.setLastName( dto.getLastName() );
        }
        if ( dto.getEmail() != null ) {
            student.setEmail( dto.getEmail() );
        }
        if ( dto.getPhone() != null ) {
            student.setPhone( dto.getPhone() );
        }
        if ( dto.getDepartment() != null ) {
            student.setDepartment( dto.getDepartment() );
        }
    }
}
