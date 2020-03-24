package com.example.cs5200sp2020dharmeprajaktajpa.dao;

import com.example.cs5200sp2020dharmeprajaktajpa.models.*;
import com.example.cs5200sp2020dharmeprajaktajpa.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionDao {
    @Autowired
    SectionRepository sectionRepository;

    public Section createSection(Section section){
        return sectionRepository.save(section);
    }

    public List<Section> findAllSections(){
        return (List<Section>) sectionRepository.findAll();
    }

    public List<Student> findStudentsInSection(Section section){
        List<Enrollment> enrollments= new ArrayList<Enrollment>();
        List<Student> students= new ArrayList<>();

        if(sectionRepository.existsById(section.getId())) {
            //if the given section exists
            enrollments = section.getEnrollments();
            for (Enrollment e : enrollments)
                students.add(e.getStudent());
            return students;
        }
        return null;
    }

    public Section findSectionByTitle(String title){
        return sectionRepository.findSectionByTitle(title);
    }

    public void deleteAllSections(){
        sectionRepository.deleteAll();
    }
}
