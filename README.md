# Информационная система "Библиотека"

Это веб-приложение для управления книгами в библиотеке, разработанное на Spring Boot.

## Технологический стек

*   Java 17
*   Spring Boot
*   Spring Data JPA (Hibernate)
*   MySQL
*   Thymeleaf
*   HTML/CSS/JavaScript

## Как запустить проект

### 1. Подготовка базы данных

1.  Запустите сервер MySQL.
2.  Выполните SQL-скрипт из файла `setup.sql`, который находится в корне этого репозитория. Этот скрипт создаст базу данных `library_db` и таблицу `books`.

### 2. Настройка подключения

1.  Откройте файл `library-app/src/main/resources/application.properties`.
2.  Измените строки `spring.datasource.username` и `spring.datasource.password`, указав свои данные для подключения к MySQL.

### 3. Сборка и запуск приложения

1.  Перейдите в директорию проекта: `cd library-app`
2.  Запустите приложение с помощью Maven: `./mvnw spring-boot:run`
3.  Приложение будет доступно по адресу: `http://localhost:8080`

### Данные для входа

*   **Логин:** `user`
*   **Пароль:** `password`
