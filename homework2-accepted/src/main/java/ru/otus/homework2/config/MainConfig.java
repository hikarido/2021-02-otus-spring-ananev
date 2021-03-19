package ru.otus.homework2.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework2.core.ConsoleExam;
import ru.otus.homework2.core.ResourceAccessor;
import ru.otus.homework2.core.dao.PersonDao;
import ru.otus.homework2.core.domain.Exam;
import ru.otus.homework2.implementations.naive.ConsoleExamImpl;
import ru.otus.homework2.implementations.naive.ResourceAccessorImpl;
import ru.otus.homework2.implementations.naive.dao.ExerciseDaoCsv;
import ru.otus.homework2.core.dao.ExerciseDao;
import ru.otus.homework2.implementations.naive.dao.PersonDaoImpl;
import ru.otus.homework2.implementations.naive.domain.ExamImpl;

@Configuration
@PropertySource("homework2.properties")
public class MainConfig {
    @Value("${examLength}")
    private int examLength;

    @Value("${shouldAnswerToPass}")
    private int shouldAnswerToPass;

    @Bean
    ExerciseDao exerciseDao(ResourceAccessor resourceAccessor){
        return new ExerciseDaoCsv("exercises.csv", resourceAccessor);
    }

    @Bean
    ResourceAccessor resourceAccessor(){
        return new ResourceAccessorImpl();
    }

    @Bean
    PersonDao personDao(){
        return new PersonDaoImpl();
    }

    @Bean
    Exam exam(ExerciseDao exerciseDao){
        return new ExamImpl(exerciseDao, examLength, shouldAnswerToPass);
    }

    @Bean
    ConsoleExam consoleExam(PersonDao personDao, ExerciseDao exerciseDao, Exam exam){
        return new ConsoleExamImpl(personDao, exerciseDao, exam);
    }
}
