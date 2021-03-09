package ru.otus.homework2.implementations.naive.domain;

import ru.otus.homework2.core.domain.Answer;

public class AnswerImpl implements Answer {

    private String content;
    private boolean isRight;

    public AnswerImpl(String content, boolean isRight) {
        this.content = content;
        this.isRight = isRight;
    }

    @Override
    public boolean isRight() {
        return isRight;
    }

    @Override
    public String getText() {
        return content;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        Answer o = (Answer) other;
        return this.getText().equals(o.getText()) && this.isRight() == o.isRight();
    }
}
