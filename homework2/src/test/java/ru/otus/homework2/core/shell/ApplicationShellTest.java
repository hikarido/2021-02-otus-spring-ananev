package ru.otus.homework2.core.shell;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.Shell;
import ru.otus.homework2.core.ConsoleExam;

@SpringBootTest
class ApplicationShellTest {
    private final String USER_FIRST_NAME = "hero";
    private final String USER_SECOND_NAME = "marin";
    private final String SHORT_CUT_FOR_LOGIN_CMD = "l";
    private final String SHORT_CUT_FOR_GET_NEXT_QUESTION_CMD = "n";
    private final String SHORT_CUT_FOR_ANSWER_CMD = "a";

    @Autowired
    Shell shell;

    @MockBean
    ConsoleExam consoleExam;

    @DisplayName("удачна попытка лагина")
    @Test
    void loginWithoutParams() {
        Mockito.when(consoleExam.getCurrentUserName()).thenReturn(USER_FIRST_NAME + " " + USER_SECOND_NAME);
        final String actual = (String) shell.evaluate(() -> SHORT_CUT_FOR_LOGIN_CMD + " " + USER_FIRST_NAME + " " + USER_SECOND_NAME);
        final String expected = "Hello " + USER_FIRST_NAME + " " + USER_SECOND_NAME;
        Assertions.assertEquals(expected, actual.trim());
    }

    @DisplayName("Не удачная попытка получить новый вопрос для не авторизированного юзера")
    @Test
    void tryGetNextAnswerForNotLoggedUser() {
        Mockito.when(consoleExam.wasUserLogged()).thenReturn(false);
        final String actual = (String) shell.evaluate(() -> SHORT_CUT_FOR_GET_NEXT_QUESTION_CMD);
        final String expected = "User have not been logged yet!";
        Assertions.assertEquals(expected, actual.trim());
    }

    @DisplayName("Попытка ответить на еще не заданные вопросы")
    @Test
    void tryAnswerToNotRetrievedQuestions() {
        Mockito.when(consoleExam.wasUserLogged()).thenReturn(true);
        Mockito.when(consoleExam.currentAnswerWasTaken()).thenReturn(true);
        final String actual = (String) shell.evaluate(() -> SHORT_CUT_FOR_ANSWER_CMD + " 0");
        final String expected = "There is no question to answer";
        Assertions.assertEquals(expected, actual.trim());
    }

    @DisplayName("Проверить возможность отвечать на вопрос")
    @Test
    void shouldAnswerToQuestion() {
        Mockito.when(consoleExam.wasUserLogged()).thenReturn(true);
        Mockito.when(consoleExam.hasNextExercise()).thenReturn(true);
        Mockito.when(consoleExam.currentAnswerWasTaken()).thenReturn(false);
        Mockito.when(consoleExam.nextExerciseText()).thenReturn("some text");
        final String okStatus = (String) shell.evaluate(() -> SHORT_CUT_FOR_ANSWER_CMD + " 0");

        Assertions.assertEquals("ok", okStatus.trim());
        Mockito.when(consoleExam.wasExamPassed()).thenReturn(true);

    }


    @DisplayName("Все вопросы отвечены, затем проверка окончания экзамена, экзамен пройден")
    @Test
    void examSuccess() {
        Mockito.when(consoleExam.wasUserLogged()).thenReturn(true);
        Mockito.when(consoleExam.hasNextExercise()).thenReturn(false);
        Mockito.when(consoleExam.currentAnswerWasTaken()).thenReturn(true);
        Mockito.when(consoleExam.wasExamPassed()).thenReturn(true);
        final String actual = (String) shell.evaluate(() -> SHORT_CUT_FOR_GET_NEXT_QUESTION_CMD);
        final String expected = "Exam already done. You passed exam";
        Assertions.assertEquals(expected, actual.trim());
    }

    @DisplayName("Все вопросы отвечены, экзамен провален")
    @Test
    void giveRightAnswerToQuestionThenCheckExamAndTellAboutExamSuccess() {
        Mockito.when(consoleExam.wasUserLogged()).thenReturn(true);
        Mockito.when(consoleExam.hasNextExercise()).thenReturn(false);
        Mockito.when(consoleExam.currentAnswerWasTaken()).thenReturn(true);
        Mockito.when(consoleExam.wasExamPassed()).thenReturn(false);
        final String actual = (String) shell.evaluate(() -> SHORT_CUT_FOR_GET_NEXT_QUESTION_CMD);
        final String expected = "Exam already done. You not passed exam";
        Assertions.assertEquals(expected, actual.trim());
    }

}