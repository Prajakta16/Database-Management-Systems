package com.example.cs5200sp2020dharmeprajaktajpa.repositories;

import com.example.cs5200sp2020dharmeprajaktajpa.models.Course;
import com.example.cs5200sp2020dharmeprajaktajpa.models.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Integer> {

    @Query("SELECT f FROM faculty f where f.username=:username ")
    public Faculty findFacultyByUsername(@Param("username") String username);

}
