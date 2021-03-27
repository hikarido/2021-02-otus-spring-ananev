package ru.otus.homework2.core.dao;

import ru.otus.homework2.core.domain.Exercise;

import java.util.List;

/**
 * позволяет получить задания из источника
 */
public interface ExerciseDao {
    /**
     * Получить все доступные задания из источника.
     * Источник заданий определяется имплементацией
     * @return список всех достуныз заданий
     * @throws CantGetExercisesException если имплементация
     * не в состоянии вернуть корректные задания
     */
    List<Exercise> getExercises();
}
