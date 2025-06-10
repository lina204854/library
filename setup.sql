-- 1. Создание базы данных 
CREATE DATABASE IF NOT EXISTS library_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- 2. Переключение на созданную базу данных
USE library_db;

-- 3. Создание таблицы для книг
CREATE TABLE IF NOT EXISTS books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    student_name VARCHAR(255),
    issue_date DATE,
    return_date DATE
);

-- 4.  Добавление нескольких тестовых записей
INSERT INTO books (title, publisher, student_name, issue_date, return_date) VALUES
('Война и мир', 'Эксмо', 'Иванов Иван Иванович', '2023-11-10', '2023-12-01'),
('Преступление и наказание', 'АСТ', 'Петрова Анна Сергеевна', '2023-11-10', NULL),
('Мастер и Маргарита', 'Азбука', 'Сидоров Петр Васильевич', '2023-11-12', '2023-11-28'),
('1984', 'Эксмо', 'Кузнецова Ольга Дмитриевна', '2023-11-15', NULL),
('Идиот', 'АСТ', 'Иванов Иван Иванович', '2023-11-15', NULL);
