package raisetech.StudentManagement;


import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {

  private String id;
  private String studentId;
  private String courseName;
  private LocalDate courseStartAt;
  private LocalDate courseEndAt ;

}
