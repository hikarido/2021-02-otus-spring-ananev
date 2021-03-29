package ru.otus.homework1.implementations.naive.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.homework1.core.dao.CantGetExercisesException;
import ru.otus.homework1.core.dao.ExerciseDao;
import ru.otus.homework1.core.domain.Answer;
import ru.otus.homework1.core.domain.Exercise;
import ru.otus.homework1.core.domain.Question;
import ru.otus.homework1.implementations.naive.domain.AnswerImpl;
import ru.otus.homework1.implementations.naive.domain.ExerciseImpl;
import ru.otus.homework1.implementations.naive.domain.QuestionImpl;

import java.util.List;

public class ExerciseDaoCsvTest {

    @Test
    void cantCreateByReasonItIsNoFile() {
        Assertions.assertThrows(CantGetExercisesException.class, () -> new ExerciseDaoCsv("."));
    }

    @Test
    void cantCreateByReasonPassesFileDoesNotExist() {
        Assertions.assertThrows(CantGetExercisesException.class,
                () -> new ExerciseDaoCsv("/dev/null/somefile"));
    }

    @Test
    void DaoShouldBeCreatedCorrectly() {
        ExerciseDao dao = new ExerciseDaoCsv("test_exercises.csv");
    }

    @Test
    void amountOfSavedAndReadedExercisesMustBeEqual() {
        ExerciseDao dao = new ExerciseDaoCsv("test_exercises.csv");
        List<Exercise> haveReadExercises = dao.getExercises();
        Assertions.assertEquals(haveReadExercises.size(), 5);
    }

    @Test
    void ReadedExercisesMustBeEqualExercisesInFile() {
        Question q = new QuestionImpl("How many people live on Earth?");
        Answer a = new AnswerImpl("more then 7_000_000_000", true);
        Answer b = new AnswerImpl("about seven millions", false);
        Answer c = new AnswerImpl("Earth?", false);
        Exercise e = new ExerciseImpl(q, List.of(a, b, c));
        List<Exercise> expectedExercises = List.of(e);
        ExerciseDao dao = new ExerciseDaoCsv("test_exercises_with_one_question.csv");
        List<Exercise> realExercises = dao.getExercises();
        Assertions.assertEquals(expectedExercises, realExercises);
    }
}