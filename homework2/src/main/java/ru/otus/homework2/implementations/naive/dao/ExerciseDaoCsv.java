package ru.otus.homework2.implementations.naive.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import ru.otus.homework2.core.ResourceAccessor;
import ru.otus.homework2.core.CantGetAccessToResource;
import ru.otus.homework2.core.dao.CantGetExercisesException;
import ru.otus.homework2.core.dao.ExerciseDao;
import ru.otus.homework2.core.domain.Answer;
import ru.otus.homework2.core.domain.Exercise;
import ru.otus.homework2.core.domain.Question;
import ru.otus.homework2.implementations.naive.domain.AnswerImpl;
import ru.otus.homework2.implementations.naive.domain.ExerciseImpl;
import ru.otus.homework2.implementations.naive.domain.QuestionImpl;

import java.io.*;
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
    private final ResourceAccessor resourceAccessor;
    private String exerciseResourceName;
    private List<Exercise> exercises;

    public ExerciseDaoCsv(String exerciseResourceName, ResourceAccessor resourceAccessor) {
        this.resourceAccessor = resourceAccessor;
        this.exerciseResourceName = exerciseResourceName;
    }

    public List<Exercise> getExercises() {
        return load();
    }

    private List<Exercise> load() {
        try(InputStream stream = resourceAccessor.getResourceInputStream(exerciseResourceName)){
            return parseCsv(stream);
        } catch (IOException | CantGetAccessToResource e) {
            throw new CantGetExercisesException("Can't get access to file: " + exerciseResourceName, e);
        } catch (IllegalStateException e) {
            throw new CantGetExercisesException(
                    "Something wrong in content of csv file: " + exerciseResourceName, e);
        }
    }

    private List<Exercise> parseCsv(InputStream stream) throws IOException {
        try (Reader in = new BufferedReader(new InputStreamReader(stream))) {
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
