package school.cesar.unit.exercises.utils;

import school.cesar.unit.exercises.Person;

import java.time.LocalDate;

public class PersonBuilder {
    private String name = "John";
    private String lastName = "Doe";
    private LocalDate birthday = LocalDate.now();
    private String maritalStatus = "single";
    private String city = "Recife";
    private String state = "PE";

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public PersonBuilder setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public PersonBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public PersonBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public Person build(){
        Person person = new Person();
        person.setName(this.name);
        person.setLastName(this.lastName);
        person.setBirthday(this.birthday);
        person.setMaritalStatus(this.maritalStatus);
        person.setCity(this.city);
        person.setState(this.state);
        return person;
    }
}
