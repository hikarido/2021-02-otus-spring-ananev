package ru.otus.homework3.implementations.naive.domain;

import ru.otus.homework3.core.domain.Answer;
import ru.otus.homework3.core.domain.Exercise;
import ru.otus.homework3.core.domain.Question;

import java.util.List;
import java.util.Objects;
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

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this.getClass() != other.getClass()) {
            return false;
        }

        Exercise o = (Exercise) other;
        return this.getQuestion().equals(o.getQuestion())
                & this.getAnswerChoices().equals(o.getAnswerChoices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, answers);
    }
}
