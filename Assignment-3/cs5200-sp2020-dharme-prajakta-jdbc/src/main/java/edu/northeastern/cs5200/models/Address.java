package edu.northeastern.cs5200.models;

public class Address {
    private int id;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private int zip;
    private boolean primary_address;
    private Person person;

    public Address( Person person,String street1, String city, int zip, boolean primary_address) {
        this.street1 = street1;
        this.city = city;
        this.zip = zip;
        this.primary_address = primary_address;
        this.person = person;
    }

    public Address(int id, String street1, String street2, String city, String state, int zip, boolean primary_address, Person person) {
        this.id = id;
        this.street1 = street1;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.primary_address = primary_address;
        this.person = person;
    }

    public Address(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public boolean isprimary_address() {
        return primary_address;
    }

    public void setPrimary_address(boolean primary) {
        this.primary_address = primary;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
