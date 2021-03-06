package com.example.cs5200sp2020dharmeprajaktajpa.repositories;

import com.example.cs5200sp2020dharmeprajaktajpa.models.Enrollment;
import com.example.cs5200sp2020dharmeprajaktajpa.models.Section;
import com.example.cs5200sp2020dharmeprajaktajpa.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query("SELECT s FROM student s where s.username=:username")
    public Student findStudentByUsername(@Param("username") String username);

    @Query("SELECT e FROM enrollment e WHERE e.student=:student")
    public List<Enrollment> findEnrollmentsForStudent(@Param("student")Student student);
}
