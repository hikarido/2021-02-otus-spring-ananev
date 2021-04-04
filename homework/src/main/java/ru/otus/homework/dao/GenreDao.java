package ru.otus.homework.dao;

import ru.otus.homework.domain.Genre;

import java.util.Collection;
import java.util.Optional;

public interface GenreDao {
    Genre create(Genre genre);

    Optional<Genre> read(long id);

    Collection<Genre> readAll();

    void update(Genre genre);

    boolean delete(Genre genre);
}
