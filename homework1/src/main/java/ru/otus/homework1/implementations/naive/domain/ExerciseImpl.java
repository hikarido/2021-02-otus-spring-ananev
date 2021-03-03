package ru.otus.homework1.implementations.naive.domain;

import ru.otus.homework1.core.domain.Answer;
import ru.otus.homework1.core.domain.Exercise;
import ru.otus.homework1.core.domain.Question;

import java.util.List;
import java.util.stream.Collectors;

public class ExerciseImpl implements Exercise {

    private Question question;
    private List<Answer> answers;

    public ExerciseImpl(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public List<Answer> getAnswerChoices() {
        return answers;
    }

    public List<Answer> getAnswersOfExercise() {
        return answers
                .stream()
                .filter(Answer::isRight)
                .collect(Collectors.toList());
    }
}
