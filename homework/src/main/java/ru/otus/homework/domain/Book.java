package ru.otus.homework.domain;

import java.util.Objects;

public class Book {
    private final int id;
    private final int authorId;
    private final int genreId;
    private final String title;

    public Book(int id, int authorId, int genreId, String title) {
        this.id = id;
        this.authorId = authorId;
        this.genreId = genreId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Book other = (Book) o;
        return getId() == other.getId()
                & getAuthorId() == other.getAuthorId()
                & getGenreId() == other.getGenreId()
                & getTitle().equals(other.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, genreId, title);
    }
}
