package br.ufal.ic.p2.jackut;

import java.io.Serializable;

/**
 * Represents a message sent from one user to another in the Jackut system.
 * This class implements {@link Serializable} to allow message persistence.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private String senderSessionId;
    private User sender;
    private String receiverLogin;
    private String text;

    /**
     * Constructs a message with sender, receiver, and content.
     *
     * @param senderSessionId The session ID of the user who sent the message.
     * @param receiverLogin The login of the user who will receive the message.
     * @param text The content of the message.
     * @param sender The User object representing the sender.
     */
    public Message(String senderSessionId, String receiverLogin, String text, User sender) {
        this.senderSessionId = senderSessionId;
        this.receiverLogin = receiverLogin;
        this.sender = sender;
        this.text = text;
    }

    /**
     * Gets the message content.
     *
     * @return The message text.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the session ID of the user who sent the message.
     *
     * @return The sender's session ID.
     */
    public String getSenderSessionId() {
        return senderSessionId;
    }

    /**
     * Gets the User object representing the sender.
     *
     * @return The sender User object.
     */
    public User getSender() {
        return sender;
    }

    /**
     * Gets the login of the user who received the message.
     *
     * @return The receiver's login.
     */
    public String getReceiver() {
        return receiverLogin;
    }
}