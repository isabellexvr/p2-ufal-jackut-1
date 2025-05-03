package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;

import java.io.*;
import java.util.*;

/**
 * Represents a user in the Jackut system.
 * This class manages user data, friendships, and messages.
 */
public class User implements Serializable {
    private String login;
    private String password;
    private String name;
    private Map<String, String> atributosExtras;

    private List<String> friends;
    private List<String> follows;
    private List<String> followers;
    private List<String> crushes;
    private List<String> enemies;

    private Repository repository;

    private Queue<Message> messages;
    private Queue<CommunityMessage> communityMessages;

    private ArrayList<String> communities;

    private static final long serialVersionUID = -8830410604829432853L;

    /**
     * Constructs a User with the given login, password, and name.
     * @param login User's unique identifier.
     * @param password User's password.
     * @param name User's name.
     */
    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.friends = new ArrayList<>();
        this.messages = new LinkedList<>();
        this.atributosExtras = new HashMap<>();
        this.repository = Repository.getInstance();
        this.communities = new ArrayList<>();
        this.communityMessages = new LinkedList<>();
        this.enemies = new ArrayList<>();
        this.follows = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.crushes = new ArrayList<>();
    }

    /**
     * Gets the user's login.
     * @return The user's login.
     */
    public String getLogin() { return login; }

    /**
     * Gets the user's name.
     * @return The user's name.
     */
    public String getName() { return name; }

    /**
     * Gets the user's password.
     * @return The user's password.
     */
    public String getPassword() { return password; }

    /**
     * Retrieves a specific user attribute.
     * @param chave The attribute key.
     * @return The attribute value.
     */
    public String getAtributo(String chave) {
        switch (chave.toLowerCase()) {
            case "login": return login;
            case "nome": return name;
            default: return atributosExtras.get(chave);
        }
    }

    /**
     * Sets a user attribute.
     * @param chave The attribute key.
     * @param valor The attribute value.
     */
    public void setAtributo(String chave, String valor) {
        switch (chave.toLowerCase()) {
            case "login": this.login = valor; break;
            case "name": this.name = valor; break;
            default: atributosExtras.put(chave, valor); break;
        }
    }

    /**
     * Adds a friend request.
     * @param friendLogin The login of the friend to be added.
     * @throws CantAddItselfException If the user tries to add themselves.
     * @throws UserNotFoundException If the friend does not exist.
     * @throws WaitingToAcceptException If the friend request is pending.
     * @throws AlreadyFriendException If the users are already friends.
     */
    public void addFriend(String friendLogin) throws CantAddItselfException, UserNotFoundException, WaitingToAcceptException, AlreadyFriendException {
        if (friendLogin.equals(this.login)) {
            throw new CantAddItselfException();
        }

        User possibleFriend = this.repository.getUser(friendLogin);

        if (friends.contains(friendLogin)) {
            if (!possibleFriend.friends.contains(this.login)) {
                throw new WaitingToAcceptException();
            }
            throw new AlreadyFriendException();
        }
        friends.add(friendLogin);
        this.repository.saveData();
    }

    /**
     * Retrieves the list of confirmed friends.
     * @return A list of confirmed friends.
     * @throws UserNotFoundException If a friend is not found in the repository.
     */
    public List<String> getFriends() throws UserNotFoundException {
        List<String> finalFriends = new ArrayList<>();
        for (String friend : friends) {
            User user = this.repository.getUser(friend);
            if (user.friends.contains(this.login)) {
                finalFriends.add(friend);
            }
        }
        return finalFriends;
    }

    /**
     * Checks if another user is a confirmed friend.
     * @param friendLogin The friend's login.
     * @return True if they are friends, false otherwise.
     * @throws UserNotFoundException If the friend is not found.
     */
    public boolean isFriend(String friendLogin) throws UserNotFoundException {
        User possibleFriend = this.repository.getUser(friendLogin);
        return friends.contains(friendLogin) && possibleFriend.friends.contains(this.login);
    }

    public void removeFriend(String friendLogin) {
        friends.remove(friendLogin);
    }

    public void removeMessages() {
        messages = new LinkedList<>();
        communityMessages = new LinkedList<>();
    }

    /**
     * Adds a message to the user's message queue.
     * @param message The message to add.
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Retrieves and removes the first message from the queue.
     * @return The text of the first message.
     * @throws NoMessagesException If there are no messages.
     */
    public String getFirstMessage() throws NoMessagesException {
        if (messages.isEmpty()) {
            throw new NoMessagesException();
        }
        return messages.poll().getText();
    }

    /**
     * Retrieves the first community message from the queue.
     * @return The text of the first community message.
     * @throws NoCommunityMessagesException If there are no community messages.
     */
    public String getFirstCommunityMessage() throws NoCommunityMessagesException {
        if (communityMessages.isEmpty()) {
            throw new NoCommunityMessagesException();
        }
        return communityMessages.poll().getMessage();
    }

    /**
     * Adds a community message to the user's queue.
     * @param message The community message to add.
     */
    public void addCommunityMessage(CommunityMessage message) {
        communityMessages.add(message);
    }

    /**
     * Adds a community to the user's list of communities.
     * @param community The community to add.
     */
    public void addCommunity(String community) {
        this.communities.add(community);
    }

    /**
     * Gets the list of communities the user belongs to.
     * @return List of community names.
     */
    public ArrayList<String> getCommunities() {
        return new ArrayList<>(communities); // Retorna uma cópia para evitar modificações externas
    }

    /**
     * Checks if the user follows another user.
     * @param idolLogin The login of the user to check.
     * @return True if the user follows the specified user, false otherwise.
     */
    public boolean doesUserFollow(String idolLogin) {
        return follows.contains(idolLogin);
    }

    /**
     * Follows another user.
     * @param idolLogin The login of the user to follow.
     */
    public void follow(String idolLogin) {
        this.follows.add(idolLogin);
    }

    /**
     * Gets the list of followers.
     * @return List of follower logins.
     */
    public List<String> getFollowers() {
        return new ArrayList<>(followers); // Retorna uma cópia para evitar modificações externas
    }

    /**
     * Adds a follower to the user's list.
     * @param fanId The login of the follower to add.
     */
    public void setFollower(String fanId) {
        this.followers.add(fanId);
    }

    /**
     * Checks if another user is a crush.
     * @param crushLogin The login of the user to check.
     * @return True if the user is a crush, false otherwise.
     */
    public boolean isCrush(String crushLogin) {
        return crushes.contains(crushLogin);
    }

    /**
     * Adds a crush to the user's list.
     * @param crushLogin The login of the crush to add.
     */
    public void addCrush(String crushLogin) {
        this.crushes.add(crushLogin);
    }

    /**
     * Gets the list of crushes.
     * @return List of crush logins.
     */
    public List<String> getCrushes() {
        return new ArrayList<>(crushes); // Retorna uma cópia para evitar modificações externas
    }

    /**
     * Adds an enemy to the user's list.
     * @param login The login of the enemy to add.
     */
    public void addEnemy(String login) {
        this.enemies.add(login);
    }

    /**
     * Checks if another user is an enemy.
     * @param login The login of the user to check.
     * @return True if the user is an enemy, false otherwise.
     */
    public boolean isEnemy(String login) {
        return this.enemies.contains(login);
    }

    /**
     * Removes a community from the user's list.
     * @param community The community to remove.
     * @return True if the community was removed, false if it wasn't found.
     */
    public boolean removeCommunity(String community) {
        return this.communities.remove(community);
    }

    /**
     * Gets the user's message queue.
     * @return The message queue.
     */
    public Queue<Message> getMessages() {
        return messages;
    }

    /**
     * Gets the user's community message queue.
     * @return The community message queue.
     * */
    public Queue<CommunityMessage> getCommunityMessages() {
        return communityMessages;
    }
}
