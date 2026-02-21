INSERT INTO students(name, kana_name, nickname, email, area, age, sex)
VALUES('山田太郎', 'ヤマダタロウ', 'タロー', 'taro.yamada@example.com', '東京都', 25, '男性'),
      ('佐藤花子', 'サトウハナコ', 'ハナ', 'hanako.sato@example.com', '神奈川県', 23, '女性'),
      ('鈴木一郎', 'スズキイチロウ', 'イチ', 'ichiro.suzuki@example.com', '大阪府', 30, '男性'),
      ('高橋美咲', 'タカハシミサキ', 'ミサ', 'misaki.takahashi@example.com', '愛知県', 27, '女性'),
      ('伊藤健', 'イトウケン', 'ケン', 'ken.ito@example.com', '福岡県', 22, '男性');

INSERT INTO students_courses(student_id, course_name, course_start_at, course_end_at)
VALUES(1, 'Java', '2025-04-01 09:00:00', '2025-07-01 15:00:00'),
      (2, 'AWS', '2025-05-01 10:00:00', '2025-08-01 16:00:00'),
      (3, 'Java', '2025-04-15 09:30:00', '2025-06-30 14:30:00'),
      (4, 'AWS', '2025-06-01 09:00:00', '2025-09-01 15:00:00'),
      (5, 'Java', '2025-05-10 10:00:00', '2025-08-10 16:00:00');


