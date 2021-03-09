package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework2.core.dao.ExerciseDao;
import ru.otus.homework2.core.domain.Answer;
import ru.otus.homework2.core.domain.Exercise;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        ExerciseDao dao = context.getBean(ExerciseDao.class);

        for(Exercise e: dao.getExercises()){
            System.out.println(e.getQuestion().getText());
            for(Answer a: e.getAnswerChoices()){
                System.out.println(" * " + a.getText());
            }
            System.out.println();
        }
    }
}
