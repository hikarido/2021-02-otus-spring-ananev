package ru.otus.homework.domain;

import java.util.Objects;

public class Author {
    private final Long id;
    private final String fullName;

    public Author(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "author id=" + id +
                ", author fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != getClass()) {
            return false;
        }

        Author o = (Author) other;
        return getFullName().equals(o.fullName) & getId().equals(o.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName);
    }
}
