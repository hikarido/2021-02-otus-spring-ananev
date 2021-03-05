package ru.otus.homework1.implementations.naive.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import ru.otus.homework1.core.dao.CantGetExercisesException;
import ru.otus.homework1.core.dao.ExerciseDao;
import ru.otus.homework1.core.domain.Answer;
import ru.otus.homework1.core.domain.Exercise;
import ru.otus.homework1.core.domain.Question;
import ru.otus.homework1.implementations.naive.domain.AnswerImpl;
import ru.otus.homework1.implementations.naive.domain.ExerciseImpl;
import ru.otus.homework1.implementations.naive.domain.QuestionImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDaoCsvTest {

    @Test
    void cantCreateByReasonItIsNoFile() {
        Assertions.assertThrows(CantGetExercisesException.class,
                () -> new ExerciseDaoCsv(Paths.get("."))
        );
    }

    @Test
    void cantCreateByReasonPassesFileDoesNotExist() {
        Assertions.assertThrows(CantGetExercisesException.class,
                () -> new ExerciseDaoCsv(Paths.get("/dev/null/somefile"))
        );
    }

    @Test
    void DaoShouldBeCreatedCorrectly() {
        StringBuilder builder = new StringBuilder();
        builder.append("\"question 1 with commas, and some test?\"");
        builder.append(",\"answer 1\", \"false\"");
        builder.append(",\"answer 2\", \"true\"");
        builder.append(System.lineSeparator());

        try {
            Path tempCsv = Files.createTempFile("tmp", "csv");
            try (Writer out = Files.newBufferedWriter(tempCsv)) {
                out.write(builder.toString());
            }
            ExerciseDao dao = new ExerciseDaoCsv(tempCsv);
        } catch (IOException e) {
            Assertions.fail("Can't create tmp file for test resources");
        }
    }

    @Test
    void amountOfSavedAndReadedExercisesMustBeEqual(TestInfo testInfo) {
        List<Exercise> exercises = new ArrayList<>(3);
        Question q1 = new QuestionImpl("\"question 1 with commas, and some test?\"");
        Answer q1Answer1 = new AnswerImpl("answer 1", false);
        Answer q1Answer2 = new AnswerImpl("answer 2", true);
        Exercise e1 = new ExerciseImpl(q1, List.of(q1Answer1, q1Answer2));
        exercises.add(e1);

        Question q2 = new QuestionImpl("who was Einstein ?");
        Answer q2Answer1 = new AnswerImpl("scientist", true);
        Answer q2Answer2 = new AnswerImpl("pipesmoker", true);
        Exercise e2 = new ExerciseImpl(q2, List.of(q2Answer1, q2Answer2));
        exercises.add(e2);

        Question q3 = new QuestionImpl("Is london capital?");
        Answer q3Answer1 = new AnswerImpl("yes", true);
        Answer q3Answer2 = new AnswerImpl("Isn't it a pub?", true);
        Answer q3Answer3 = new AnswerImpl("Creator of this questions a little dizzy!", true);
        Exercise e3 = new ExerciseImpl(q3, List.of(q3Answer1, q3Answer2, q3Answer3));
        exercises.add(e3);

        try {
            Path tempCsv = saveExercisesToTmpFile(exercises);
            ExerciseDao dao = new ExerciseDaoCsv(tempCsv);
            List<Exercise> haveReadExercises = dao.getExercises();
            Assertions.assertEquals(haveReadExercises.size(), exercises.size());
        } catch (IOException e) {
            Assertions.fail("Can't create tmp file for test resources");
        }
    }

    @Test
    void ReadedExercisesMustBeEqualExercisesInFile(TestInfo testInfo) {
        List<Exercise> exercises = new ArrayList<>(3);
        Question q1 = new QuestionImpl("\"question 1 with commas, and some test?\"");
        Answer q1Answer1 = new AnswerImpl("answer 1", false);
        Answer q1Answer2 = new AnswerImpl("answer 2", true);
        Exercise e1 = new ExerciseImpl(q1, List.of(q1Answer1, q1Answer2));
        exercises.add(e1);

        Question q2 = new QuestionImpl("who was Einstein ?");
        Answer q2Answer1 = new AnswerImpl("scientist", true);
        Answer q2Answer2 = new AnswerImpl("pipesmoker", true);
        Exercise e2 = new ExerciseImpl(q2, List.of(q2Answer1, q2Answer2));
        exercises.add(e2);

        Question q3 = new QuestionImpl("Is london capital?");
        Answer q3Answer1 = new AnswerImpl("yes", true);
        Answer q3Answer2 = new AnswerImpl("Isn't it a pub?", true);
        Answer q3Answer3 = new AnswerImpl("Creator of this questions a little dizzy!", true);
        Exercise e3 = new ExerciseImpl(q3, List.of(q3Answer1, q3Answer2, q3Answer3));
        exercises.add(e3);

        try {
            Path tempCsv = saveExercisesToTmpFile(exercises);
            ExerciseDao dao = new ExerciseDaoCsv(tempCsv);
            List<Exercise> haveReadExercises = dao.getExercises();
            Assertions.assertIterableEquals(exercises, haveReadExercises);
        } catch (IOException e) {
            Assertions.fail("Can't create tmp file for test resources");
        }
    }

    private Path saveExercisesToTmpFile(List<Exercise> exercises) throws IOException {
        final String prefix = "tmp";
        final String suffix = ".csv";
        Path tempCsv = Files.createTempFile(prefix, suffix);
        try (CSVPrinter printer = new CSVPrinter(
                new FileWriter(tempCsv.toFile()), CSVFormat.DEFAULT)) {
            for (Exercise e : exercises) {
                printer.print(e.getQuestion().getText());
                for (Answer a : e.getAnswerChoices()) {
                    printer.print(a.getText());
                    printer.print(a.isRight());
                }
                printer.println();
            }
        }
        return tempCsv;
    }
}