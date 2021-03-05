package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework1.core.dao.ExerciseDao;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        ExerciseDao dao = context.getBean(ExerciseDao.class);
    }
}
