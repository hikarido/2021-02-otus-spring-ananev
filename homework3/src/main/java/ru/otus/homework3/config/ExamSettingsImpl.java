package ru.otus.homework3.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "exam.settings")
public class ExamSettingsImpl implements ExamSettings {
    private int length;
    private int shouldAnswerToPass;

    public void setLength(int length) {
        this.length = length;
    }

    public void setShouldAnswerToPass(int shouldAnswerToPass) {
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
    public String toString(){
        return "Exam settings are: "
                + "length: " + length
                + "should answer: " + shouldAnswerToPass;
    }
}
