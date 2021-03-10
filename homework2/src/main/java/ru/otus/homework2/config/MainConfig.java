package ru.otus.homework2.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import ru.otus.homework2.core.ResourceAccessor;
import ru.otus.homework2.implementations.naive.ResourceAccessorImpl;
import ru.otus.homework2.implementations.naive.dao.ExerciseDaoCsv;
import ru.otus.homework2.core.dao.ExerciseDao;

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
}
