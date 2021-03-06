package ru.otus.homework2.implementations.naive.dao;

import ru.otus.homework2.core.dao.PersonDao;
import ru.otus.homework2.core.domain.Person;
import ru.otus.homework2.implementations.naive.domain.PersonImpl;

import java.util.HashSet;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {
    private HashSet<Person> persons;

    public PersonDaoImpl() {
        this.persons = new HashSet<>();
    }

    @Override
    public Person addPerson(String firstName, String secondName) {
        Person person = new PersonImpl(firstName, secondName);
        persons.add(person);
        return person;
    }

    @Override
    public Optional<Person> getPerson(String firstName, String secondName) {
        Person toFind = new PersonImpl(firstName, secondName);
        if(persons.contains(toFind)){
            return Optional.of(toFind);
        }
        return Optional.empty();
    }
}
