package com.example.cs5200sp2020dharmeprajaktajpa.repositories;

import com.example.cs5200sp2020dharmeprajaktajpa.models.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends CrudRepository<Section, Integer> {

    @Query("SELECT s FROM section s where s.title=:title")
    public Section findSectionByTitle(@Param("title") String title);
}

