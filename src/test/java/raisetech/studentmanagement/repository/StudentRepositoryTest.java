package raisetech.studentmanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.studentmanagement.data.Student;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void 受講生の全件検索が行えること() {
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生の登録が行えること() {
    Student student = new Student();
    student.setName("江並公史");
    student.setKanaName("エナミコウジ");
    student.setNickname("エナミ");
    student.setEmail("test@example.com");
    student.setArea("奈良県");
    student.setAge(36);
    student.setSex("男性");
    student.setRemark("");
    student.setDeleted(false);

    sut.registerStudent(student);

    List<Student> actual = sut.search();

    assertThat(actual.size()).isEqualTo(6);

  }

  @Test
  void 受講生の更新が行えること() {
    // 既存データ取得
    Student student = sut.search().get(0);
    String id = student.getId();

    // 更新
    student.setName("更新後の名前");
    student.setArea("更新後の住所");

    sut.updateStudent(student);

    // 再取得
    Student updated = sut.searchStudent(id);

    assertThat(updated.getName()).isEqualTo("更新後の名前");
    assertThat(updated.getArea()).isEqualTo("更新後の住所");
  }

  @Test
  void 受講生の削除が行えること() {
    Student student = sut.search().get(0);

    student.setDeleted(true);
    sut.updateStudent(student);

    Student deleted = sut.searchStudent(student.getId());
    assertThat(deleted.isDeleted()).isTrue();
  }
}