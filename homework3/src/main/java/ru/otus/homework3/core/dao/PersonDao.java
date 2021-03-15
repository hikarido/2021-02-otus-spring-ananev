package ru.otus.homework3.core.dao;

import ru.otus.homework3.core.domain.Person;

import java.util.Optional;

public interface PersonDao {
    /**
     * create new person in backend
     *
     * @param firstName  firstName
     * @param secondName secondName
     * @return newly created person
     */
    Person addPerson(String firstName, String secondName);

    /**
     * Search for such person in backend.
     *
     * @param firstName  first name of searching person
     * @param secondName second name of searching person
     * @return fulfilled value if such person exists in backend else empty optional will
     * be returned
     */
    Optional<Person> getPerson(String firstName, String secondName);
}
