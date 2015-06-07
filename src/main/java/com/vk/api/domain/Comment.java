package com.vk.api.domain;

/**
 * Created by Денис on 07.06.2015. Ave furby!
 */
public class Comment {

    private int id;
    private String firstName;
    private String lastName;
    private String text;

    public Comment(int id, String lastName, String firstName, String text) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
