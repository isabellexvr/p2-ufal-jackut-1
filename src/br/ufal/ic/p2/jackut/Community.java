package br.ufal.ic.p2.jackut;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a community in the Jackut system.
 * A community has a name, description, owner, and members.
 * Implements Serializable to allow object serialization.
 */
public class Community implements Serializable {
    /**
     * The name of the community. Must be unique.
     */
    private String name;

    /**
     * A brief description of the community's purpose or theme.
     */
    private String description;

    /**
     * The user who created and owns the community.
     */
    private User owner;

    /**
     * A list of users who are members of the community.
     */
    private ArrayList<User> members;

    /**
     * Constructs a new Community with the given parameters.
     *
     * @param name The name of the community
     * @param description The description of the community
     * @param owner The User who owns/created this community
     */
    Community(String name, String description, User owner) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.members = new ArrayList<User>();
    }

    /**
     * Gets the name of this community.
     *
     * @return The community name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of this community.
     *
     * @return The community description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the login of the owner of this community.
     *
     * @return The owner's login
     */
    public String getOwner() {
        return owner.getLogin();
    }

    /**
     * Gets a list of all members' logins in this community.
     *
     * @return ArrayList containing all members' logins
     */
    public ArrayList<String> getMembers() {
        ArrayList<String> membersLogins = new ArrayList<>();

        for (User user : members) {
            membersLogins.add(user.getLogin());
        }

        return membersLogins;
    }

    /**
     * Sends a message to all community members.
     *
     * @param message The CommunityMessage to send to all members
     */
    public void sendMessageToMembers(CommunityMessage message) {
        for (User user : members) {
            user.addCommunityMessage(message);
        }
    }

    /**
     * Checks if a user is already a member of this community.
     *
     * @param user The User to check
     * @return true if the user is a member, false otherwise
     */
    public Boolean isAlreadyMember(User user) {
        return members.contains(user);
    }

    /**
     * Adds a new member to this community.
     * Also adds the community to the user's community list.
     *
     * @param user The User to add as a member
     */
    public void addMember(User user) {
        user.addCommunity(this.name);
        members.add(user);
    }
}