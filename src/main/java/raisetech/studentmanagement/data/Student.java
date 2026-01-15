package raisetech.studentmanagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")
@Getter
@Setter

public class Student {

  @Pattern(regexp = "^\\d+$", message = "数字のみ入力するようにしてください。")
  private String id;
  private String name;
  private String kanaName;
  private String nickname;
  @Email
  private String email;
  private String area;
  private int age;
  private String sex;
  private String remark;
  private boolean isDeleted;
}
