package ru.otus.homework1.implementations.naive.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.homework1.core.dao.CantGetExercisesException;
import ru.otus.homework1.core.dao.ExerciseDao;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    void RedExercisesMustBeEqualExercisesInFile() {
        StringBuilder builder = new StringBuilder();
        builder.append("\"question 1 with commas, and some test?\"");
        builder.append(",\"answer 1\", \"false\"");
        builder.append(",\"answer 2\", \"true\"");
        builder.append(System.lineSeparator());

        builder.append("\"Who was Einstein ?\"");
        builder.append(",\"Scientist\", \"true\"");
        builder.append(",\"Pipe smoker?\", \"true\"");
        builder.append(System.lineSeparator());

        builder.append("\"Is london capital?\"");
        builder.append(",\"yes\", \"true\"");
        builder.append(",\"Isn't it a pub?\", \"true\"");
        builder.append(",\"Creator of this questions a little dizzy!\", \"true\"");
        builder.append(",\"\", \"true\"");
        builder.append(System.lineSeparator());

        try {
            Path tempCsv = Files.createTempFile("tmp", "csv");
            try (Writer out = Files.newBufferedWriter(tempCsv)) {
                out.write(builder.toString());
            }
            ExerciseDao dao = new ExerciseDaoCsv(tempCsv);
            // TODO how will you compare saved exercises with have read exercises?
        } catch (IOException e) {
            Assertions.fail("Can't create tmp file for test resources");
        }
    }
}