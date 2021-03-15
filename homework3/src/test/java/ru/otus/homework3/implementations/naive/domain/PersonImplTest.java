package ru.otus.homework3.implementations.naive.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.otus.homework3.core.domain.Person;

class PersonImplTest {

    @Test
    void createTest() {
        new PersonImpl("a", "b");
    }

    @Test
    void getFirstName() {
        String firstName = "Max";
        Person person = new PersonImpl(firstName, "...");
        Assertions.assertEquals(firstName, person.getFirstName());
    }

    @Test
    void getSecondName() {
        String secondName = "Mad";
        Person person = new PersonImpl("...", secondName);
        Assertions.assertEquals(secondName, person.getSecondName());
    }

    @ParameterizedTest
    // formatting: off
    @CsvSource({
            "a, a, a, a, true",
            "a, a, a, b, false",
            "a, a, b, a, false",
            "a, a, b, b, false",
            "a, b, a, a, false",
            "a, b, a, b, true",
            "a, b, b, a, false",
            "a, b, b, b, false",
            "b, a, a, a, false",
            "b, a, a, b, false",
            "b, a, b, a, true",
            "b, a, b, b, false",
            "b, b, a, a, false",
            "b, b, a, b, false",
            "b, b, b, a, false",
            "b, b, b, b, true",
    })
        // formatting: on
    void equalsTest(String aFirstName, String aSecondName, String bFirstName,
                    String bSecondName, String areEqualStr) {
        boolean expected = Boolean.parseBoolean(areEqualStr);
        Person personA = new PersonImpl(aFirstName, aSecondName);
        Person personB = new PersonImpl(bFirstName, bSecondName);
        boolean real = personA.equals(personB);
        Assertions.assertEquals(expected, real);
    }

    @ParameterizedTest
    // formatting: off
    @CsvSource({
            "a, a, a, a, true",
            "a, a, a, b, false",
            "a, a, b, a, false",
            "a, a, b, b, false",
            "a, b, a, a, false",
            "a, b, a, b, true",
            "a, b, b, a, false",
            "a, b, b, b, false",
            "b, a, a, a, false",
            "b, a, a, b, false",
            "b, a, b, a, true",
            "b, a, b, b, false",
            "b, b, a, a, false",
            "b, b, a, b, false",
            "b, b, b, a, false",
            "b, b, b, b, true",
    })
        // formatting: on
    void hashCodeTest(String aFirstName, String aSecondName, String bFirstName,
                      String bSecondName, String areEqualHashCodeStr) {
        boolean expected = Boolean.parseBoolean(areEqualHashCodeStr);
        int hashOfA = new PersonImpl(aFirstName, aSecondName).hashCode();
        int hashOfB = new PersonImpl(bFirstName, bSecondName).hashCode();
        boolean real = hashOfA == hashOfB;
        Assertions.assertEquals(expected, real);
    }
}