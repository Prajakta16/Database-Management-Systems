package edu.northeastern.cs5200.models;


import java.util.Collection;
import java.util.Date;

public class Developer extends Person{
    private int id;
    private String developer_key;
    private Person person;

    public Developer(int id, String developer_key) {
        this.id = id;
        this.developer_key = developer_key;
    }

    public Developer(){

    }

    public Developer(int id, String first_name, String last_name, String username, String password, String email, String developer_key, Person person) {
        super(id, first_name, last_name, username, password, email);
        this.id = id;
        this.developer_key = developer_key;
        this.person=person;
    }

    public Developer(int id, String developer_key, Person person) {
        this.id = id;
        this.developer_key = developer_key;
        this.person=person;
    }

    public Developer(int id, String first_name, String last_name, String username, String password, String email, String developer_key) {
        super(id, first_name, last_name, username, password, email);
        this.id = id;
        this.developer_key = developer_key;
    }

    public Developer(int id, String first_name, String last_name, String username, String password, String email, Date dob, String developer_key) {
        super(id, first_name, last_name, username, password, email, dob);
        this.id = id;
        this.developer_key = developer_key;
    }

    public Developer(int id, String first_name, String last_name, String username, String password, String email, Date dob, Collection<Phone> phones, Collection<Address> addresses, String developer_key) {
        super(id, first_name, last_name, username, password, email, dob, phones, addresses);
        this.id = id;
        this.developer_key = developer_key;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeveloper_key() {
        return developer_key;
    }

    public void setDeveloper_key(String developer_key) {
        this.developer_key = developer_key;
    }
}
