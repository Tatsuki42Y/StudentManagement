
package raisetech.studentmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentsCourses;
import raisetech.studentmanagement.repository.StudentRepository;

@SpringBootApplication

public class StudentManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
  }


}