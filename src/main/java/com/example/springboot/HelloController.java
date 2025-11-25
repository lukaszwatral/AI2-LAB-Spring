package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/people")
@Controller
public class HelloController {
    final PeopleService peopleService;

    @Autowired
    public HelloController(PeopleService peopleService){
        this.peopleService = peopleService;
    }

    @GetMapping
    public String people(Model model) {
        model.addAttribute("people", peopleService.getPeople());
        return "people";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Person person = peopleService.getPerson(id);
        if (person == null) {
            return "redirect:/people";
        }
        model.addAttribute("person", person);
        return "show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Person person = peopleService.getPerson(id);
        if (person == null) {
            return "redirect:/people";
        }
        model.addAttribute("person", person);
        return "edit";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "edit";
    }

    @PostMapping("/save")
    public String save(Person person) {
        peopleService.addPerson(person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/remove")
    public String remove(@PathVariable int id) {
        peopleService.removePerson(id);
        return "redirect:/people";
    }

}