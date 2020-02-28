package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.Developer;
import edu.northeastern.cs5200.models.Person;

public interface PersonDao {

    void createPerson(Person person);
    public Person findPersonById(int personId);
}
