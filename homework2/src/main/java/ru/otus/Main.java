package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.homework2.config.MainConfig;
import ru.otus.homework2.core.ConsoleExam;
import ru.otus.homework2.core.dao.ExerciseDao;
import ru.otus.homework2.core.domain.Answer;
import ru.otus.homework2.core.domain.Exercise;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(MainConfig.class);

        ConsoleExam consoleExam = context.getBean(ConsoleExam.class);
        consoleExam.performExam();
    }
}
