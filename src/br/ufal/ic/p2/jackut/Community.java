package br.ufal.ic.p2.jackut;

import java.io.Serializable;
import java.util.ArrayList;

public class Community implements Serializable {
    private String name, description;
    private User owner;
    private ArrayList<User> members;

    Community(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.members = new ArrayList<User>();
    }

    public String getName() {
        return name;
    }


}
