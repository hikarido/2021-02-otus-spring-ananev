package ru.otus.homework.domain;

import java.util.Objects;

public class Author {
    private final int id;
    private final String fullName;

    public Author(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return String.format("id: %d, fullName: %s", id, fullName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }

        Author o = (Author) other;
        return getFullName().equals(o.fullName) & getId() == o.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }
}
