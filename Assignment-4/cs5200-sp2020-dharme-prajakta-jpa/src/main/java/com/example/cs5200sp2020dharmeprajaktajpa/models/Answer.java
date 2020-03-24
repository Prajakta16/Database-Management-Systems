package com.example.cs5200sp2020dharmeprajaktajpa.models;

import javax.persistence.*;

@Entity(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int multiple_choice_answer;
    private boolean true_false_answer;

    @OneToOne
    private Person student;

    @ManyToOne
    private Question question;

    public Answer() {
    }

    public Answer(Person student, Question question, int multiple_choice_answer, boolean true_false_answer) {
        this.student = student;
        this.question = question;
        this.multiple_choice_answer = multiple_choice_answer;
        this.true_false_answer = true_false_answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMultiple_choice_answer() {
        return multiple_choice_answer;
    }

    public void setMultiple_choice_answer(int multiple_choice_answer) {
        this.multiple_choice_answer = multiple_choice_answer;
    }

    public boolean isTrue_false_answer() {
        return true_false_answer;
    }

    public void setTrue_false_answer(boolean true_false_answer) {
        this.true_false_answer = true_false_answer;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
