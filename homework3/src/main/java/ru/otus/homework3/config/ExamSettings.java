package ru.otus.homework3.config;

public interface ExamSettings {
    /**
     * @return how many questions will be asked through exam
     */
    int getExamLength();

    /**
     * @return value less or equals to {@link ExamSettings#getExamLength}
     */
    int getHowManyAnswersShouldGiveStudentToPassExam();
}
