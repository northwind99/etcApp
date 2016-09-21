package com.evan.etcweb.entities;

/**
 * Created by Evan on 2016-09-20.
 */
public class Contact {

    int id;
    String name;
    String title;
    String phone;
    String email;

    public Contact(int id, String name, String title, String phone, String email) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
