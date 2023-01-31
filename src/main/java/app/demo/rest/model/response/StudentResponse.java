package app.demo.rest.model.response;

import app.demo.rest.model.dto.StudentDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private List<StudentDto> students;
}
