package com.example.cs5200sp2020dharmeprajaktajpa.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "widget")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Widget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private int height;
    private int width;
    String src; //image
    int size; //heading
    String text; //paragraph
    String items; //list
    int ordered;
    int youTubeid; //youtube
    String quiz_name; //quiz

    @ManyToOne
    private Topic topic;
    @OneToMany
    private List<Question> questions;

    public Widget() {
    }

    public Widget(String type, int height, int width, String src, int size, String text, String items, int ordered, int youTubeid, String quiz_name) { //image, paragraph, youTube
        this.type = type;
        this.height = height;
        this.width = width;
        this.src = src;
        this.size = size;
        this.text = text;
        this.items = items;
        this.ordered = ordered;
        this.youTubeid = youTubeid;
        this.quiz_name = quiz_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getOrdered() {
        return ordered;
    }

    public void setOrdered(int ordered) {
        this.ordered = ordered;
    }

    public int getYouTubeid() {
        return youTubeid;
    }

    public void setYouTubeid(int youTubeid) {
        this.youTubeid = youTubeid;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String quiz_name) {
        this.quiz_name = quiz_name;
    }
}
