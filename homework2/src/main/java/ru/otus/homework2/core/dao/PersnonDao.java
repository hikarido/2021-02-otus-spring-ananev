package ru.otus.homework2.core.dao;

import ru.otus.homework2.core.domain.Person;

import java.util.Optional;

public interface PersnonDao {
    /**
     * Add person to backend. If person equals adding person is occurred
     * in backend new person will not be added
     * @param person person which will be added
     * @return true if person was added, false if person already exist,
     * therefore adding such person can involve ambiguity
     */
    boolean addPerson(Person person);

    /**
     * Search for such person in backend.
     * @param firstName first name of searching person
     * @param secondName second name of searching person
     * @return fulfilled value if such person exists in backend else empty optional will
     * be returned
     */
    Optional<Person> getPerson(String firstName, String secondName);
}
