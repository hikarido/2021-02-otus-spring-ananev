package ru.otus.homework3.implementations.naive.domain;

import ru.otus.homework3.core.domain.Person;

import java.util.Objects;

public class PersonImpl implements Person {
    private final String firstName;
    private final String secondName;

    public PersonImpl(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getSecondName() {
        return secondName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if(getClass() != other.getClass()){
            return false;
        }

        Person p = (Person) other;
        return getFirstName().equals(p.getFirstName()) & getSecondName().equals(p.getSecondName());
    }

    @Override
    public int hashCode(){
        return Objects.hash(firstName, secondName);
    }

    @Override
    public String toString(){
        return firstName + " " + secondName;
    }
}
