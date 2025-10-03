package raisetech.studentmanagement;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.Student;

@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students_courses")
  List<Student> search();
}