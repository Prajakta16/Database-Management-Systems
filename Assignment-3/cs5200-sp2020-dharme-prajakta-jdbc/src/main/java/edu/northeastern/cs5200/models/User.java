package edu.northeastern.cs5200.models;

public class User extends Person{
    private int id;
    private Boolean user_agreement;
    private Person person;

    public User(int id, Boolean user_agreement, Person person) {
        this.id = id;
        this.user_agreement = user_agreement;
        this.person = person;
    }

    public User(int id, String first_name, String last_name, Boolean user_agreement, Person person) {
        super(id, first_name, last_name);
        this.id = id;
        this.user_agreement = user_agreement;
        this.person = person;
    }

    public User() {
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

    public Boolean getUser_agreement() {
        return user_agreement;
    }

    public void setUser_agreement(Boolean user_agreement) {
        this.user_agreement = user_agreement;
    }

}
