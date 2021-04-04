package ru.otus.homework.dao;

import ru.otus.homework.domain.Author;

import java.util.Collection;
import java.util.Optional;

public interface AuthorDao {
    Author create(Author author);

    Optional<Author> read(Author author);

    Collection<Author> readAll();

    void update(Author author);

    boolean delete(Author author);
}
