package ru.otus.homework3.implementations.naive.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.otus.homework3.core.domain.Question;

class QuestionImplTest {
    @Test
    void createTest() {
        Question text = new QuestionImpl("Text");
    }

    @Test
    void getTestTest() {
        String expected = "Text";
        Assertions.assertEquals(expected, new QuestionImpl(expected).getText());
    }

    @ParameterizedTest
    @CsvSource({"text, text, true", "FirstLiterInAppear, firstLiterInAppear, false", "text, otherText, false"})
    void equalsTest(String firstText, String secondText, String areEqualsStr) {
        boolean areEquals = Boolean.parseBoolean(areEqualsStr);
        Question firstQuestion = new QuestionImpl(firstText);
        Question secondQuestion = new QuestionImpl(secondText);
        Assertions.assertEquals(areEquals, firstQuestion.equals(secondQuestion));
    }


    @CsvSource({"text, text, true", "FirstLiterInAppear, firstLiterInAppear, false", "text, otherText, false"})
    void hashCodeTest(String firstText, String secondText, String areHashCodesEqualStr) {
        boolean areEquals = Boolean.parseBoolean(areHashCodesEqualStr);
        Question firstQuestion = new QuestionImpl(firstText);
        Question secondQuestion = new QuestionImpl(secondText);
        Assertions.assertEquals(areEquals, firstQuestion.hashCode() == secondQuestion.hashCode());
    }
}