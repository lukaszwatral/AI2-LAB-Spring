package com.example.springboot;

import com.example.springboot.Person;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class PeopleService {
    private List<Person> people;
    private int nextId;

    @PostConstruct
    public void init() {
        people = new ArrayList<>();
        people.add(new Person(0, "John", "Cena"));
        people.add(new Person(1, "Randy", "Orton"));
        nextId = 2;
    }

    public List<Person> getPeople() {
        return people == null ? new ArrayList<>() : people;
    }

    public Person getPerson(int id) {
        return people.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addPerson(Person person){
        if (person.getId() == null) {
            person.setId(nextId++);
            people.add(person);
        } else {
            for (int i = 0; i < people.size(); i++) {
                if (people.get(i).getId().equals(person.getId())) {
                    people.set(i, person);
                    return;
                }
            }
                people.add(person);
        }
    }

    public void setPerson(int id, Person person){
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getId() == id) {
                people.set(i, person);
                return;
            }
        }
    }

    public void removePerson(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
