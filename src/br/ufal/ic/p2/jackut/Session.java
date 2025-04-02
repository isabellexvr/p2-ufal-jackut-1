package br.ufal.ic.p2.jackut;

import java.io.Serializable;

public class Session implements Serializable {
    private String id;
    private User user;

    Session(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return id;
    }
}
