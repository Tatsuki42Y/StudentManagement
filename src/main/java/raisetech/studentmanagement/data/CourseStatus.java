package raisetech.studentmanagement.data;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース状況")
@Getter
@Setter

public class CourseStatus {
  private String id;
  private String courseId;

  @Pattern(regexp = "仮申込|本申込|受講中|受講終了", message = "statusは 仮申込・本申込・受講中・受講終了 のいずれかを指定してください"
  )
  private String status;
}
