package ru.otus.homework3.implementations.naive.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.homework3.core.domain.Answer;
import ru.otus.homework3.core.domain.Exercise;
import ru.otus.homework3.core.domain.Question;

import java.util.List;

class ExerciseImplTest {
    @Test
    void createTest() {
        Answer answer = Mockito.mock(Answer.class);
        Question question = Mockito.mock(Question.class);
        new ExerciseImpl(question, List.of(answer));
    }

    @Test
    void getQuestion(){
        Answer answer = Mockito.mock(Answer.class);
        Question question = Mockito.mock(Question.class);
        Exercise exercise = new ExerciseImpl(question, List.of(answer));
        Assertions.assertEquals(question, exercise.getQuestion());
    }

    @Test
    void getAnswerChoices(){
        Answer rightAnswer = Mockito.mock(Answer.class);
        Answer wrongAnswer = Mockito.mock(Answer.class);
        Mockito.when(rightAnswer.isRight()).thenReturn(true);
        Mockito.when(wrongAnswer.isRight()).thenReturn(false);

        Question question = Mockito.mock(Question.class);
        Exercise exercise = new ExerciseImpl(question, List.of(rightAnswer, wrongAnswer));
        Assertions.assertEquals(List.of(rightAnswer, wrongAnswer), exercise.getAnswerChoices());
    }

    @Test
    void getAnswerOfExercise(){
        Answer rightAnswer = Mockito.mock(Answer.class);
        Answer wrongAnswer = Mockito.mock(Answer.class);
        Mockito.when(rightAnswer.isRight()).thenReturn(true);
        Mockito.when(wrongAnswer.isRight()).thenReturn(false);

        Question question = Mockito.mock(Question.class);
        Exercise exercise = new ExerciseImpl(question, List.of(rightAnswer, wrongAnswer));
        Assertions.assertEquals(List.of(rightAnswer), exercise.getAnswersOfExercise());
    }
}