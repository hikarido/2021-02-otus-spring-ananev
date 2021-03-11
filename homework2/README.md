# Домашнее задание 2

# Запуск

* java -cp target/homework2-1.0-SNAPSHOT.jar ru.otus.Main

# Текст задачи

Приложение по проведению тестирования студентов (с самим тестированием)
Цель: Цель: конфигурировать Spring-приложения современным способом, как это и делается в современном мире
Результат: готовое современное приложение на чистом Spring
Новый функционал:

Программа должна спросить у пользователя фамилию и имя, спросить 5 вопросов из CSV-файла и вывести результат тестирования.

Выполняется на основе предыдущего домашнего задания + , собственно, сам функционал тестирования.

Требования:
1. Переписать конфигурацию в виде Java + Annotation-based конфигурации.
2. Добавить функционал тестирования студента.
3. Добавьте файл настроек для приложения тестирования студентов.
4. В конфигурационный файл можно поместить путь до CSV-файла, количество правильных ответов для зачёта - на Ваше усмотрение.
5. Если Вы пишите интеграционные тесты, то не забудьте добавить аналогичный файл и для тестов.
6. Scanner, PrintStream и другие стандартные типы в контекст класть не нужно!
7. Ввод-вывод на английском языке.
8. Помним, "без фанатизма" :)

# take aways

* how to run debug from mvn
  * set breakpoints where you want in IDEA
  * mvn package -Dmaven.surefire.debug 
  * go Run -> Attach to Process -> and attach to process on 5005 port 
* how to run certain test 
  *  mvn -Dtest=ResourceAccessorImplTest#resultPathEqualToExpectedPath test
* Difference between Class.getResource and similar action trough ClassLoader
  * In short: Class represent path as relative or absolute, ClassLoader things that path is always absolute. 
  * Full: [Answer](https://stackoverflow.com/a/6608848/5542559)
* faced with IDEA [issue](https://youtrack.jetbrains.com/issue/IDEA-238891). Only newly installation of idea was helpful
* best [answer](https://stackoverflow.com/a/20389418/5542559) to question "Why it works in ide but doesn't in jar after package. Or why resource can't be accessed in jar"

# Questions

* How to test ExerciseDaoCsv by unit tests? Any test I have written can be treated as integration test. 
  * for example this test: ReadedExercisesMustBeEqualExercisesInFile