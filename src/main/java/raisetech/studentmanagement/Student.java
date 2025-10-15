
package raisetech.studentmanagement;



import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Student {

  private int id;
  private String studentId;
  private String courseName;
  // DBのcourse_start_at, course_end_atカラムはDATE型のため、LocalDateでマッピングしています。
  private LocalDate courseStartAt;
  private LocalDate courseEndAt ;

}
