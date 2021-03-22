package ru.otus.homework2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import ru.otus.homework2.core.ConsoleExam;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Homework3Application {

    public static void main(String[] args) {
        var context = SpringApplication.run(Homework3Application.class, args);
        var exam = context.getBean(ConsoleExam.class);
        exam.performExam();
    }

}
