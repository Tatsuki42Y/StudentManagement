package raisetech.studentmanagement.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import raisetech.studentmanagement.controller.converter.StudentConverter;
import raisetech.studentmanagement.data.CourseStatus;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentCourse;
import raisetech.studentmanagement.domain.StudentDetail;

class StudentConverterTest {

  @Test
  void コンバーターのテスト() {
    // --- 準備 ---
    StudentConverter converter = new StudentConverter();

    Student student1 = new Student();
    student1.setId("1");
    student1.setName("山田太郎");

    Student student2 = new Student();
    student2.setId("2");
    student2.setName("佐藤花子");

    List<Student> studentList = List.of(student1, student2);

    StudentCourse course1 = new StudentCourse();
    course1.setStudentId("1");
    course1.setCourseName("Java");

    StudentCourse course2 = new StudentCourse();
    course2.setStudentId("1");
    course2.setCourseName("AWS");

    StudentCourse course3 = new StudentCourse();
    course3.setStudentId("2");
    course3.setCourseName("Java");

    List<StudentCourse> studentCourseList = List.of(course1, course2, course3);

    List<CourseStatus> courseStatusList = new ArrayList<>();

    // --- 実行 ---
    List<StudentDetail> result = converter.convertStudentDetails(studentList, studentCourseList, courseStatusList);

    // --- 検証 ---
    assertEquals(2, result.size());

    // student1 の検証
    StudentDetail detail1 = result.get(0);
    assertEquals("1", detail1.getStudent().getId());
    assertEquals(2, detail1.getStudentCourseList().size());

    // student2 の検証
    StudentDetail detail2 = result.get(1);
    assertEquals("2", detail2.getStudent().getId());
    assertEquals(1, detail2.getStudentCourseList().size());
  }
}