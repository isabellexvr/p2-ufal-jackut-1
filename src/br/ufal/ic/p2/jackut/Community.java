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
        members.add(owner);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner.getLogin();
    }

    public ArrayList<String> getMembers() {
        ArrayList<String> membersLogins = new ArrayList<>();

        for (User user : members) {
            //System.out.println(user.getLogin());
            membersLogins.add(user.getLogin());
        }

        return membersLogins;
    }


}
