package com.example.cs5200sp2020dharmeprajaktajpa.dao;

import com.example.cs5200sp2020dharmeprajaktajpa.models.*;
import com.example.cs5200sp2020dharmeprajaktajpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityDao {
    @Autowired
    PersonRepository personRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    public void truncateDatabase(){
        enrollmentRepository.deleteAll();
        sectionRepository.deleteAll();
        courseRepository.deleteAll();
        studentRepository.deleteAll();
        facultyRepository.deleteAll();
        personRepository.deleteAll();
    }
}
