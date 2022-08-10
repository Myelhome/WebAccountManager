package com.MVC.controller;

import com.MVC.dao.PersonDAO;
import com.MVC.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.DirectoryStream;
import java.util.Objects;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonDAO personDAO;

    @GetMapping
    public String index(Model model){

        model.addAttribute("persons", personDAO.index());

        return "person/all_persons.html";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){

        model.addAttribute("person", personDAO.show(id));

        return "person/one_person.html";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id){

        model.addAttribute("person", personDAO.show(id));

        return "person/edit.html";
    }

    @GetMapping("/new")
    public String newPerson(Model model){

        model.addAttribute("person", new Person());

        return "person/new.html";
    }

    @PostMapping()
    public String put(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){

        if(bindingResult.hasErrors()) return "person/new.html";

        personDAO.put(personDAO.getPersonId()+1, person.getName());

        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){

        if (bindingResult.hasErrors()) return "person/edit.html";

        personDAO.update(id, person);

        return "redirect:/people/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){

        personDAO.delete(id);

        return "redirect:/people";
    }

}
