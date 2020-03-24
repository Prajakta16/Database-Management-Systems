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
@Transactional
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
        List<Section> sections = new ArrayList<>();
        List<Enrollment> enrollments = new ArrayList<Enrollment>();
        enrollments = studentRepository.findEnrollmentsForStudent(student);

        for (Enrollment e : enrollments)
        {
            sections.add(e.getSection());
        }
        for (Section s : sections)
            System.out.println(s.getTitle());
        return sections;
    }

    public Student findStudentByUsername(String username){
        return studentRepository.findStudentByUsername(username);
    }

    public void deleteAllStudents(){
        studentRepository.deleteAll();
    }

}
