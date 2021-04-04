package ru.otus.homework.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.homework.domain.Genre;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@JdbcTest
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"})
@Import(ru.otus.homework.dao.GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    void create() {
        final Genre expectedGenre = new Genre(1000L, "name");
        final Genre actualGenre = genreDao.create(expectedGenre);
        Assertions.assertEquals(expectedGenre, actualGenre);
    }

    @Test
    void readOfExistedEntity() {
        final Genre expectedGenre = new Genre(1L, "classic");
        final Optional<Genre> actualGenre = genreDao.read(1L);
        if (actualGenre.isEmpty()) {
            Assertions.fail("empty optional was retrieved. Entity should presence.");
        }
        Assertions.assertEquals(expectedGenre, actualGenre.get());
    }

    @Test
    void readOfNotExistedEntity() {
        final Optional<Genre> actualGenre = genreDao.read(19234029L);
        Assertions.assertFalse(actualGenre.isPresent());
    }

    @Test
    void update() {
        final long expectedId = 10L;
        final Genre genre = new Genre(expectedId, "New genre");
        genreDao.create(genre);
        final Genre expected = new Genre(genre.getId(), "Updated");
        genreDao.update(expected);
        final Optional<Genre> actual = genreDao.read(expectedId);
        if (actual.isEmpty()) {
            Assertions.fail("Entity should presence");
        }
        Assertions.assertEquals(expected, actual.get());
    }

    @Test
    void delete() {
        final boolean wasRemoved = genreDao.delete(new Genre(5L, "humor"));
        Assertions.assertTrue(wasRemoved);
    }

    @Test
    void readAll() {
        Set<Genre> expected = Set.of(
                new Genre(1L, "classic"),
                new Genre(2L, "horror"),
                new Genre(3L, "fantasy"),
                new Genre(4L, "science fiction"),
                new Genre(5L, "humor")
        );

        Set<Genre> actualSet = new HashSet<>(genreDao.readAll());
        Assertions.assertEquals(expected, actualSet);
    }
}