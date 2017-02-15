package com.jaek.widgit.Models;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jaek on 22/01/17.
 */

public class User {
    private String firstname;
    private String lastname;
    private int currency;
    private String email;
    List<String> widgets;
    HashMap<String, Note> notes;
    HashMap<String, Todo> todos;

    public User(){}

    public User(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getCurrency() {
        return currency;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getWidgets() {
        return widgets;
    }

    public HashMap<String, Note> getNotes() {
        return notes;
    }

    public HashMap<String, Todo> getTodos() {
        return todos;
    }
}
