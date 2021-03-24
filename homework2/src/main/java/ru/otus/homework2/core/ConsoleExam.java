package ru.otus.homework2.core;

/**
 * Консольный экзамен<p>
 * Что то очень близкое к state machine. Вызов процедур для перемещения по состояниям следующий:
 *
 * <ul>
 *     <li>login</li>
 *     <li>getCurrentUserName for hello message</l>
 *     <li>while hasNextExercise if so:</li>
 *     <ul>
 *          <li>nextExerciseText</li>
 *          <li>setAnswerOfUser</li>
 *     </ul>
 *     <li>wasExamPassed</li>
 * </ul>
 *
 * Для проверки консистентности экзамена используйте
 * <ul>
 *     <li>wasUserLogged</li>
 *     <li>currentAnswerWasTaken</li>
 *     <li>hasNextExercise</li>
 * </ul>
 */
public interface ConsoleExam {
    /**
     * сервис узнает о пользователе
     * Что бы начать тестирование обязательно нужен
     * залогиненный пользователь
     *
     * @param userFirstName имя пользователя
     * @param userSecondName фамилия пользователя
     */
    void login(String userFirstName, String userSecondName);

    /**
     * Проверка был ли пользователь залигинен
     * @return true if before login was called else false
     */
    boolean wasUserLogged();

    /**
     * Получить имя текущего пользователя
     * @return user name if befor login was called else it leaves up to implementation. For example empty string.
     */
    String getCurrentUserName();

    /**
     * Узнать пройден ли экзамен
     * @return true if student has enough right answers to pass exam
     */
    boolean wasExamPassed();

    /**
     * Проверить, есть ли в текущем экзамене еще вопросы
     * которые можно задать пользователю
     * @return true if new exercise can be retrieved else false
     */
    boolean hasNextExercise();

    /**
     * Получить следующий вопрос из экзамена
     * Обязательно перед использованием проверить на {@link ConsoleExam#hasNextExercise()}
     * @return formatted text of exercise (question + answers)
     */
    String nextExerciseText();

    /**
     * Установить ответ на вопрос полученный ранее через {@link ConsoleExam#nextExerciseText()}
     * @param answer answer in free form, interpretation rules leaves up to implementation
     */
    void setAnswerOfUser(String answer);

    /**
     * Проверить был ли последний поврос полученный из {@link ConsoleExam#nextExerciseText()} отвечен
     * @return true if for current question the setAnswerOfUser was called already
     */
    boolean currentAnswerWasTaken();
}
