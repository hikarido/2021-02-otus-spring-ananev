package ru.otus.homework2.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
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
public class MainConfig {
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
        return new ExamImpl(exerciseDao, 5, 0);
    }

    @Bean
    ConsoleExam consoleExam(PersonDao personDao, ExerciseDao exerciseDao, Exam exam){
        return new ConsoleExamImpl(personDao, exerciseDao, exam);
    }
}
