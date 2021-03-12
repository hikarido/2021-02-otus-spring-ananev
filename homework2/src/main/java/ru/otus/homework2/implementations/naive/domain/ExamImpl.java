package ru.otus.homework2.implementations.naive.domain;

import ru.otus.homework2.core.dao.ExerciseDao;
import ru.otus.homework2.core.domain.Answer;
import ru.otus.homework2.core.domain.Exam;
import ru.otus.homework2.core.domain.Exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamImpl implements Exam {
    private final ExerciseDao exerciseDao;
    private final int howManyQuestionsMustBeQueried;
    private final int howManyRightAnswersStudentShouldGiveToPassExam;
    private final HashMap<Exercise, List<Answer>> answersGivenByUser;

    public ExamImpl(ExerciseDao exerciseDao, int howManyQuestionsMustBeQueried, int howManyRightAnswersStudentShouldGiveToPassExam) {
        this.exerciseDao = exerciseDao;
        this.howManyQuestionsMustBeQueried = howManyQuestionsMustBeQueried;
        this.howManyRightAnswersStudentShouldGiveToPassExam =
                howManyRightAnswersStudentShouldGiveToPassExam;
        this.answersGivenByUser = new HashMap<>();
    }

    @Override
    public List<Exercise> getExercises() {
        return exerciseDao.getExercises().subList(0, howManyQuestionsMustBeQueried);
    }

    @Override
    public void answer(Exercise toExercise, List<Answer> byAnswers) {
        answersGivenByUser.put(toExercise, byAnswers);
    }

    @Override
    public boolean wasExamPassed() {
        int countOfCorrectlyAnsweredQuestions = 0;
        for(Map.Entry<Exercise, List<Answer>> entry: answersGivenByUser.entrySet()){
            if(entry.getKey().getAnswersOfExercise().equals(entry.getValue())){
                countOfCorrectlyAnsweredQuestions += 1;
            }
        }

        return countOfCorrectlyAnsweredQuestions >= this.howManyRightAnswersStudentShouldGiveToPassExam;
    }
}