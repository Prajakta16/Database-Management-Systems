package com.example.cs5200sp2020dharmeprajaktajpa.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "faculty")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Faculty extends Person {

    private String office;
    private boolean tenured;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
    private List<Course> authoredCourses;

    public Faculty(String username, String password, String first_name, String last_name, String office, boolean tenured) {
        super(username, password, first_name, last_name);
        this.office = office;
        this.tenured = tenured;
    }

    public Faculty() {
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public boolean isTenured() {
        return tenured;
    }

    public void setTenured(boolean tenured) {
        this.tenured = tenured;
    }

    public List<Course> getAuthoredCourses() {
        return authoredCourses;
    }

    public void setAuthoredCourses(List<Course> courses) {
        this.authoredCourses = courses;
    }
}
