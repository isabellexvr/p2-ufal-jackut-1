package br.ufal.ic.p2.jackut;

public class Session {
    private int id;
    private User user;

    Session(int id, User user) {
        this.id = id;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }
}
