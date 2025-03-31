package br.ufal.ic.p2.jackut;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Community implements Serializable {
    private String name, description;
    private List<User> members;
    private User owner;

    Community(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.members = new ArrayList<User>();
        this.owner = owner;
        this.members.add(owner);
    }

    public void addMember(User user) {
        if(!this.members.contains(user)) {
            members.add(user);
        }
    }

    public void broadcastMessage(User sender, String message) {
        for (User u : members) {
            u.receiveMessage(sender, message);
        }
    }
}
