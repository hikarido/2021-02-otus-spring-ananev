package ru.otus.homework2.implementations.naive.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.homework2.core.ResourceAccessor;
import ru.otus.homework2.core.dao.ExerciseDao;
import ru.otus.homework2.core.domain.Answer;
import ru.otus.homework2.core.domain.Exercise;
import ru.otus.homework2.core.domain.Question;
import ru.otus.homework2.implementations.naive.domain.AnswerImpl;
import ru.otus.homework2.implementations.naive.domain.ExerciseImpl;
import ru.otus.homework2.implementations.naive.domain.QuestionImpl;

import org.mockito.Mockito;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ExerciseDaoCsvTest {

    @Test
    void DaoShouldBeCreatedCorrectly() {
        final String resourceName = "test_exercises.csv";
        final ResourceAccessor accessor = Mockito.mock(ResourceAccessor.class);
        final Path path = Paths.get(accessor.getClass().getClassLoader().getResource(resourceName).getPath());
        Mockito.when(accessor.getResourcePath(resourceName)).thenReturn(path);
        ExerciseDao dao = new ExerciseDaoCsv(resourceName, accessor);
    }

    @Test
    void amountOfSavedAndReadedExercisesMustBeEqual() {
        final String resourceName = "test_exercises.csv";
        final ResourceAccessor accessor = Mockito.mock(ResourceAccessor.class);
        final Path path = Paths.get(accessor.getClass().getClassLoader().getResource(resourceName).getPath());
        Mockito.when(accessor.getResourcePath(resourceName)).thenReturn(path);
        ExerciseDao dao = new ExerciseDaoCsv(resourceName, accessor);
        List<Exercise> haveReadExercises = dao.getExercises();
        Assertions.assertEquals(haveReadExercises.size(), 5);
    }

    @Test
    void ReadedExercisesMustBeEqualExercisesInFile() {
        // exercizes equals to exercises in file
        Question q = new QuestionImpl("How many people live on Earth?");
        Answer a = new AnswerImpl("more then 7_000_000_000", true);
        Answer b = new AnswerImpl("about seven millions", false);
        Answer c = new AnswerImpl("Earth?", false);
        Exercise e = new ExerciseImpl(q, List.of(a, b, c));
        List<Exercise> expectedExercises = List.of(e);

        final String resourceName = "test_exercises_with_one_question.csv";
        final ResourceAccessor accessor = Mockito.mock(ResourceAccessor.class);
        final Path path = Paths.get(accessor.getClass().getClassLoader().getResource(resourceName).getPath());
        Mockito.when(accessor.getResourcePath(resourceName)).thenReturn(path);
        ExerciseDao dao = new ExerciseDaoCsv(resourceName, accessor);
        List<Exercise> realExercises = dao.getExercises();
        Assertions.assertEquals(expectedExercises, realExercises);
    }
}