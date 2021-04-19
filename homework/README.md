# Домашнее задание 5

# Цели занятия

* эффективно использовать JDBC вместе со Spring JDBC для разработки приложений с мощью чистого SQL;
* правильно применять паттерн DAO для подключения к БД;
* пользоваться embedded БД для написания тестов и при разработке простых приложений.

# Краткое содержание
* паттерн DAO;
* необходимость кода для чистого JDBC;
* простое DAO на Spring JDBC с минимумом boilerplate кода.

# Результаты
* приложение, работающее с embedded БД с DAO на Spring JDBC.

# Описание работы
* Создать приложение хранящее информацию о книгах в библиотеке

# Цель:
* использовать возможности Spring JDBC и spring-boot-starter-jdbc для подключения к реляционным базам данных 

# Результат
приложение с хранением данных в реляционной БД, которое в дальнейшем будем развивать

---
* Использовать Spring JDBC и реляционную базу (H2 или настоящую реляционную БД). 
  * Настоятельно рекомендуем использовать NamedParametersJdbcTemplate 
* Предусмотреть таблицы авторов, книг и жанров.
* Предполагается отношение многие-к-одному (у книги один автор и жанр).
  * Опциональное усложнение - отношения многие-ко-многим (у книги может быть много авторов и/или жанров).
* Интерфейс выполняется на Spring Shell (CRUD книги обязателен, операции с авторами и жанрами - как будет удобно).
* Скрипт создания таблиц и скрипт заполнения данными должны автоматически запускаться с помощью spring-boot-starter-jdbc.
* Покрыть тестами, насколько это возможно.

Рекомендации к выполнению работы:
 * НЕ делать AbstractDao. 
 * НЕ делать наследования в тестах

# Ход работ

* ссылки
  * [how to run h2 in spring boot](https://www.baeldung.com/spring-boot-h2-database)
  * [baeldung soring data jdbc tutorial](https://www.baeldung.com/spring-jdbc-jdbctemplate)
  * [sql language online trainer](https://www.pgexercises.com/questions/joins/simplejoin.html)
  * [spring's exception for dao](https://docs.spring.io/spring-framework/docs/5.3.4/reference/html/data-access.html#dao-exceptions)
  * [baeldung tutorial source code repo](https://github.com/eugenp/tutorials)
  
* основной запрос
```sql
select book.title as `Название`, author.full_name as `Писатель`, genre.name as `Жанр` 
  from book join author on book.author_id = author.id
  join genre on book.genre_id = genre.id ;
```
