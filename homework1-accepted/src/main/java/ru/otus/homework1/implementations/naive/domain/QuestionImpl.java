package ru.otus.homework1.implementations.naive.domain;

import ru.otus.homework1.core.domain.Question;

import java.util.Objects;

public class QuestionImpl implements Question {
    private String content;

    public QuestionImpl(String content) {
        this.content = content;
    }

    public String getText() {
        return content;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this.getClass() != other.getClass()) {
            return false;
        }

        Question o = (Question) other;
        return Objects.equals(this.getText(), o.getText());
    }
}
