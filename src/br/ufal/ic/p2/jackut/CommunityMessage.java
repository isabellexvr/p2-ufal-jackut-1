package br.ufal.ic.p2.jackut;

import java.io.Serializable;

/**
 * Represents a message sent to a community in the Jackut system.
 * Contains information about the sender, target community, and message content.
 * Implements Serializable to support object persistence.
 */
public class CommunityMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    private User sender;
    private Community community;
    private String message;

    /**
     * Constructs a new CommunityMessage with the specified sender, community, and content.
     *
     * @param sender    The User who sent this message
     * @param community The Community this message is sent to
     * @param message   The content of the message
     */
    CommunityMessage(User sender, Community community, String message) {
        this.sender = sender;
        this.community = community;
        this.message = message;
    }

    /**
     * Gets the user who sent this community message.
     *
     * @return The sender User object
     */
    public User getSender() {
        return sender;
    }

    /**
     * Gets the community this message was sent to.
     *
     * @return The target Community object
     */
    public Community getCommunity() {
        return community;
    }

    /**
     * Gets the content of this community message.
     *
     * @return The message text/content
     */
    public String getMessage() {
        return message;
    }
}