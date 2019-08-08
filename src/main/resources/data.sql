  insert into roles (id, created_at, deleted_at, updated_at, name) values (1, '2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'ROLE_ADMIN');
  insert into roles (id, created_at, deleted_at, updated_at, name) values (2, '2019-07-14 11:46:01.432630', null, '2019-07-14 11:46:01.432630', 'ROLE_TEACHER');
  insert into roles (id, created_at, deleted_at, updated_at, name) values (3, '2019-07-14 11:46:05.958924', null, '2019-07-14 11:46:05.958924', 'ROLE_STUDENT');
  insert into roles (id, created_at, deleted_at, updated_at, name) values (4, '2019-07-14 11:47:04.925222', null, '2019-07-14 11:47:04.925222', 'ROLE_MANAGER');

  INSERT INTO users (id, created_at, deleted_at, updated_at, first_name, last_name, login, password, phone_number, role_id) VALUES (1, '2019-07-14 18:25:32.856000', null, '2019-07-14 18:25:32.856000', 'admin', 'admin', 'admin', '$2a$10$ucIJJWRSsGpF8LbZZP4GbeYjoKmSN9ADD3W3rA3xNsfJIVaK1Mv2q', '8777-777-77-77', 3);
  INSERT INTO users (id, created_at, deleted_at, updated_at, first_name, last_name, login, password, phone_number, role_id) VALUES (2, '2019-07-14 18:25:32.856000', null, '2019-07-14 18:25:32.856000', 'admin2', 'admin2', 'admin2', '$2a$10$ucIJJWRSsGpF8LbZZP4GbeYjoKmSN9ADD3W3rA3xNsfJIVaK1Mv2q', '8777-777-77-77', 3);


  INSERT INTO courses (id, created_at, deleted_at, updated_at, course_name, description, grade, image, price, teacher_support, user_id)
  values (1,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Математика','Курс математика, прикольно очень',9,'src/koe 4to',500.00,true,1);

 INSERT INTO courses (id, created_at, deleted_at, updated_at, course_name, description, grade, image, price, teacher_support, user_id)
 values (2,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Физика','Курс физика,прикольно очень',10,'src/koe 4to',600.00,true,2);

INSERT INTO lessons (id, created_at, deleted_at, updated_at, lesson_name, ordering, course_id)
 values (1,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Урок 1 математика', 1, 1);

INSERT INTO lessons (id, created_at, deleted_at, updated_at, lesson_name, ordering, course_id)
 values (2,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Урок 1 физика', 1, 2);

INSERT INTO lesson_materials (id, created_at, deleted_at, updated_at, description, type, url, lesson_id)
 values (1,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Материал первого урока математики', 'video','url/url', 1);

INSERT INTO lesson_materials (id, created_at, deleted_at, updated_at, description, type, url, lesson_id)
 values (2,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Материал первого урока физики', 'video', 'url/url', 2);

INSERT INTO tests (id, created_at, deleted_at, updated_at, title, course_id, lesson_id, description)
 values (1,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Тест математики первый урок', 1, 1, 'Тест математика поможет вам быть ахуенным');

INSERT INTO tests (id, created_at, deleted_at, updated_at, title, course_id, lesson_id, description)
 values (2,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Тест физики первый урок', 2, 2, 'Тест физики поможет вам быть ахуенным');

INSERT INTO questions (id, created_at, deleted_at, updated_at, image, question, score, test_id)
 values (1,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', null, 'Как меня зовут?', 1, 1);

INSERT INTO questions (id, created_at, deleted_at, updated_at, image, question, score, test_id)
 values (2,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', null, 'Как тебя зовут?', 2, 2);

INSERT INTO question_options (id, created_at, deleted_at, updated_at, answer, right_answer, question_id)
 values (1,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Меня зовут Бекзат', true, 1);

INSERT INTO question_options (id, created_at, deleted_at, updated_at, answer, right_answer, question_id)
 values (2,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Меня зовут Асыл', false, 1);

INSERT INTO question_options (id, created_at, deleted_at, updated_at, answer, right_answer, question_id)
 values (3,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Тебя зовут Асыл', false, 2);

INSERT INTO question_options (id, created_at, deleted_at, updated_at, answer, right_answer, question_id)
 values (4,'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Тебя зовут Бекзат', true, 2);