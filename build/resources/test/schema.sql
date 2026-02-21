CREATE TABLE IF NOT EXISTS students
 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name varchar(50) NOT NULL,
  kana_name varchar(50) NOT NULL,
  nickname varchar(50) ,
  email varchar(50) NOT NULL,
  area varchar(50),
  age int,
  sex varchar(10),
  remark TEXT,
  isDeleted boolean
);

CREATE TABLE IF NOT EXISTS students_courses (
  id INT AUTO_INCREMENT PRIMARY KEY,
  student_id INT NOT NULL,
  course_name VARCHAR(50) NOT NULL,
  course_start_at TIMESTAMP,
  course_end_at TIMESTAMP
);

