package ru.otus.homework1.implementations.naive.domain;

import ru.otus.homework1.core.domain.Answer;

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
}
