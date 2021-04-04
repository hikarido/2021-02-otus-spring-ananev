package ru.otus.homework.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import ru.otus.homework.domain.Author;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@JdbcTest
@Import(ru.otus.homework.dao.AuthorDaoJdbc.class)
@Sql(scripts = {"classpath:test-schema.sql", "classpath:test-data.sql"})
class AuthorDaoJdbcTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void create() {
        final Author expected = new Author(10L, "Author");
        final Author actual = authorDao.create(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void read() {
        final Author expected = new Author(1L, "Hero Marin");
        final Optional<Author> retrieved = authorDao.read(expected.getId());
        if (retrieved.isEmpty()) {
            Assertions.fail("Entity must presence");
        }
        Assertions.assertEquals(expected, retrieved.get());
    }

    @Test
    void readAll() {
        Set<Author> expected = Set.of(
                new Author(1L, "Hero Marin"),
                new Author(2L, "Mihail Afanasievich Bulgakov"),
                new Author(3L, "Oxana Geppert"),
                new Author(4L, "John Ronald Reuel Tolkien"));

        Set<Author> actual = new HashSet<>(authorDao.readAll());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void update() {
        Author expected = new Author(1L, "Hero Marin Updated");
        authorDao.update(expected);
        final Optional<Author> actual = authorDao.read(expected.getId());
        if(actual.isEmpty()){
            Assertions.fail("Entity must presence");
        }
        Assertions.assertEquals(expected, actual.get());
    }

    @Test
    void delete() {
        Author expected = new Author(1L, "Hero Marin");
        Assertions.assertTrue(authorDao.delete(expected));
        Assertions.assertTrue(authorDao.read(expected.getId()).isEmpty());
    }

    @Test
    void readAbsenceEntity() {
        Author expected = new Author(1L, "Hero Marin");
        Assertions.assertTrue(authorDao.delete(expected));
        Assertions.assertTrue(authorDao.read(expected.getId()).isEmpty());
    }
}