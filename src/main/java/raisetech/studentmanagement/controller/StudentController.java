package raisetech.studentmanagement.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.studentmanagement.domain.StudentDetail;
import raisetech.studentmanagement.exception.TestException;
import raisetech.studentmanagement.service.StudentService;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして受け付けるcontrollerです。
 */
@Validated
@RestController
public class StudentController {

  private StudentService service;

  /**
   * 受講生詳細の一覧検索です。 全件検索を行うので、条件指定は行いません。
   *
   * @return　受講生詳細一覧（全件）
   */

  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生詳細の一覧検索です。全件検索を行うので、条件指定は行いません。
   *
   * @return 受講生詳細一覧（全件）
   */
@Operation(summary = "一覧検索", description = "受講生の一覧を検索します。")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生詳細の検索です。 IDに紐づく任意の受講生の情報を取得します。
   *
   * @param id 受講生ID
   * @return 受講生
   */
  @Operation(summary = "受講生ID検索", description = "受講生をIDで検索します。")
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String id) {
    return service.searchStudent(id);
  }

  /**
   * 受講生詳細の登録を行います
   *
   * @param studentDetail
   * @return 実行結果
   */
  @Operation(summary = "受講生登録", description = "受講生を登録します。")
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(
      @RequestBody @Valid StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行います。キャンセルフラグの更新もここで行います（論理削除）
   *
   * @param studentDetail 受講生詳細
   * @return 実行結果
   */
  @Operation(summary = "受講生情報更新", description = "受講生の情報を更新します。")
  @PostMapping("/updateStudent")
  public ResponseEntity<Void> updateStudent(@RequestBody String body) {
    service.registerStudent(null);
    return ResponseEntity.ok().build();
  }

  /**
   * 受講生コース状況の状況更新を行います。
   *
   * @param studentDetail 受講生詳細
   * @return 実行結果
   */
  @Operation(summary = "受講生コース状況更新", description = "受講生の情報を更新します。")
  @PostMapping("/updateCourseStatus")
  public ResponseEntity<Void> updateCourseStatus(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateCourseStatus(studentDetail);
    return ResponseEntity.ok().build();
  }


  @Operation(summary = "テストエラー発生用クラス", description = "エラーを発生させるクラス")
  @GetMapping("/errorTest")
  public List<StudentDetail> errorTest() throws TestException {
    throw new TestException("エラーが発生しました");
  }

  @GetMapping("/exception")
  public ResponseEntity<String> exception() {
    return ResponseEntity
        .badRequest()
        .body("このAPIhは現在利用できません。古いURLとなっています。");
  }
}
