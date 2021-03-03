package ru.otus.homework1.implementations.naive.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.homework1.core.dao.CantGetExercisesException;
import ru.otus.homework1.core.dao.ExerciseDao;
import ru.otus.homework1.core.domain.Exercise;
import ru.otus.homework1.core.domain.Question;
import ru.otus.homework1.implementations.naive.domain.QuestionImpl;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.io.Reader;
import java.nio.file.Files;

/**
 * Exercises must be stored as described below:
 * name: exercises.csv
 * content:
 *
 * "question of exercise 1","answer 1", is answer 1 is correct?, "answer 2", is answer 2 correct?,...
 * "question of exercise 2","answer 1", is answer 1 is correct?, "answer 2", is answer 2 correct?,...
 * ...
 * "question of exercise N","answer 1", is answer 1 is correct?, "answer 2", is answer 2 correct?,...
 *
 * schema looks like this:
 * text, text, boolean, text, boolean, ..., text, boolean
 *
 * sample:
 * "Who was Albert Einstein?","Scientist",true,"Pipesmoker",true,"real hero",true, "boat with same name",false,"book",false
 * "Is London capital?","Strange question, generally speaking",true, "yes", false, "no", false
 *
 * Csv has no header or comments
 */
public final class ExerciseDaoCsv implements ExerciseDao {
    private Path pathToExercisesSource;
    private List<Exercise> exercises;

    public ExerciseDaoCsv(Path pathToExercisesSource) {
        this.pathToExercisesSource = pathToExercisesSource;
        try {
            exercises = parseCsv(pathToExercisesSource);
        }
        catch (IOException e){
            CantGetExercisesException toThrow = new CantGetExercisesException();
            toThrow.initCause(e);
            throw toThrow;
        }
    }

    public List<Exercise> getExercises() {
        return null;
    }

    private List<Exercise> parseCsv(Path pathToCsv) throws IOException {
        try(Reader in = Files.newBufferedReader(pathToCsv)){
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for(CSVRecord record: records){
                // TODO stayed here
            }
        }

        return Collections.emptyList();
    }
}
