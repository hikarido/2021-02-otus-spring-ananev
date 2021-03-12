package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.homework2.config.MainConfig;
import ru.otus.homework2.core.ConsoleExam;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(MainConfig.class);

        ConsoleExam consoleExam = context.getBean(ConsoleExam.class);
        consoleExam.performExam();
    }
}
