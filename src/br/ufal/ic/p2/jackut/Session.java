package br.ufal.ic.p2.jackut;

import java.io.Serializable;

/**
 * Represents a user session in the Jackut system.
 * Each session is identified by a unique ID and is associated with a specific user.
 */
public class Session implements Serializable {
    private String id;
    private User user;

    /**
     * Constructs a new session for a user.
     *
     * @param id   The unique session identifier.
     * @param user The user associated with this session.
     */
    public Session(String id, User user) {
        this.id = id;
        this.user = user;
    }

    /**
     * Gets the user associated with this session.
     *
     * @return The session's user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the unique identifier of this session.
     *
     * @return The session ID.
     */
    public String getId() {
        return id;
    }
}