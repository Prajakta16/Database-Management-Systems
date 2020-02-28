package edu.northeastern.cs5200.daos;

import edu.northeastern.cs5200.models.*;

import java.util.Collection;

public interface DeveloperDao {

    void createDeveloper(Developer developer);

    Collection<Developer> findAllDevelopers();

    Developer findDeveloperById(int developerId);

    Developer findDeveloperByUsername(String username);

    Developer findDeveloperByCredentials(String username, String password);

    int updateDeveloper(int developerId, Developer developer);

    int deleteDeveloper(int developerId);

    public void insertPhoneForDeveloper(Developer developer, Phone phone);

    int updatePhoneForDeveloper(Developer developer, Phone updatedPhone);

    public void insertAddressForDeveloper(Developer developer, Address address);

    int deletePrimaryAddressForDeveloper(Developer developer);

}
