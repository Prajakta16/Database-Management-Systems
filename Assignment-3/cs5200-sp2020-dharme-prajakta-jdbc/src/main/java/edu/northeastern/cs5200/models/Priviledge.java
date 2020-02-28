package edu.northeastern.cs5200.models;

public enum Priviledge {
    owner(1,"create"),
    admin(2,"read"),
    writer(3,"update"),
    reviwer(4,"delete");

    private final int id;
    private final String role;

    Priviledge(int id, String role) {
        this.id=id;
        this.role=role;
    }

    public int getId() {
        return id;
    }

    public String getPriviledge() {
        return role;
    }

    public static Priviledge getById(int id) {
        for(Priviledge p : values()) {
            if(p.id == id) return p;
        }
        return null;
    }

    public static int getIdByPriviledge(String priviledge_name) {
        return Role.valueOf(priviledge_name).getId();
    }

}
