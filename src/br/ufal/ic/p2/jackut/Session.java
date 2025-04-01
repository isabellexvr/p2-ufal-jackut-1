package br.ufal.ic.p2.jackut;

public class Session {
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
