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
     * Gets the user's password.
     * @return The user's password.
     */
    public String getPassword() { return password; }

    /**
     * Gets the user's name.
     * @return The user's name.
     */
    public String getName() { return name; }

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
     * Checks if another user is a confirmed friend.
     * @param friendLogin The friend's login.
     * @return True if they are friends, false otherwise.
     * @throws UserNotFoundException If the friend is not found.
     */
    public boolean isFriend(String friendLogin) throws UserNotFoundException {
        User possibleFriend = this.repository.getUser(friendLogin);
        return friends.contains(friendLogin) && possibleFriend.friends.contains(this.login);
    }

    /**
     * Receives a message from another user.
     * @param sender The sender user.
     * @param text The message text.
     */
    public void receiveMessage(User sender, String text) {
        messages.add(new Message(sender, this, text));
    }

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
     * Removes an attribute.
     * @param chave The attribute key to remove.
     * @throws EmptyAttributeException If the attribute does not exist.
     */
    public void removeAtributo(String chave) throws EmptyAttributeException {
        if (atributosExtras.containsKey(chave)) {
            atributosExtras.remove(chave);
        } else {
            throw new EmptyAttributeException();
        }
    }

    /**
     * Sends a message to another user.
     * @param message The message to send.
     */
    public void sendMessage(Message message) {
        message.getReceiver().addMessage(message);
    }

    /**
     * Adds a message to the user's message queue.
     * @param message The message to add.
     */
    public void addMessage(Message message) {
        messages.add(message);
    }

    /**
     * Prints all messages received by the user.
     */
    public void printAllMessages() {
        for (Message message : messages) {
            System.out.println("Mensagem de " + message.getSender().getName() + " para " + message.getReceiver().getName());
            System.out.println(message.getText());
        }
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

    public String getFirstCommunityMessage() throws NoCommunityMessagesException {
        if(communityMessages.isEmpty()) {
            throw new NoCommunityMessagesException();
        }
        return communityMessages.poll().getMessage();
    }

    public void addCommunityMessage(CommunityMessage message) {
        communityMessages.add(message);
    }

    public void addCommunity(String community) {
        this.communities.add(community);
    }

    public ArrayList<String> getCommunities() {
        return communities;
    }

    public boolean doesUserFollow(String idolLogin){
        return follows.contains(idolLogin);
    }

    public void follow(String idolLogin) {
        this.follows.add(idolLogin);
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollower(String fanId) {
        this.followers.add(fanId);
    }

    public boolean isCrush(String crushLogin) {
        return crushes.contains(crushLogin);
    }

    public void addCrush(String crushLogin) {
        this.crushes.add(crushLogin);
    }

    public List<String> getCrushes(){
        return this.crushes;
    }

    public void addEnemy(String login){
        this.enemies.add(login);
    }

    public boolean isEnemy(String login){
        return this.enemies.contains(login);
    }

    public void removeCommunity(String community) {
        this.communities.remove(community);
    }

    public Queue<Message> getMessages() {
        return messages;
    }
}
