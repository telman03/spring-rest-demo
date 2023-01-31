package app.demo.controller;


import app.demo.rest.model.dto.StudentDto;
import app.demo.rest.model.response.StudentResponse;
import app.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public StudentResponse getAllStudents(){
        return studentService.getAllStudents();
    }


    @GetMapping("/{student-id}")
    public StudentDto getStudent(@PathVariable("student-id") Integer id ){
        return studentService.getStudent(id);
    }

    @GetMapping("/search")
    public StudentResponse getStudentByNameAndLastname(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "lastname") String lastname) {

        return studentService.getByNameAndLastname(name, lastname);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody StudentDto studentDto){
        studentService.insert(studentDto);
    }


    @PutMapping("/{id}")
    public void updateAll(@RequestBody StudentDto studentDto, @PathVariable("id") Integer id){
        studentService.updateAll(studentDto,id);
    }

    @PatchMapping("/{id}")
    public void updateSome(@RequestBody StudentDto studentDto, @PathVariable("id") Integer id){
        studentService.updateSome(studentDto,id);
    }
}
