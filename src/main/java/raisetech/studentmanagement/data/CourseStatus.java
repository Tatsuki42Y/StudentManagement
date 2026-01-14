package raisetech.studentmanagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース状況")
@Getter
@Setter

public class CourseStatus {
  private String id;
  private String courseId;
  private String status;
}
