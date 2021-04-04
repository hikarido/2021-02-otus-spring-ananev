package ru.otus.homework.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GenreTest {
    private final Long expectedId = 1L;
    private final String expectedName = "Old Classic";
    private Genre x;
    private Genre y;

    @BeforeEach
    void beforeEach(){
        x = new Genre(expectedId, expectedName);
        y = new Genre(expectedId, expectedName);
    }

    @Test
    void getId() {
        Assertions.assertEquals(expectedId, x.getId());
    }

    @Test
    void getName() {
        Assertions.assertEquals(expectedName, x.getName());
    }

    @Test
    void testEquals() {
        Assertions.assertEquals(x, x);
        Assertions.assertEquals(x, y);
        Assertions.assertEquals(y, x);
        Assertions.assertNotEquals(x, null);
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(x.hashCode(), y.hashCode());
    }
}