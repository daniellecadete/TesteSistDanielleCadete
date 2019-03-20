package school.cesar.unit.exercises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import school.cesar.unit.exercises.utils.PersonBuilder;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PeopleComparatorTest {

    PeopleComparator peopleComparator;
    PersonBuilder personBuilder;
    LocalDate now = LocalDate.now();

    @BeforeEach
    public void setUpTest(){
        peopleComparator = new PeopleComparator(){
            @Override
            public LocalDate getCurrentDate(){
                return now;
            }
        };
        personBuilder = new PersonBuilder();
    }

    @Test
    public void todayIsThePersonBirthDay(){
        Person person = personBuilder
                .setBirthday(now)
                .build();
        assertTrue(peopleComparator.isTodayPersonsBirthDay(person));
    }

    @Test
    public void todayIsNotThePersonBirthDay(){
        Person person = personBuilder
                .setBirthday(now.minusDays(1))
                .build();
        assertFalse(peopleComparator.isTodayPersonsBirthDay(person));
    }

    @Test
    public void leapYearPersonBirthDayOnNonLeapYear(){
        now = LocalDate.of(2018, Month.MARCH, 1);
        LocalDate birthday = LocalDate.of(1984, Month.FEBRUARY, 29);
        Person person = personBuilder
                .setBirthday(birthday)
                .build();
        assertTrue(peopleComparator.isTodayPersonsBirthDay(person));
    }

    @Test
    public void peopleWithSameInformation(){
        Person firstPerson = personBuilder
                .build();
        Person secondPerson = personBuilder
                .build();
        assertFalse(peopleComparator.isSameFamily(firstPerson, secondPerson));
    }

    @Test
    public void peopleWithSameInformationButName(){
        Person firstPerson = personBuilder
                .build();
        Person secondPerson = personBuilder
                .setName("Ada")
                .build();
        assertTrue(peopleComparator.isSameFamily(firstPerson, secondPerson));
    }

    @Test
    public void peopleWithSameInformationButNameAndLastName(){
        Person firstPerson = personBuilder
                .build();
        Person secondPerson = personBuilder
                .setName("Ada")
                .setLastName("Lovelace")
                .build();
        assertFalse(peopleComparator.isSameFamily(firstPerson, secondPerson));
    }

    @Test
    public void peopleWithSameInformationButNameAndCity(){
        Person firstPerson = personBuilder
                .build();
        Person secondPerson = personBuilder
                .setName("Ada")
                .setCity("London")
                .build();
        assertFalse(peopleComparator.isSameFamily(firstPerson, secondPerson));
    }

    @Test
    public void peopleWithSameInformationButNameAndState(){
        Person firstPerson = personBuilder
                .build();
        Person secondPerson = personBuilder
                .setName("Ada")
                .setState("MG")
                .build();
        assertFalse(peopleComparator.isSameFamily(firstPerson, secondPerson));
    }

    @Test
    public void twoPeopleWithSameInformation(){
        Person firstPerson = personBuilder.build();
        Person secondPerson = personBuilder.build();
        assertTrue(peopleComparator.isSamePerson(firstPerson, secondPerson));
    }

    @Test
    public void twoPeopleWithSameObjectInstance(){
        Person person = personBuilder.build();
        assertThrows(RuntimeException.class, () -> peopleComparator.isSamePerson(person, person));
    }

    @Test
    public void twoPeopleWithSameInformationButName(){
        Person firstPerson = personBuilder.build();
        Person secondPerson = personBuilder
                .setName("Ada")
                .build();
        assertFalse(peopleComparator.isSamePerson(firstPerson, secondPerson));
    }

    @Test
    public void twoPeopleWithSameInformationButLastName(){
        Person firstPerson = personBuilder.build();
        Person secondPerson = personBuilder
                .setLastName("Lovelace")
                .build();
        assertFalse(peopleComparator.isSamePerson(firstPerson, secondPerson));
    }

    @Test
    public void twoPeopleWithSameInformationButBirthday(){
        Person firstPerson = personBuilder.build();
        Person secondPerson = personBuilder
                .setBirthday(now.minusDays(1))
                .build();
        assertFalse(peopleComparator.isSamePerson(firstPerson, secondPerson));
    }

    @Test
    public void twoPeopleWithSameInformationButMaritalStatus(){
        Person firstPerson = personBuilder.build();
        Person secondPerson = personBuilder
                .setMaritalStatus("married")
                .build();
        assertFalse(peopleComparator.isSamePerson(firstPerson, secondPerson));
    }

    @Test
    public void twoPeopleWithSameInformationButCity(){
        Person firstPerson = personBuilder.build();
        Person secondPerson = personBuilder
                .setCity("London")
                .build();
        assertFalse(peopleComparator.isSamePerson(firstPerson, secondPerson));
    }

    @Test
    public void twoPeopleWithSameInformationButState(){
        Person firstPerson = personBuilder.build();
        Person secondPerson = personBuilder
                .setState("MG")
                .build();
        assertFalse(peopleComparator.isSamePerson(firstPerson, secondPerson));
    }
}
