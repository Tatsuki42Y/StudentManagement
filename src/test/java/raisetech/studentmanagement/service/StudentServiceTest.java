package raisetech.studentmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.studentmanagement.controller.converter.StudentConverter;
import raisetech.studentmanagement.data.Student;
import raisetech.studentmanagement.data.StudentCourse;
import raisetech.studentmanagement.domain.StudentDetail;
import raisetech.studentmanagement.exception.TestException;
import raisetech.studentmanagement.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;
  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細一覧検索_リポジトリとコンバーターの処理が適切に呼び出せていること() {

    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();
    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

    sut.searchStudentList();

    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList);
  }

  @Test
  void 受講生詳細検索_正常に取得できること() {
    Student student = new Student();
    student.setId("6");
    student.setName("鈴木一郎");

    List<StudentCourse> courseList = new ArrayList<>();

    when(repository.searchStudent("6")).thenReturn(student);
    when(repository.searchStudentCourse("6")).thenReturn(courseList);

    StudentDetail actual = sut.searchStudent("6");

    verify(repository, times(1)).searchStudent("6");
    verify(repository, times(1)).searchStudentCourse("6");

    assertNotNull(actual);
    assertEquals(student, actual.getStudent());
    assertEquals(courseList, actual.getStudentCourseList());
  }

  @Test
  void 受講生詳細検索_IDが存在しない場合に例外が投げられること() {
    when(repository.searchStudent("999")).thenReturn(null);

    assertThrows(TestException.class, () -> sut.searchStudent("999"));

    verify(repository, times(1)).searchStudent("999");
  }


  @Test
  void 受講生情報登録_受講生と受講生コースが登録されること() {

    Student student = new Student();
    student.setId("10");
    student.setName("前田健太");

    StudentCourse course1 = new StudentCourse();
    StudentCourse course2 = new StudentCourse();

    List<StudentCourse> courseList = new ArrayList<>();
    courseList.add(course1);
    courseList.add(course2);

    StudentDetail detail = new StudentDetail(student, courseList);

    StudentDetail result = sut.registerStudent(detail);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(2)).registerStudentCourse(Mockito.any(StudentCourse.class));

    assertSame(detail, result);
  }

  @Test
  void 受講生情報更新_受講生と受講生コースが更新されること() {
    // given
    Student student = new Student();
    student.setId("11");
    student.setName("ダルビッシュ有");

    StudentCourse course1 = new StudentCourse();
    StudentCourse course2 = new StudentCourse();

    List<StudentCourse> courseList = new ArrayList<>();
    courseList.add(course1);
    courseList.add(course2);

    StudentDetail detail = new StudentDetail(student, courseList);

    sut.updateStudent(detail);

    verify(repository, times(1)).updateStudent(student);
    verify(repository, times(2)).updateStudentCourse(Mockito.any(StudentCourse.class));
  }

}