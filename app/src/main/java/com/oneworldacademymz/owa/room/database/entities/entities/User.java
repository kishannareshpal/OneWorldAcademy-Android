package com.oneworldacademymz.owa.room.database.entities.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    private int id;
    private String first_name;
    private String last_name;
    private String current_grade;
    private String username;




    // Getters And Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCurrent_grade() {
        return current_grade;
    }

    public void setCurrent_grade(String current_grade) {
        this.current_grade = current_grade;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
