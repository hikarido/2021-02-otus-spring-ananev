package ru.otus.homework3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "exam.settings")
@ConstructorBinding
public class ExamSettingsImpl implements ExamSettings {
    private final int length;
    private final int shouldAnswerToPass;

    public ExamSettingsImpl(int length, int shouldAnswerToPass) {
        this.length = length;
        this.shouldAnswerToPass = shouldAnswerToPass;
    }

    @Override
    public int getExamLength() {
        return length;
    }

    @Override
    public int getHowManyAnswersShouldGiveStudentToPassExam() {
        return shouldAnswerToPass;
    }

    @Override
    public String toString() {
        return "Exam settings are: "
                + "length: " + length
                + "should answer: " + shouldAnswerToPass;
    }
}
