
package raisetech.studentmanagement;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentRepository {

  @Select("""
    SELECT
        id,
        student_id AS studentId,
        course_name AS courseName,
        course_start_at AS courseStartAt,
        course_end_at AS courseEndAt
    FROM students_courses
    """)
  List<Student> search();
}