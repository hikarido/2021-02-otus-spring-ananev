package ru.otus.homework2.implementations.naive;

import org.springframework.context.MessageSource;
import ru.otus.homework2.core.ConsoleExam;
import ru.otus.homework2.core.dao.ExerciseDao;
import ru.otus.homework2.core.dao.PersonDao;
import ru.otus.homework2.core.domain.Answer;
import ru.otus.homework2.core.domain.Exam;
import ru.otus.homework2.core.domain.Exercise;
import ru.otus.homework2.core.domain.Person;

import java.util.*;
import java.util.stream.Collectors;

public class ConsoleExamImpl implements ConsoleExam {
    private final PersonDao personDao;
    private final Exam exam;
    private Person loggedPerson;
    private Iterator<Exercise> exerciseIterator;
    private boolean currentQuestionWasAnswered = true;
    private List<Answer> currentAnswerChoices;
    private Exercise currentExercise;

    public ConsoleExamImpl(
            PersonDao personDao,
            ExerciseDao exerciseDao,
            Exam exam,
            MessageSource messageSource,
            Locale locale) {
        this.personDao = personDao;
        this.exam = exam;
        init();
    }

    private void init() {
        exerciseIterator = exam.getExercises().iterator();
    }

    @Override
    public void login(String userFirstName, String userSecondName) {
        this.loggedPerson = personDao.addPerson(userFirstName, userSecondName);
    }

    @Override
    public boolean wasUserLogged() {
        return loggedPerson != null;
    }

    @Override
    public String getCurrentUserName() {
        if (wasUserLogged()) {
            return loggedPerson.toString();
        }
        return "";
    }

    @Override
    public boolean wasExamPassed() {
        if (hasNextExercise()) {
            return false;
        }
        return exam.wasExamPassed();
    }

    @Override
    public boolean hasNextExercise() {
        return exerciseIterator.hasNext();
    }

    @Override
    public String nextExerciseText() {
        StringBuilder builder = new StringBuilder();
        Exercise exercise = exerciseIterator.next();
        builder.append(exercise.getQuestion().getText());
        final String newLine = String.format("%n");
        builder.append(newLine);
        int answerIndex = 0;
        for (Answer answer : exercise.getAnswerChoices()) {
            builder.append(answerIndex);
            builder.append(") ");
            builder.append(answer.getText());
            builder.append(newLine);
            answerIndex++;
        }
        currentExercise = exercise;
        currentAnswerChoices = exercise.getAnswerChoices();
        currentQuestionWasAnswered = false;
        return builder.toString();
    }

    @Override
    public void setAnswerOfUser(String answer) {
        List<Integer> answersIndexes = Arrays
                .stream(answer.split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        List<Answer> chosenAnswers = new ArrayList<>();
        for(Integer index: answersIndexes){
            chosenAnswers.add(currentAnswerChoices.get(index));
        }
        exam.answer(currentExercise, chosenAnswers);
        currentQuestionWasAnswered = true;
    }

    @Override
    public boolean currentAnswerWasTaken() {
        return currentQuestionWasAnswered;
    }
}