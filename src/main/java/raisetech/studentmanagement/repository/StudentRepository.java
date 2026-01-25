package raisetech.studentmanagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.studentmanagement.data.CourseStatus;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentCourse;

/**
 * 受講生コーステーブルと受講生コース情報テーブルと紐づくrepositoryです。
 */

@Mapper
public interface StudentRepository {

  /**
   * 受講生の全件検索を行います。
   *
   * @return　受講生一覧（全件）
   */
  List<Student> search();

  /**
   * 受講生の検索を行います。
   *
   * @param id 受講生ID
   * @return 受講生
   */
  Student searchStudent(String id);

  /**
   * 受講生のコース情報の全件検索を行います。
   *
   * @return 受講生のコース情報(全件)
   */
  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchStudentCourseList();

  /**
   * 受講生のコース状況の全件検索を行います。
   *
   * @return 受講生のコース状況(全件)
   */
  @Select("SELECT * FROM course_status")
  List<CourseStatus> searchCourseStatusList();

  /**
   * 受講生に紐づく受講生コース情報を検索します。
   *
   * @param id 受講生ID
   * @return 受講生IDに紐づく受講生コース情報
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{student_id}")
  List<StudentCourse> searchStudentCourse(String id);

  /**
   * 受講生コースに紐づく受講生コース状況を検索します。
   *
   * @param studentId 受講生コースID
   * @return 受講生コースIDに紐づく受講生コース状況
   */
  @Select("""
        SELECT cs.*
        FROM course_status cs
        JOIN students_courses sc
          ON cs.course_id = sc.id
        WHERE sc.student_id = #{studentId}
      """)
  List<CourseStatus> searchCourseStatusByStudentId(String studentId);

  /**
   * 受講生を新規登録します。IDに関しては自動採番を行う。
   *
   * @param student 受講生
   */
  void registerStudent(Student student);

  /**
   * 受講生コースを新規登録します。IDに関しては自動採番を行う。
   *
   * @param studentCourse 受講生コース情報
   */
  void registerStudentCourse(StudentCourse studentCourse);

  /**
   * 受講生を更新します。
   *
   * @param student 受講生
   */
  void updateStudent(Student student);

  /**
   * 受講生コース情報のコース名を更新します。
   *
   * @param studentCourse 受講生コース情報
   */
  void updateStudentCourse(StudentCourse studentCourse);


  /**
   * 受講生コース状況の状況を更新します。
   *
   * @param courseStatus 受講生コース状況
   */
  void updateCourseStatus(CourseStatus courseStatus);

}