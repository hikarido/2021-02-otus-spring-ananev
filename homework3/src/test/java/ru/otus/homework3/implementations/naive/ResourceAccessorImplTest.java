package ru.otus.homework3.implementations.naive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.homework3.core.ResourceAccessor;
import ru.otus.homework3.core.CantGetAccessToResource;

class ResourceAccessorImplTest {

    @Test
    void createTest(){
        new ResourceAccessorImpl();
    }

    @Test
    void cantCreateByReasonPassesFileDoesNotExist() {
        ResourceAccessor resourceAccessor = new ResourceAccessorImpl();
        Assertions.assertThrows(CantGetAccessToResource.class,
                () -> resourceAccessor.getResourceInputStream("/dev/null/somefile"));
    }

    @Test
    void assessorShouldNoThrowExceptions() {
        ResourceAccessor resourceAccessor = new ResourceAccessorImpl();
        resourceAccessor.getResourceInputStream("test_exercises.csv");
    }
}