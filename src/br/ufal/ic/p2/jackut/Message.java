package br.ufal.ic.p2.jackut;
import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    private User sender;
    private User receiver;
    private String text;

    public Message(User sender, User receiver, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
    }

    public String getText() { return text; }
    public User getSender() { return sender; }
    public User getReceiver() { return receiver; }
}
