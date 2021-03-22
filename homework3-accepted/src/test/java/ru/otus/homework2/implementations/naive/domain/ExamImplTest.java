package ru.otus.homework2.implementations.naive.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.homework2.core.dao.ExerciseDao;
import ru.otus.homework2.core.domain.Answer;
import ru.otus.homework2.core.domain.Exam;
import ru.otus.homework2.core.domain.Exercise;
import ru.otus.homework2.core.domain.Question;

import java.util.List;

class ExamImplTest {
    @Test
    void create() {
        ExerciseDao dao = Mockito.mock(ExerciseDao.class);
        new ExamImpl(dao, 5, 5);
    }

    @Test
    void getExercisesTest() {
        ExerciseDao dao = Mockito.mock(ExerciseDao.class);
        Exercise exercise = Mockito.mock(Exercise.class);
        List<Exercise> exercises = List.of(exercise, exercise, exercise);
        Mockito.when(dao.getExercises()).thenReturn(exercises);
        Exam exam = new ExamImpl(dao, 3, 3);
        Assertions.assertEquals(exercises, exam.getExercises());
    }

    @Test
    @DisplayName("Create exam with two exercise, exercise contains two question. " +
            "Each question has two answer. One answer is right other is wrong." +
            " User give one right answer and exam deems as passed ")
    void createTwoExerciseWithTwoQuestionInEachThenUserAnswersEnoughToPassExamTest() {
        Question questionOne = Mockito.mock(Question.class);
        Mockito.when(questionOne.getText()).thenReturn("First question");
        Question questionTwo = Mockito.mock(Question.class);
        Mockito.when(questionTwo.getText()).thenReturn("Second question");

        Answer correctAnswerOfQuestionOne = Mockito.mock(Answer.class);
        Mockito.when(correctAnswerOfQuestionOne.isRight()).thenReturn(true);
        Answer wrongAnswerOfQuestionOne = Mockito.mock(Answer.class);
        Mockito.when(wrongAnswerOfQuestionOne.isRight()).thenReturn(false);

        Answer correctAnswerQuestionTwo = Mockito.mock(Answer.class);
        Mockito.when(correctAnswerQuestionTwo.isRight()).thenReturn(true);
        Answer wrongAnswerOfQuestionTwo = Mockito.mock(Answer.class);
        Mockito.when(wrongAnswerOfQuestionTwo.isRight()).thenReturn(false);

        Exercise exerciseOne = Mockito.mock(Exercise.class);
        Mockito.when(exerciseOne.getQuestion()).thenReturn(questionOne);
        Mockito.when(exerciseOne.getAnswerChoices()).thenReturn(List.of(correctAnswerOfQuestionOne, wrongAnswerOfQuestionOne));
        Mockito.when(exerciseOne.getAnswersOfExercise()).thenReturn(List.of(correctAnswerOfQuestionOne));

        Exercise exerciseTwo = Mockito.mock(Exercise.class);
        Mockito.when(exerciseTwo.getQuestion()).thenReturn(questionTwo);
        Mockito.when(exerciseTwo.getAnswerChoices()).thenReturn(List.of(correctAnswerQuestionTwo, wrongAnswerOfQuestionTwo));
        Mockito.when(exerciseTwo.getAnswersOfExercise()).thenReturn(List.of(correctAnswerQuestionTwo));
        List<Exercise> exercises = List.of(exerciseOne, exerciseTwo);

        ExerciseDao dao = Mockito.mock(ExerciseDao.class);
        Mockito.when(dao.getExercises()).thenReturn(exercises);

        Exam exam = new ExamImpl(dao, 2, 1);
        List<Exercise> realExercises = exam.getExercises();
        Exercise realExerciseOne = realExercises.get(0);
        exam.answer(realExerciseOne, List.of(correctAnswerOfQuestionOne));
        Exercise realExerciseTwo = realExercises.get(1);
        exam.answer(realExerciseTwo, List.of(wrongAnswerOfQuestionTwo));
        Assertions.assertTrue(exam.wasExamPassed());
    }
}