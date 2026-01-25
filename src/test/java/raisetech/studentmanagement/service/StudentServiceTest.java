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
import raisetech.studentmanagement.data.CourseStatus;
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
    List<CourseStatus> courseStatusList = new ArrayList<>();

    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);
    when(repository.searchCourseStatusList()).thenReturn(courseStatusList);

    sut.searchStudentList();

    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(repository, times(1)).searchCourseStatusList();
    verify(converter, times(1)).convertStudentDetails(studentList, studentCourseList, courseStatusList);
  }

  @Test
  void 受講生詳細検索_正常に取得できること() {
    Student student = new Student();
    student.setId("1");
    student.setName("山田太郎");

    List<StudentCourse> courseList = new ArrayList<>();

    when(repository.searchStudent("1")).thenReturn(student);
    when(repository.searchStudentCourse("1")).thenReturn(courseList);

    StudentDetail actual = sut.searchStudent("1");

    verify(repository, times(1)).searchStudent("1");
    verify(repository, times(1)).searchStudentCourse("1");

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


    StudentCourse course1 = new StudentCourse();
    StudentCourse course2 = new StudentCourse();

    List<StudentCourse> courseList = new ArrayList<>();
    courseList.add(course1);
    courseList.add(course2);

    List<CourseStatus> courseStatusList = new ArrayList<>();

    StudentDetail detail = new StudentDetail(student, courseList, courseStatusList);

    StudentDetail result = sut.registerStudent(detail);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(2)).registerStudentCourse(Mockito.any(StudentCourse.class));

    assertSame(detail, result);
  }

  @Test
  void 受講生情報更新_受講生と受講生コースが更新されること() {
    Student student = new Student();

    StudentCourse course1 = new StudentCourse();
    StudentCourse course2 = new StudentCourse();

    List<StudentCourse> courseList = new ArrayList<>();
    courseList.add(course1);
    courseList.add(course2);

    List<CourseStatus> courseStatusList = new ArrayList<>();

    StudentDetail detail = new StudentDetail(student, courseList, courseStatusList);

    sut.updateStudent(detail);

    verify(repository, times(1)).updateStudent(student);
    verify(repository, times(2)).updateStudentCourse(Mockito.any(StudentCourse.class));
  }

  @Test
  void 受講生コース状況更新_状況が更新されること() {
    Student student = new Student();

    List<StudentCourse> courseList = new ArrayList<>();

    CourseStatus status1 = new CourseStatus();

    List<CourseStatus> courseStatusList = new ArrayList<>();
    courseStatusList.add(status1);

    StudentDetail detail = new StudentDetail(student, courseList, courseStatusList);

    sut.updateCourseStatus(detail);


    verify(repository, times(1)).updateCourseStatus(Mockito.any(CourseStatus.class));
  }

}