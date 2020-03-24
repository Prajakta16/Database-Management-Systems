package com.example.cs5200sp2020dharmeprajaktajpa.dao;

import com.example.cs5200sp2020dharmeprajaktajpa.models.*;
import com.example.cs5200sp2020dharmeprajaktajpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyDao {
    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    PersonRepository personRepository;

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public List<Faculty> findAllFaculty() {
        return (List<Faculty>) facultyRepository.findAll();
    }

    public List<Course> findCoursesForAuthor(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId()))
            return faculty.getAuthoredCourses();
        return null;
    }

    public Faculty findFacultyByUserName(String username) {
        return facultyRepository.findFacultyByUsername(username);
    }

    public void deleteAllFaculties() {
        facultyRepository.deleteAll();
    }
}
