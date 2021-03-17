package ru.otus.homework3.implementations.naive.domain;

import ru.otus.homework3.core.dao.ExerciseDao;
import ru.otus.homework3.core.domain.Answer;
import ru.otus.homework3.core.domain.Exam;
import ru.otus.homework3.core.domain.Exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamImpl implements Exam {
    private final ExerciseDao exerciseDao;
    private final int howManyQuestionsMustBeQueried;
    private final int howManyRightAnswersStudentShouldGiveToPassExam;
private final Map<Exercise, List<Answer>> answersGivenByUser;

    public ExamImpl(ExerciseDao exerciseDao, int howManyQuestionsMustBeQueried, int howManyRightAnswersStudentShouldGiveToPassExam) {
        this.exerciseDao = exerciseDao;
        this.howManyQuestionsMustBeQueried = howManyQuestionsMustBeQueried;
        this.howManyRightAnswersStudentShouldGiveToPassExam =
                howManyRightAnswersStudentShouldGiveToPassExam;
        this.answersGivenByUser = new HashMap<>();
    }

    /**
     * @return list of {@link Exercise}
     * @throws IllegalArgumentException if wrong exam settings was used
     */
    @Override
    public List<Exercise> getExercises() {
        checkCorrectnessOfExamSettings(howManyQuestionsMustBeQueried, howManyRightAnswersStudentShouldGiveToPassExam);
        List<Exercise> exercises = exerciseDao.getExercises();
        if (howManyQuestionsMustBeQueried > exercises.size()) {
            throw new IllegalArgumentException("There is no such amount of exercises. " +
                    "You should require less exercises for correct behaviour");
        }
        return exercises.subList(0, howManyQuestionsMustBeQueried);
    }

    @Override
    public void answer(Exercise toExercise, List<Answer> byAnswers) {
        answersGivenByUser.put(toExercise, byAnswers);
    }

    @Override
    public boolean wasExamPassed() {
        int countOfCorrectlyAnsweredQuestions = 0;
        for (Map.Entry<Exercise, List<Answer>> entry : answersGivenByUser.entrySet()) {
            if (entry.getKey().getAnswersOfExercise().equals(entry.getValue())) {
                countOfCorrectlyAnsweredQuestions += 1;
            }
        }

        return countOfCorrectlyAnsweredQuestions >= this.howManyRightAnswersStudentShouldGiveToPassExam;
    }

    private void checkCorrectnessOfExamSettings(int howManyQuestionsMustBeQueried, int howManyRightAnswersStudentShouldGiveToPassExam) {
        if (howManyQuestionsMustBeQueried < howManyRightAnswersStudentShouldGiveToPassExam) {
            throw new IllegalArgumentException("Must be no less questions to answer then pass threshold of exam.");
        }
    }

}