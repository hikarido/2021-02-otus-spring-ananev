package ru.otus.homework.domain;

import java.util.Objects;

public class Genre {
    private final Long id;
    private final String name;

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return getId().equals(genre.getId()) & getName().equals(genre.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genre id=" + getId() +
                ", genre name='" + getName() + '\'' +
                '}';
    }
}
