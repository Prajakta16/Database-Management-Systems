package edu.northeastern.cs5200.models;

public enum Type {
    heading(1,"heading"),
    html(2,"html"),
    youTube(3,"youTube"),
    image(4,"image"),
    question(5,"question"),
    answer(6,"answer");

    private final int id;
    private final String type;

    Type(int id, String type) {
        this.id=id;
        this.type=type;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public static Type getById(int id) {
        for(Type t : values()) {
            if(t.id == id) return t;
        }
        return null;
    }

    public static Type getByName(String widget_type) {
        for(Type t : values()) {
            if(t.type.equals(widget_type)) return t;
        }
        return null;
    }



}
