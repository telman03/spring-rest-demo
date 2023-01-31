package app.demo.service;

import app.demo.rest.model.dto.StudentDto;
import app.demo.rest.model.response.StudentResponse;

public interface StudentService {
    StudentResponse getAllStudents();
    StudentDto getStudent(Integer id);
    StudentResponse getByNameAndLastname(String name, String lastname);
    void insert(StudentDto studentDto);
    void updateAll(StudentDto studentDto, Integer id);


    void updateSome(StudentDto studentDto, Integer id);
}
