package ru.otus.homework2.core.domain;

import java.util.List;

/**
 * Задание, которое предлагается пользователю.
 */
public interface Exercise {
    /**
     * @return
     * вопрос к заданию для отображения пользователю
     */
    Question getQuestion();

    /**
     * @return
     * Список вариантов ответов  для отображения пользователю
     */
    List<Answer> getAnswerChoices();

    /**
     * Ответ на задание может быть не один
     * @return
     * Все правильные ответы на вопрос
     */
    List<Answer> getAnswersOfExercise();
}
