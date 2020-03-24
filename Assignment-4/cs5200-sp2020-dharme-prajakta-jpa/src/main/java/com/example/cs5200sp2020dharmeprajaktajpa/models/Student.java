package com.example.cs5200sp2020dharmeprajaktajpa.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity(name = "student")
public class Student extends Person {
    private int grad_year;
    private long scholarship;

    @OneToMany(mappedBy = "student")
    private List<Answer> answers;

    @OneToMany(mappedBy = "student") //, fetch = FetchType.EAGER
    private List<Enrollment> enrollments;

    public Student(String username, String password, String first_name, String last_name, int grad_year, long scholarship) {
        super(username, password, first_name, last_name);
        this.grad_year = grad_year;
        this.scholarship = scholarship;
    }

    public Student() {
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public int getGrad_year() {
        return grad_year;
    }

    public void setGrad_year(int grad_year) {
        this.grad_year = grad_year;
    }

    public long getScholarship() {
        return scholarship;
    }

    public void setScholarship(long scholarship) {
        this.scholarship = scholarship;
    }
}
