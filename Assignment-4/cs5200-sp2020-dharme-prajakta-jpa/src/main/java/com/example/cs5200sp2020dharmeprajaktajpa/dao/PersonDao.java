package com.example.cs5200sp2020dharmeprajaktajpa.dao;

import com.example.cs5200sp2020dharmeprajaktajpa.models.Person;
import com.example.cs5200sp2020dharmeprajaktajpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonDao {
    @Autowired
    PersonRepository personRepository;

    public List<Person> findAllUsers(){
        return (List<Person>) personRepository.findAll();
    }

    public void deleteAllPersons(){
        personRepository.deleteAll();
    }
}
