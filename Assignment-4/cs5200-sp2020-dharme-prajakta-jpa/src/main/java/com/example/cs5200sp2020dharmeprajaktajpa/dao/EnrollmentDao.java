package com.example.cs5200sp2020dharmeprajaktajpa.dao;

import com.example.cs5200sp2020dharmeprajaktajpa.models.*;
import com.example.cs5200sp2020dharmeprajaktajpa.repositories.EnrollmentRepository;
import com.example.cs5200sp2020dharmeprajaktajpa.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EnrollmentDao {
    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    SectionRepository sectionRepository;

    public Boolean enrollStudentInSection(Student student, Section section) {
        if (section.getSeats() != 0) { //if a section has seats available
            Enrollment enrollment = new Enrollment();
            List<Enrollment> enrollments = new ArrayList<>();

            enrollment.setStudent(student);
            enrollment.setSection(section);
            enrollments.add(enrollment);
            section.setSeats(section.getSeats() - 1);

            if (student.getEnrollments() == null) {
                //when student is not enrolled in any section
                student.setEnrollments(enrollments);
            } else
                student.getEnrollments().add(enrollment);
            if (section.getEnrollments() == null) {
                //when section is not enrolled for any students (New section)
                section.setEnrollments(enrollments);
            } else
                section.getEnrollments().add(enrollment);

            enrollmentRepository.save(enrollment);
            sectionRepository.save(section);
            return true;
        }
        return false;
    }

    public void deleteAllEnrollments() {
        enrollmentRepository.deleteAll();
    }
}
