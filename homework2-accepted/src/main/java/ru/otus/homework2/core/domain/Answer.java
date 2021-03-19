package ru.otus.homework2.core.domain;

/**
 * Ответ на вопрос к заданию
 * @see Question
 * @see Exercise
 */
public interface Answer {
    /**
     * @return контент ответа
     */
    String getText();

    /**
     * @return верен ли ответ
     */
    boolean isRight();
}
