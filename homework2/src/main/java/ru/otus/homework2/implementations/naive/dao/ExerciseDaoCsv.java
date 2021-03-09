package ru.otus.homework2.implementations.naive.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.homework2.core.dao.CantGetExercisesException;
import ru.otus.homework2.core.dao.ExerciseDao;
import ru.otus.homework2.core.domain.Answer;
import ru.otus.homework2.core.domain.Exercise;
import ru.otus.homework2.core.domain.Question;
import ru.otus.homework2.implementations.naive.domain.AnswerImpl;
import ru.otus.homework2.implementations.naive.domain.ExerciseImpl;
import ru.otus.homework2.implementations.naive.domain.QuestionImpl;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Exercises must be stored as described below:<p>
 * name: exercises.csv<p>
 * content:<p>
 * <p>
 * "question of exercise 1","answer 1", is answer 1 is correct?, "answer 2", is answer 2
 * correct?,... <p>
 * "question of exercise 2","answer 1", is answer 1 is correct?, "answer 2", is answer 2
 * correct?,... <p>
 * ...<p>
 * "question of exercise N","answer 1", is answer 1 is correct?, "answer 2", is answer 2
 * correct?,...<p>
 * <p>
 * schema looks like this:
 * text, text, boolean, text, boolean, ..., text, boolean
 * <p>
 * sample: <p>
 * "Who was Albert Einstein?","Scientist",true,"Pipesmoker",true,"real hero",true, "boat with
 * same name",false,"book",false<p>
 * "Is London capital?","Strange question, generally speaking",true, "yes", false, "no", false<p>
 * <p>
 * Csv has no header or comments
 * <p>
 */
public final class ExerciseDaoCsv implements ExerciseDao {
    private Path pathToExercisesSource;
    private List<Exercise> exercises;

    public ExerciseDaoCsv(String exerciseResourceName) {
        try {
            URL url = getClass().getClassLoader().getResource(exerciseResourceName);
            if (url == null) {
                throw new IllegalArgumentException("Can't find resource: " + exerciseResourceName);
            }
            pathToExercisesSource = Paths.get(url.getPath());
            exercises = parseCsv(pathToExercisesSource);
        } catch (IOException | IllegalArgumentException e) {
            throw new CantGetExercisesException("Can't get access to file: " + pathToExercisesSource,
                    e);
        } catch (IllegalStateException e) {
            throw new CantGetExercisesException("Something wrong in content of csv file: " + pathToExercisesSource,
                    e);
        }
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    private List<Exercise> parseCsv(Path pathToCsv) throws IOException {
        if (!Files.isReadable(pathToCsv) || !Files.isRegularFile(pathToCsv)) {
            throw new IOException("path: " + pathToCsv.toString() + " isn't readable");
        }
        try (Reader in = Files.newBufferedReader(pathToCsv)) {
            List<Exercise> exercises = new ArrayList<>();
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {
                exercises.add(parseRecord(record));
            }
            return exercises;
        }
    }

    private Exercise parseRecord(CSVRecord record) {
        Iterator<String> iterOverRecord = record.iterator();
        String questionContent = iterOverRecord.next();
        Question question = new QuestionImpl(questionContent);
        List<Answer> answers = new ArrayList<>();
        while (iterOverRecord.hasNext()) {
            String answerContent = iterOverRecord.next();
            boolean isAnswerRight = Boolean.parseBoolean(iterOverRecord.next());
            Answer answer = new AnswerImpl(answerContent, isAnswerRight);
            answers.add(answer);
        }
        return new ExerciseImpl(question, answers);
    }
}
