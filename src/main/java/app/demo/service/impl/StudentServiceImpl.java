package app.demo.service.impl;

import app.demo.enums.ErrorCodeEnum;
import app.demo.exception.CustomRestException;
import app.demo.model.Student;
import app.demo.repository.StudentRepository;
import app.demo.rest.model.dto.StudentDto;
import app.demo.rest.model.response.StudentResponse;
import app.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {


    private final StudentRepository studentRepository;
    @Override
    public StudentResponse getAllStudents() {
        List<StudentDto> studentDtoList = studentRepository.findAll().stream()
                .map(student -> convertToDto(student))
                .collect(Collectors.toList());


        return makeStudentResponse(studentDtoList);

    }

    @Override
    public StudentDto getStudent(Integer id) {
        return studentRepository.findById(Long.valueOf(id))
                .map(student -> convertToDto(student))
                .orElseThrow(()->new CustomRestException(ErrorCodeEnum.STUDENT_NOT_FOUND.getMessage()));
    }

    @Override
    public StudentResponse getByNameAndLastname(String name, String lastname) {
        List<StudentDto> students = studentRepository.findByNameAndLastname(name, lastname)
                .stream().map(student -> convertToDto(student))
                .collect(Collectors.toList());


        return makeStudentResponse(students);
    }

    @Override
    public void insert(StudentDto studentDto) {
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        studentRepository.save(student);
    }

    @Override
    public void updateAll(StudentDto studentDto, Integer id) {
        Student student = getStudentById(id);

        student.setName(studentDto.getName());
        student.setLastname(studentDto.getLastname());
        student.setMajor(studentDto.getMajor());
        studentRepository.save(student);
    }

    @Override
    public void updateSome(StudentDto studentDto, Integer id) {
        Student student = getStudentById(id);
        student.setName(studentDto.getName());
        student.setLastname(studentDto.getLastname());
        student.setMajor(studentDto.getMajor());
        studentRepository.save(student);
    }

    private Student getStudentById(Integer id){
        return studentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new CustomRestException(ErrorCodeEnum.STUDENT_NOT_FOUND.getMessage()));

    }


    private StudentDto convertToDto(Student student){
//        return StudentDto.builder()
//                .id(student.getId())
//                .name(student.getName())
//                .lastname(student.getLastname())
//                .major(student.getMajor())
//                .build();

        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student,studentDto);
        return studentDto;
    }

    private StudentResponse makeStudentResponse(List<StudentDto> students){
        return StudentResponse.builder()
                .students(students)
                .build();
    }
}
