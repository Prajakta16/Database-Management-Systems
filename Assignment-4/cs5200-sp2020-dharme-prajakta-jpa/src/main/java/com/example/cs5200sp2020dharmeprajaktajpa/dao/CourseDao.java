package com.example.cs5200sp2020dharmeprajaktajpa.dao;

import com.example.cs5200sp2020dharmeprajaktajpa.models.*;
import com.example.cs5200sp2020dharmeprajaktajpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseDao {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    FacultyRepository facultyRepository;

    public Course createCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    public List<Course> findAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    public Course addSectionToCourse(Section section, Course course) {
        if (course.getSections() == null) {
            //when the course has no sections (New Course)
            List<Section> sectionsForACourse = new ArrayList<>();
            sectionsForACourse.add(section);
            course.setSections(sectionsForACourse);
        } else
            course.getSections().add(section);
        if (section.getCourse() != course) {
            section.setCourse(course);
        }
        sectionRepository.save(section);
        return courseRepository.save(course);
    }

    public List<Section> findSectionForCourse(Course course) {
        if (courseRepository.existsById(course.getId()))
            return course.getSections();
        return null;
    }

    public Course setAuthorForCourse(Faculty faculty, Course course) {
        if (faculty.getAuthoredCourses() == null) {
            // If faculty never authored any other course before (New Faculty)
            List<Course> authoredCourses = new ArrayList<>();
            authoredCourses.add(course);
            faculty.setAuthoredCourses(authoredCourses);
        } else {
            faculty.getAuthoredCourses().add(course);
        }
        if (course.getFaculty() != faculty) {
            course.setFaculty(faculty);
        }
        facultyRepository.save(faculty);
        return courseRepository.save(course);
    }

    public Course findCourseByLabel(String label) {
        return courseRepository.findCourseByLabel(label);
    }

    public void deleteAllCourses() {
        courseRepository.deleteAll();
    }
}
