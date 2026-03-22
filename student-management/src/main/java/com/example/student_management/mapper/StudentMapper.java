package com.example.student_management.mapper;

import com.example.student_management.dto.StudentRequestDTO;
import com.example.student_management.entity.Student;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import com.example.student_management.dto.StudentResponseDTO;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    //requestDto -> Entity(for Create)
    Student toEntity(StudentRequestDTO dto);

    //Entity -> responseDTo (for APi response)
    StudentResponseDTO toResponseDTO(Student student);

    //update existing entity from dto (for update)
    //NULL fields in dto are ignored -exixting values kept
    @BeanMapping(nullValuePropertyMappingStrategy =   NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(StudentRequestDTO dto, @MappingTarget Student student);



}
