
package raisetech.studentmanagement;



import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {

  private int id;
  private String studentId;
  private String courseName;
  private LocalDate courseStartAt;
  private LocalDate courseEndAt ;

}
