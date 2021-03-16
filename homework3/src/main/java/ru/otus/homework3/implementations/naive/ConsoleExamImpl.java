package ru.otus.homework3.implementations.naive;

import org.springframework.context.MessageSource;
import ru.otus.homework3.core.ConsoleExam;
import ru.otus.homework3.core.dao.ExerciseDao;
import ru.otus.homework3.core.dao.PersonDao;
import ru.otus.homework3.core.domain.Answer;
import ru.otus.homework3.core.domain.Exam;
import ru.otus.homework3.core.domain.Exercise;
import ru.otus.homework3.core.domain.Person;

import java.util.*;
import java.util.stream.Collectors;

public class ConsoleExamImpl implements ConsoleExam {
    private final PersonDao personDao;
    private final ExerciseDao exerciseDao;
    private final Exam exam;
    Scanner scanner = new Scanner(System.in);
    MessageSource messageSource;
    Locale locale;

    public ConsoleExamImpl(
            PersonDao personDao,
            ExerciseDao exerciseDao,
            Exam exam,
            MessageSource messageSource,
            Locale locale) {
        this.personDao = personDao;
        this.exerciseDao = exerciseDao;
        this.exam = exam;
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public void performExam() {
        Person student = getStudent();
        writeMessageToConsole(messageSource.getMessage("console.welcome", new String[]{student.toString()}, locale));
        for (Exercise exercise : exam.getExercises()) {
            writeMessageToConsole(exercise.getQuestion().getText());
            Map<Integer, Answer> sequenceNumberToAnswer = new HashMap<>();
            int number = 0;
            for (Answer answer : exercise.getAnswerChoices()) {
                sequenceNumberToAnswer.put(number, answer);
                writeMessageToConsole(number + ") " + answer.getText());
                number += 1;
            }
            List<Answer> answers = getUserAnswers(sequenceNumberToAnswer);
            exam.answer(exercise, answers);
        }

        writeMessageToConsole(messageSource.getMessage(
                "console.test-outcome",
                new String[]{student.toString(), String.valueOf(exam.wasExamPassed())},
                locale
        ));
    }

    private Person getStudent() {
        writeMessageToConsole(messageSource.getMessage("console.enter-user-name", null, locale));
        String firstName = getUserInput();
        writeMessageToConsole(messageSource.getMessage("console.enter-user-second-name", null, locale));
        String secondName = getUserInput();
        return personDao.addPerson(firstName, secondName);
    }

    private void writeMessageToConsole(String msg) {
        System.out.println(msg);
    }

    private String getUserInput() {
        writeMessageToConsole(">>> ");
        return scanner.nextLine();
    }

    private List<Answer> getUserAnswers(Map<Integer, Answer> sequenceNumberToAnswer) {
        writeMessageToConsole(messageSource.getMessage("console.note-about-plural-answer", null, locale));
        String input = getUserInput();
        List<Integer> indexes =
                Arrays.stream(input.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        List<Answer> answers = new ArrayList<>();
        for (Integer i : indexes) {
            answers.add(sequenceNumberToAnswer.get(i));
        }

        return answers;
    }

}
