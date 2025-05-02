package br.ufal.ic.p2.jackut;

import java.io.Serializable;

public class CommunityMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private User sender;
    private Community community;
    private String message;

    CommunityMessage(User sender, Community community, String message) {
        this.sender = sender;
        this.community = community;
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public Community getCommunity() {
        return community;
    }

    public String getMessage() {
        return message;
    }
}
