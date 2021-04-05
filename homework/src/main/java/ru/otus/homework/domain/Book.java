package ru.otus.homework.domain;

import java.util.Objects;

public class Book {
    private final Long id;
    private final Author author;
    private final Genre genre;
    private final String title;

    public Book(Long id, Author author, Genre genre, String title) {
        this.id = id;
        this.author = author;
        this.genre = genre;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
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
                & getAuthor().equals(other.getAuthor())
                & getGenre().equals(other.getGenre())
                & getTitle().equals(other.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, genre, title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "book id=" + id +
                ", book author=" + author +
                ", book genre=" + genre +
                ", book title='" + title + '\'' +
                '}';
    }
}
