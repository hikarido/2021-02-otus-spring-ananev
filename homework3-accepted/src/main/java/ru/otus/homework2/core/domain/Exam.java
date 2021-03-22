package ru.otus.homework2.core.domain;

import java.util.List;

/**
 *
 * Contains exam logic. <p>
 *
 * Actions like this lead to perform exam<p>
 * <ul>
 *     <li>User takes all exercises
 *     <li> Answers on each of them consequently
 *     <li> After iteration through all exercises of exam checks was exam passed or not
 * </ul>
 *
 * Decision about how many answers should be answered and answered correctly leave up to
 * implementation decision
 */
public interface Exam {
    List<Exercise> getExercises();
    void answer(Exercise toExercise, List<Answer> byAnswers);
    boolean wasExamPassed();
}
