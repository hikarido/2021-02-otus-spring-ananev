package ru.otus.homework.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book x;
    private Book y;
    final long expectedId = 1;
    final long expectedGenreId = 2;
    final long expectedAuthorId = 3;
    final String expectedTitle = "Long Story";

    @BeforeEach
    void beforeEach(){
        this.x = new Book(expectedId, expectedAuthorId, expectedGenreId, expectedTitle);
        this.y = new Book(expectedId, expectedAuthorId, expectedGenreId, expectedTitle);
    }

    @Test
    void getId() {
        Assertions.assertEquals(expectedId, x.getId());
    }

    @Test
    void getAuthorId() {
        Assertions.assertEquals(expectedAuthorId, x.getAuthorId());
    }

    @Test
    void getGenreId() {
        Assertions.assertEquals(expectedGenreId, x.getGenreId());
    }

    @Test
    void getTitle() {
        Assertions.assertEquals(expectedTitle, x.getTitle());
    }

    @Test
    void testEquals() {
        Assertions.assertEquals(x, x);
        assertEquals(y, x);
        Assertions.assertEquals(y, x);
        Assertions.assertNotEquals(x, null);
    }

    @Test
    void testHashCode() {
        Assertions.assertEquals(x.hashCode(), y.hashCode());
    }
}