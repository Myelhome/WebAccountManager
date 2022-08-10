package com.MVC.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {
    private long id;

//    @NotNull(message = "Name can't be empty")
    @Size(min=2, max=30, message = "Name must be between 1 and 30 letters")
    @Pattern(regexp = "[A-Za-z]+", message = "Letters only")
    private String name;


    public Person(){}

//    public Person(String name){
//        this();
//        this.name = name;
//    }

    public Person(long id, String name) {
        this.name = name;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Person: (id: " + id + ", name: " + name + ")";
    }
}
