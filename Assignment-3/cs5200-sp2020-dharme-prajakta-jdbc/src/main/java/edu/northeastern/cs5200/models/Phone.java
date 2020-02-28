package edu.northeastern.cs5200.models;

public class Phone {
    private int id;
    private String phone;
    private boolean primary;
    private Person person;

    public Phone(int id, String phone, boolean primary, Person person) {
        this.id = id;
        this.phone = phone;
        this.primary = primary;
        this.person = person;
    }

    public Phone(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }



}
