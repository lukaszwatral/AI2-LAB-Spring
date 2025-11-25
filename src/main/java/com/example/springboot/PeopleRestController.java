package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
public class PeopleRestController {
    private final PeopleService peopleService;

    @Autowired
    public PeopleRestController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPeople() {
        List<Person> people = peopleService.getPeople();
        return ResponseEntity.ok(people);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable int id) {
        Person person = peopleService.getPerson(id);
        if (person == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Person not found");
        }
        return ResponseEntity.ok(person);
    }

    @PostMapping("/add")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        peopleService.addPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<?> updatePerson(@PathVariable int id, @RequestBody Person updatedPerson) {
        Person existing = peopleService.getPerson(id);
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Person not found");
        }
        updatedPerson.setId(id);
        peopleService.setPerson(id, updatedPerson);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}/remove")
    public ResponseEntity<?> removePerson(@PathVariable int id) {
        Person person = peopleService.getPerson(id);
        if (person == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Person not found");
        }
        peopleService.removePerson(id);
        return ResponseEntity.noContent().build();
    }
}
