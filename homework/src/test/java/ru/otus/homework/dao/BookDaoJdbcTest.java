package ru.otus.homework.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homework.domain.Author;
import ru.otus.homework.domain.Book;
import ru.otus.homework.domain.Genre;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@JdbcTest
@Import(ru.otus.homework.dao.BookDaoJdbc.class)
class BookDaoJdbcTest {

    private final int BOOKS_COUNT = 4;

    private final Author HERO_MARIN_AUTHOR = new Author(1L, "Hero Marin");
    private final Author MIHAIL_AFANASIEVICH_BULGAKOV = new Author(2L, "Mihail Afanasievich Bulgakov");
    private final Author OXANA_GEPPERT = new Author(3L, "Oxana Geppert");
    private final Author JOHN_RONALD_REUEL_TOLKIEN = new Author(4L, "John Ronald Reuel Tolkien");

    private final Genre SCIENCE_FICTION = new Genre(4L, "science fiction");
    private final Genre CLASSIC = new Genre(1L, "classic");
    private final Genre FANTASY = new Genre(3L, "fantasy");

    private Author expectedAuthor = new Author(2L, "Mihail Afanasievich Bulgakov");
    private Genre expectedGenre = new Genre(1L, "classic");
    private Book expectedBook = new Book(2L, expectedAuthor, expectedGenre, "The Master and Margarita");
    @Autowired
    private BookDao bookDao;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private GenreDao genreDao;

    @BeforeEach
    void init() {
        Mockito.when(authorDao.read(Mockito.anyLong())).thenReturn(Optional.of(expectedAuthor));
        Mockito.when(genreDao.read(Mockito.anyLong())).thenReturn(Optional.of(expectedGenre));
        Mockito.when(authorDao.create(Mockito.any())).thenReturn(expectedAuthor);
        Mockito.when(genreDao.create(Mockito.any())).thenReturn(expectedGenre);
    }

    @Test
    void create() {
        final Book expected = new Book(10L, expectedAuthor, expectedGenre, "title");
        final Book actual = bookDao.create(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void read() {
        final Optional<Book> actual = bookDao.read(expectedBook.getId());
        if (actual.isEmpty()) {
            Assertions.fail("Entity must be presented");
        }
        Assertions.assertEquals(expectedBook, actual.get());
    }

    @Test
    void update() {
        final Book expected = new Book(expectedBook.getId(), expectedAuthor, expectedGenre, "New name");
        final Optional<Book> actual = bookDao.update(expected);
        if (actual.isEmpty()) {
            Assertions.fail("Entity must be presented");
        }
        Assertions.assertEquals(expected, actual.get());
    }

    @Test
    void delete() {
        Assertions.assertTrue(bookDao.delete(expectedBook));
    }

    @Test
    void deleteAlreadyDeleted() {
        bookDao.delete(expectedBook);
        Assertions.assertFalse(bookDao.delete(expectedBook));
    }

    @Test
    void findAll() {
        Set<Book> books = Set.of(new Book(1L, HERO_MARIN_AUTHOR, SCIENCE_FICTION, "The Starcraft hand book"),
                new Book(2L, MIHAIL_AFANASIEVICH_BULGAKOV, CLASSIC, "The Master and Margarita"),
                new Book(3L, OXANA_GEPPERT, SCIENCE_FICTION, "The Community"),
                new Book(4L, JOHN_RONALD_REUEL_TOLKIEN, FANTASY, "The Lord Of the rings")
        );
        final Set<Book> actual = new HashSet<>(bookDao.findAll());
        Assertions.assertEquals(books, actual);
    }

    @Test
    void count() {
        Assertions.assertEquals(BOOKS_COUNT, bookDao.count());
    }
}