package ru.otus.homework1.core.dao;

/**
 * Выбрасывается если, при загрузке заданий из источника, возникли
 * проблеммы при которых загрузка задыний стала невозможна
 *
 * {@link ExerciseDao}
 */
public class CantGetExercisesException extends RuntimeException {
}
