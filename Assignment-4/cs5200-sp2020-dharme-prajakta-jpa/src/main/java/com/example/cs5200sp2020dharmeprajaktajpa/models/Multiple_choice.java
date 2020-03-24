package com.example.cs5200sp2020dharmeprajaktajpa.models;

import javax.persistence.*;

@Entity(name = "multiple_choice")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Multiple_choice extends Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String choices;
    private int correct;

    public Multiple_choice() {
    }

    public Multiple_choice(String question, int points, Widget widget, String type, String choices, int correct) {
        super(question, points, widget, type);
        this.correct = correct;
        this.choices = choices;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }
}
