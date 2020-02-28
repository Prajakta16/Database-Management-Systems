package edu.northeastern.cs5200.models;

import java.util.Collection;
import java.util.Date;

public class Person {

    private int id;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private String email;
    private Date dob;
    private Collection<Phone> phones;
    private Collection<Address> addresses;
    private Collection<Developer> developers; //there will be just 1 developer in association with person? Is Collection still fine?
    private Collection<User> users;

    public Person(int id, String first_name, String last_name, String username, String password, String email, Date dob, Collection<Phone> phones, Collection<Address> addresses) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
        this.phones = phones;
        this.addresses = addresses;
    }

    public Person(int id, String first_name, String last_name, String username, String password, String email, Date dob) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }

    public Person(int id, String first_name, String last_name, String username, String password, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Person(int id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public Person() {
    }

    public Person(int id, String first_name, String last_name, String username, String password) {
    }


    public Collection<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Collection<Phone> phones) {
        this.phones = phones;
    }

    public Collection<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {
        this.addresses = addresses;
    }

    public Collection<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Collection<Developer> developers) {
        this.developers = developers;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
