package com.jaek.widgit.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jaek on 22/01/17.
 */

public class User {
    private String firstname;
    private String lastname;
    private String country;
    private String city;
    private int currency;
    private String email;
    List<String> widgets;
    HashMap<String, Note> notes;
    HashMap<String, Todo> todos;

    public User(){}

    public User(String firstname, String lastname, String email, String country, String city) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.country = country;
        this.city = city;
        this.currency = 600;
        this.widgets = new ArrayList<String>();
        this.notes = new HashMap<String, Note>();
        this.todos = new HashMap<String, Todo>();
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

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public void setWidgets(List<String> widgets) {
        this.widgets = widgets;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNotes(HashMap<String, Note> notes) {
        this.notes = notes;
    }

    public void setTodos(HashMap<String, Todo> todos) {
        this.todos = todos;
    }
}
