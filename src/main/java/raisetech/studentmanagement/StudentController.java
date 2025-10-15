package raisetech.studentmanagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  private final StudentRepository repository;

  public StudentController(StudentRepository repository) {
    this.repository = repository;
  }


@GetMapping("/studentList")
public List<Student> getStudentList() {
  return repository.search();
}}


