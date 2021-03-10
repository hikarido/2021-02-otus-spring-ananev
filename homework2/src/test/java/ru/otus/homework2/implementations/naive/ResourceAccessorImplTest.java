package ru.otus.homework2.implementations.naive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.homework2.core.ResourceAccessor;
import ru.otus.homework2.core.ThereIsNoSuchResource;
import ru.otus.homework2.core.dao.ExerciseDao;
import ru.otus.homework2.implementations.naive.dao.ExerciseDaoCsv;

import java.nio.file.Path;

class ResourceAccessorImplTest {

    @Test
    void createTest(){
        new ResourceAccessorImpl();
    }

    @Test
    void cantCreateByReasonPassesFileDoesNotExist() {
        ResourceAccessor resourceAccessor = new ResourceAccessorImpl();
        Assertions.assertThrows(ThereIsNoSuchResource.class,
                () -> resourceAccessor.getResourcePath("/dev/null/somefile"));
    }

    @Test
    void assessorShouldNoThrowExceptions() {
        ResourceAccessor resourceAccessor = new ResourceAccessorImpl();
        resourceAccessor.getResourcePath("test_exercises.csv");
    }
}