package br.ufal.ic.p2.jackut;
import java.io.Serializable;

public class FriendRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private User sender;
    private User receiver;

    public FriendRequest(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public User getSender() { return sender; }
    public User getReceiver() { return receiver; }
}
