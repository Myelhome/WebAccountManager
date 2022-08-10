package com.MVC.dao;

import com.MVC.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM people", new PersonMapper());
    }

    public Person show(long id) {
        return jdbcTemplate.query("SELECT * FROM people WHERE id = ?", new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public void put(long id, String name) {
        jdbcTemplate.update("INSERT INTO PEOPLE (id, name) VALUES(?,?)", id, name);
    }

    public int getPersonId(){
        return jdbcTemplate.query("SELECT MAX(id) FROM PEOPLE", new PersonIdMapper()).stream().findAny().orElse(0);
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE PEOPLE SET name = ? WHERE id = ?", person.getName(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM people WHERE id = ?", id);
    }
}
