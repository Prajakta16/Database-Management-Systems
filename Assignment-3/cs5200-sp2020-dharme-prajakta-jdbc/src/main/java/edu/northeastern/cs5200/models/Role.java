package edu.northeastern.cs5200.models;

public enum Role {
    owner(1,"owner"),
    admin(2,"admin"),
    writer(3,"writer"),
    reviewer(4,"reviewer"),
    editor(5,"editor");

    private final int id;
    private final String role;

    Role(int id, String role) {
        this.id=id;
        this.role=role;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public static Role getById(int id) {
        for(Role r : values()) {
            if(r.id == id) return r;
        }
        return null;
    }

    public static int getIdByRole(String role_name) {
        return Role.valueOf(role_name).getId();
    }

}
