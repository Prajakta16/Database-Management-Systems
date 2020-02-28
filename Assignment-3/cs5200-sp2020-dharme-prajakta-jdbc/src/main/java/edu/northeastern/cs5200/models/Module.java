package edu.northeastern.cs5200.models;

public enum Module {
  Project1 (1,"Project1"),
    Project2 (2,"Project2"),
    Assignment1(3,"Assignment1"),
    Assignment2 (4,"Assignment2"),
    Quiz1 (5,"Quiz1"),
    Exam (6,"Exam"),
    Logistics (7,"Logistics");


    private final int id;
    private final String module;

    Module(int id, String module) {
        this.id=id;
        this.module=module;
    }

    public int getId() {
        return id;
    }

    public String getModule() {
        return module;
    }

    public static Module getById(int id) {
        for(Module m : values()) {
            if(m.id == id) return m;
        }
        return null;
    }

}
