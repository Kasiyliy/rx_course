-- Roles
  insert into roles (id, created_at, deleted_at, updated_at, name) values (nextval('s_roles'), '2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'ROLE_ADMIN');
  insert into roles (id, created_at, deleted_at, updated_at, name) values (nextval('s_roles'), '2019-07-14 11:46:01.432630', null, '2019-07-14 11:46:01.432630', 'ROLE_TEACHER');
  insert into roles (id, created_at, deleted_at, updated_at, name) values (nextval('s_roles'), '2019-07-14 11:46:05.958924', null, '2019-07-14 11:46:05.958924', 'ROLE_STUDENT');
  insert into roles (id, created_at, deleted_at, updated_at, name) values (nextval('s_roles'), '2019-07-14 11:47:04.925222', null, '2019-07-14 11:47:04.925222', 'ROLE_MANAGER');

-- MaterialTypes
  insert into material_types (id, created_at, deleted_at, updated_at, name, value) values (nextval('s_material_types'), '2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'VIDEO' , 'Видео');
  insert into material_types (id, created_at, deleted_at, updated_at, name, value) values (nextval('s_material_types'), '2019-07-14 11:46:01.432630', null, '2019-07-14 11:46:01.432630', 'AUDIO', 'Аудио');
  insert into material_types (id, created_at, deleted_at, updated_at, name, value) values (nextval('s_material_types'), '2019-07-14 11:46:05.958924', null, '2019-07-14 11:46:05.958924', 'DOCUMENT', 'Документ');
  insert into material_types (id, created_at, deleted_at, updated_at, name, value) values (nextval('s_material_types'), '2019-07-14 11:47:04.925222', null, '2019-07-14 11:47:04.925222', 'TEXT','Текст/Картинки');


-- Users
  INSERT INTO users (id, created_at, deleted_at, updated_at, first_name, last_name, login, password, phone_number, role_id) VALUES (nextval('s_users'), '2019-07-14 18:25:32.856000', null, '2019-07-14 18:25:32.856000', 'admin', 'admin', 'admin', '$2a$10$ucIJJWRSsGpF8LbZZP4GbeYjoKmSN9ADD3W3rA3xNsfJIVaK1Mv2q', '8777-777-77-77', 3);
  INSERT INTO users (id, created_at, deleted_at, updated_at, first_name, last_name, login, password, phone_number, role_id) VALUES (nextval('s_users'), '2019-07-14 18:25:32.856000', null, '2019-07-14 18:25:32.856000', 'admin2', 'admin2', 'admin2', '$2a$10$ucIJJWRSsGpF8LbZZP4GbeYjoKmSN9ADD3W3rA3xNsfJIVaK1Mv2q', '8777-777-77-77', 3);


-- Categories
INSERT INTO categories (id, created_at, deleted_at, updated_at, name, parent_category_id) VALUES (nextval('s_categories'), '2019-07-14 18:25:32.856000', null, '2019-07-14 18:25:32.856000', 'Programming', null);
INSERT INTO categories (id, created_at, deleted_at, updated_at, name, parent_category_id) VALUES (nextval('s_categories'), '2019-07-14 18:25:32.856000', null, '2019-07-14 18:25:32.856000', 'Java', 1);

INSERT INTO categories (id, created_at, deleted_at, updated_at, name, parent_category_id)
VALUES (nextval('s_categories'), '2019-07-14 18:25:32.856000', NULL, '2019-07-14 18:25:32.856000', 'Python', 1);
INSERT INTO categories (id, created_at, deleted_at, updated_at, name, parent_category_id)
VALUES (nextval('s_categories'), '2019-07-14 18:25:32.856000', NULL, '2019-07-14 18:25:32.856000', 'C++', 1);


INSERT INTO categories (id, created_at, deleted_at, updated_at, name, parent_category_id)
VALUES (nextval('s_categories'), '2019-07-14 18:25:32.856000', NULL, '2019-07-14 18:25:32.856000', 'Marketing', NULL);
INSERT INTO categories (id, created_at, deleted_at, updated_at, name, parent_category_id)
VALUES (nextval('s_categories'), '2019-07-14 18:25:32.856000', NULL, '2019-07-14 18:25:32.856000', 'Project Manager', 5);

-- Courses
INSERT INTO courses (id, created_at, deleted_at, updated_at, name, description, grade, image, price, teacher_support, user_id, category_id)
  values (nextval('s_courses'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Математика','Курс математика, прикольно очень',9,'src/koe 4to',500.00,true,1,1);

INSERT INTO courses (id, created_at, deleted_at, updated_at, name, description, grade, image, price, teacher_support, user_id, category_id)
 values (nextval('s_courses'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Физика','Курс физика,прикольно очень',10,'src/koe 4to',600.00,true,2, 2);


-- Lessons
INSERT INTO lessons (id, created_at, deleted_at, updated_at, name, order_number, course_id)
 values (nextval('s_lessons'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Урок 1 математика', 1, 1);

INSERT INTO lessons (id, created_at, deleted_at, updated_at, name, order_number, course_id)
 values (nextval('s_lessons'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Урок 1 физика', 1, 2);


-- Lesson materials
INSERT INTO lesson_materials (id, created_at, deleted_at, updated_at, description, material_type_id, url, lesson_id)
 values (nextval('s_lesson_materials'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Материал первого урока математики', 1,'url/url', 1);

INSERT INTO lesson_materials (id, created_at, deleted_at, updated_at, description, material_type_id, url, lesson_id)
 values (nextval('s_lesson_materials'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Материал первого урока физики', 1, 'url/url', 2);


-- Tests
INSERT INTO tests (id, created_at, deleted_at, updated_at, title, course_id, lesson_id, description)
 values (nextval('s_tests'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Тест математики первый урок', 1, 1, 'Тест математика поможет вам быть ахуенным');

INSERT INTO tests (id, created_at, deleted_at, updated_at, title, course_id, lesson_id, description)
 values (nextval('s_tests'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Тест физики первый урок', 2, 2, 'Тест физики поможет вам быть ахуенным');



-- Questions
INSERT INTO questions (id, created_at, deleted_at, updated_at, image, question, score, test_id)
 values (nextval('s_questions'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', null, 'Как меня зовут?', 1, 1);

INSERT INTO questions (id, created_at, deleted_at, updated_at, image, question, score, test_id)
 values (nextval('s_questions'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', null, 'Ты далбаеб?', 2, 1);

INSERT INTO questions (id, created_at, deleted_at, updated_at, image, question, score, test_id)
 values (nextval('s_questions'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', null, 'Как тебя зовут?', 2, 2);


-- Question options
INSERT INTO question_options (id, created_at, deleted_at, updated_at, answer, right_answer, question_id)
 values (nextval('s_question_options'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Меня зовут Бекзат', true, 1);

INSERT INTO question_options (id, created_at, deleted_at, updated_at, answer, right_answer, question_id)
 values (nextval('s_question_options'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Меня зовут Асыл', false, 1);

INSERT INTO question_options (id, created_at, deleted_at, updated_at, answer, right_answer, question_id)
 values (nextval('s_question_options'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Тебя зовут Асыл', false, 2);

INSERT INTO question_options (id, created_at, deleted_at, updated_at, answer, right_answer, question_id)
 values (nextval('s_question_options'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Тебя зовут Бекзат', true, 2);

INSERT INTO question_options (id, created_at, deleted_at, updated_at, answer, right_answer, question_id)
 values (nextval('s_question_options'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Да', true, 3);

INSERT INTO question_options (id, created_at, deleted_at, updated_at, answer, right_answer, question_id)
 values (nextval('s_question_options'),'2019-07-14 11:45:49.344428', null, '2019-07-14 11:45:49.344428', 'Нет', true, 3);
