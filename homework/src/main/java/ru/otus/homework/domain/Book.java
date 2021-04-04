package ru.otus.homework.domain;

import java.util.Objects;

public class Book {
    private final Long id;
    private final Long authorId;
    private final Long genreId;
    private final String title;

    public Book(Long id, Long authorId, Long genreId, String title) {
        this.id = id;
        this.authorId = authorId;
        this.genreId = genreId;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getGenreId() {
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
        return getId().equals(other.getId())
                & getAuthorId().equals(other.getAuthorId())
                & getGenreId().equals(other.getGenreId())
                & getTitle().equals(other.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, genreId, title);
    }
}
