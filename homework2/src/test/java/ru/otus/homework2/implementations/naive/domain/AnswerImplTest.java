package ru.otus.homework2.implementations.naive.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.otus.homework2.core.domain.Answer;

class AnswerImplTest {

    @Test
    void isRightEqualsToFalseTest() {
        Answer answer = new AnswerImpl("Test", false);
        Assertions.assertFalse(answer.isRight());
    }

    @Test
    void isRightEqualsToTrueTest() {
        Answer answer = new AnswerImpl("Test", true);
        Assertions.assertTrue(answer.isRight());
    }

    @Test
    void getTextTest() {
        String expected = "Test text";
        String real = "Test text";
        Answer answer = new AnswerImpl(real, true);
        Assertions.assertEquals(real, answer.getText());
    }

    @ParameterizedTest
    // @formatter: off
    @CsvSource({
            "Yes, true, Yes, true, true",
            "Yes, true, no, true, false",
            "Yes, true, Yes, false, false",
            "here, true, there, false, false"
    })
        // @formatter: on
    void equalsTest(
            String firstAnswerText,
            String firstAnswerRightness,
            String secondAnswerText,
            String secondAnswerRightness,
            String areEqualStr
    ) {
        boolean isFirstAnswerRight = Boolean.parseBoolean(firstAnswerRightness);
        boolean isSecondAnswerRight = Boolean.parseBoolean(secondAnswerRightness);
        boolean expectedResult = Boolean.parseBoolean(areEqualStr);
        Answer firstAnswer = new AnswerImpl(firstAnswerText, isFirstAnswerRight);
        Answer secondAnswer = new AnswerImpl(secondAnswerText, isSecondAnswerRight);
        Assertions.assertEquals(expectedResult, firstAnswer.equals(secondAnswer));
    }

    @ParameterizedTest
    // @formatter: off
    @CsvSource({
            "Yes, true, Yes, true, true",
            "Yes, true, no, true, false",
            "Yes, true, Yes, false, false",
            "here, true, there, false, false"
    })
        // @formatter: on
    void testHashCode(
            String firstAnswerText,
            String firstAnswerRightness,
            String secondAnswerText,
            String secondAnswerRightness,
            String areHashCodesEqual
    ) {
        boolean isFirstAnswerRight = Boolean.parseBoolean(firstAnswerRightness);
        boolean isSecondAnswerRight = Boolean.parseBoolean(secondAnswerRightness);
        boolean expectedResult = Boolean.parseBoolean(areHashCodesEqual);
        Answer firstAnswer = new AnswerImpl(firstAnswerText, isFirstAnswerRight);
        Answer secondAnswer = new AnswerImpl(secondAnswerText, isSecondAnswerRight);
        Assertions.assertEquals(expectedResult, firstAnswer.hashCode() == secondAnswer.hashCode());
    }
}