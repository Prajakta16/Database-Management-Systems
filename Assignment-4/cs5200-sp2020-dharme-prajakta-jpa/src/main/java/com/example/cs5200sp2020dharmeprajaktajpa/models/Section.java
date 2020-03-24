package com.example.cs5200sp2020dharmeprajaktajpa.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int seats;
    private String title;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "section", fetch = FetchType.EAGER)
    private List<Enrollment> enrollments;

    public Section() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Section(int seats, String title, Course course) {
        this.seats = seats;
        this.title=title;
        this.course = course;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
