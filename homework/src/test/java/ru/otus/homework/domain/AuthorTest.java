package ru.otus.homework.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthorTest {

    private final Long expectedId = 10L;
    private final String expectedFullName = "My name";
    private Author x;
    private Author y;

    @BeforeEach
    void beforeEach() {
        x = new Author(expectedId, expectedFullName);
        y = new Author(expectedId, expectedFullName);
    }

    @Test
    void getId() {
        Assertions.assertEquals(expectedId, x.getId());
    }

    @Test
    void getFullName() {
        Assertions.assertEquals(expectedFullName, x.getFullName());
    }

    @Test
    void testEquals() {
        Assertions.assertEquals(x, x);
        Assertions.assertEquals(y, x);
        Assertions.assertEquals(x, y);
        Assertions.assertNotEquals(x,null);
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(x.hashCode(), y.hashCode());
    }
}