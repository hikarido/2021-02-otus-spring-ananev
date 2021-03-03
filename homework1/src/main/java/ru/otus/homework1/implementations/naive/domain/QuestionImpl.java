package ru.otus.homework1.implementations.naive.domain;

import ru.otus.homework1.core.domain.Question;

public class QuestionImpl implements Question {
    private String content;

    public QuestionImpl(String content) {
        this.content = content;
    }

    public String getText() {
        return content;
    }
}
