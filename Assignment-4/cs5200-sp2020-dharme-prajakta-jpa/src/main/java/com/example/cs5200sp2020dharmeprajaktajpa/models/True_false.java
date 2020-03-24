package com.example.cs5200sp2020dharmeprajaktajpa.models;

import javax.persistence.*;

@Entity(name = "true_false")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class True_false extends Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean is_true;

    public True_false() {
    }

    public True_false(String question, int points, String type, Widget widget, boolean is_true) {
        super(question, points, widget, type);
        this.is_true = is_true;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs_true() {
        return is_true;
    }

    public void setIs_true(boolean is_true) {
        this.is_true = is_true;
    }
}
