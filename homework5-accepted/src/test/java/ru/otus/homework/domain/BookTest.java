package ru.otus.homework.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book x;
    private Book y;
    final long expectedId = 1;
    private final String expectedTitle = "Long Story";
    private Author expectedAuthor;
    private Genre expectedGenre;

    @BeforeEach
    void beforeEach(){
        expectedAuthor = new Author(10L, "title writer");
        expectedGenre = new Genre(10L, "fable");
        this.x = new Book(expectedId, expectedAuthor, expectedGenre, expectedTitle);
        this.y = new Book(expectedId, expectedAuthor, expectedGenre, expectedTitle);
    }

    @Test
    void getId() {
        Assertions.assertEquals(expectedId, x.getId());
    }

    @Test
    void getAuthorId() {
        Assertions.assertEquals(expectedAuthor.getId(), x.getAuthor().getId());
    }

    @Test
    void getGenreId() {
        Assertions.assertEquals(expectedGenre.getId(), x.getGenre().getId());
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