package com.example.cs5200sp2020dharmeprajaktajpa.models;

import javax.persistence.*;

@Entity(name = "question")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String question;
    private int points;
    private String type;

    @ManyToOne
    private Widget widget;

    public Question() {
    }

    public Question(String question, int points, Widget widget, String type) {
        this.question = question;
        this.points = points;
        this.widget = widget;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getPoints() {
        return points;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }
}
