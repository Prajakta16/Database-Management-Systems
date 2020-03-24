package com.example.cs5200sp2020dharmeprajaktajpa.dao;

import com.example.cs5200sp2020dharmeprajaktajpa.models.*;
import com.example.cs5200sp2020dharmeprajaktajpa.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentDao {
    @Autowired
    StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    public List<Section> findSectionsForStudent(Student student) {
        List<Enrollment> enrollments = new ArrayList<Enrollment>();
        List<Section> sections = new ArrayList<>();

        if (studentRepository.existsById(student.getId())) {
            //if the student exists
            enrollments = student.getEnrollments();
            for (Enrollment e : enrollments)
            {
                sections.add(e.getSection());
            }
            return sections;
        }
        return null;
    }

    public Student findStudentByUsername(String username){
        return studentRepository.findStudentByUsername(username);
    }

    public void deleteAllStudents(){
        studentRepository.deleteAll();
    }

}
