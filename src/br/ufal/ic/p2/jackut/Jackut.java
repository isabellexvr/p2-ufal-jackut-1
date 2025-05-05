package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Main class of the Jackut system, responsible for managing users, sessions, friendships, and messages.
 * This class acts as the system's interface, interacting with the {@link Repository}.
 * Implements Serializable to support system persistence.
 */
public class Jackut implements Serializable {
    /**
     * Repositório associado ao usuário, contendo dados e funcionalidades relacionadas.
     */
    private Repository repository;

    /**
     * Constructs a new Jackut instance.
     * Initializes the system repository.
     */
    public Jackut() {
        this.repository = Repository.getInstance();
    }

    /**
     * Resets the system, erasing all stored data.
     */
    public void resetSystem() {
        this.repository.eraseEverything();
    }

    /**
     * Gets a specific user attribute.
     *
     * @param login The user's login
     * @param attribute The attribute name to retrieve
     * @return The attribute value
     * @throws EmptyAttributeException If the attribute is empty
     * @throws UserNotFoundException If the user doesn't exist
     */
    public String getUserAttribute(String login, String attribute) throws EmptyAttributeException, UserNotFoundException {
        User user = this.repository.getUser(login);
        String attributeValue = user.getAtributo(attribute);

        if (attributeValue == null) {
            throw new EmptyAttributeException();
        }

        return attributeValue;
    }

    /**
     * Creates a new user in the system.
     *
     * @param login The user's login
     * @param password The user's password
     * @param name The user's name
     * @throws UserAlreadyExistsException If the user already exists
     * @throws InvalidLoginException If the login is invalid
     * @throws InvalidPasswordException If the password is invalid
     */
    public void createUser(String login, String password, String name) throws UserAlreadyExistsException, InvalidLoginException, InvalidPasswordException {
        if (login == null) {
            throw new InvalidLoginException();
        } else if (password == null) {
            throw new InvalidPasswordException();
        } else {
            repository.addUser(login, password, name);
        }
    }

    /**
     * Creates a new session for a user.
     *
     * @param login The user's login
     * @param password The user's password
     * @return The new session ID
     * @throws UserNotFoundException If the user doesn't exist
     * @throws InvalidPasswordOrLoginException If the credentials are invalid
     */
    public String newSession(String login, String password) throws UserNotFoundException, InvalidPasswordOrLoginException {
        Session session = this.repository.newSession(login, password);
        return session.getId();
    }

    /**
     * Checks if two users are friends.
     *
     * @param login The first user's login
     * @param friend The friend's login
     * @return true if they are friends, false otherwise
     * @throws UserNotFoundException If either user doesn't exist
     */
    public boolean isFriend(String login, String friend) throws UserNotFoundException {
        User user = repository.getUser(login);
        return user.isFriend(friend);
    }

    /**
     * Adds a friend to a user.
     *
     * @param userSessionId The user's session ID
     * @param friendLogin The friend's login
     * @throws UserNotFoundException If either user doesn't exist
     * @throws AlreadyFriendException If they are already friends
     * @throws WaitingToAcceptException If there's a pending friend request
     * @throws CantAddItselfException If trying to add self as friend
     * @throws InvalidFunctionEnemyException If trying to add an enemy
     */
    public void addFriend(String userSessionId, String friendLogin) throws UserNotFoundException,
            AlreadyFriendException, WaitingToAcceptException, CantAddItselfException, InvalidFunctionEnemyException {
        User user = this.repository.getUserBySessionId(userSessionId);
        User possibleFriend = repository.getUser(friendLogin);

        if (user.isFriend(friendLogin)) {
            throw new AlreadyFriendException();
        }

        if(user.isEnemy(friendLogin) || possibleFriend.isEnemy(user.getLogin())) {
            throw new InvalidFunctionEnemyException(possibleFriend.getName());
        }
        user.addFriend(friendLogin);
    }

    /**
     * Saves all data and shuts down the system.
     */
    public void endSystem() {
        this.repository.saveData();
    }

    /**
     * Edits a user's profile.
     *
     * @param id The user's session ID
     * @param attribute The attribute to edit
     * @param value The new attribute value
     * @throws UserNotFoundException If the user doesn't exist
     * @throws EmptyAttributeException If the attribute is invalid
     */
    public void editProfile(String id, String attribute, String value) throws UserNotFoundException, EmptyAttributeException {
        if (id.isEmpty()) {
            throw new UserNotFoundException();
        }

        Session session = this.repository.getSession(id);

        if (session == null) {
            throw new UserNotFoundException();
        }

        this.repository.editProfile(session, attribute, value);
    }

    /**
     * Gets a user's friends list.
     *
     * @param login The user's login
     * @return Formatted string containing friends list
     */
    public String getFriends(String login) {
        try {
            User user = this.repository.getUser(login);
            return "{" + String.join(",", user.getFriends()) + "}";
        } catch (UserNotFoundException e) {
            return "{}";
        }
    }

    /**
     * Sends a message to another user.
     *
     * @param sessionId The sender's session ID
     * @param receiverLogin The recipient's login
     * @param message The message content
     * @throws InvalidFunctionEnemyException If messaging an enemy
     * @throws UserNotFoundException If either user doesn't exist
     * @throws CantMessageItselfException If trying to message self
     */
    public void sendMessage(String sessionId, String receiverLogin, String message) throws InvalidFunctionEnemyException, UserNotFoundException,
            CantMessageItselfException {
        User sender = this.repository.getUserBySessionId(sessionId);
        User receiver = this.repository.getUser(receiverLogin);

        if (sender.getLogin().equals(receiverLogin)) {
            throw new CantMessageItselfException();
        }

        if(sender.isFriend(receiverLogin) || receiver.isEnemy((sender.getLogin()))) {
            throw new InvalidFunctionEnemyException(receiver.getName());
        }

        Message m = new Message(sessionId, receiverLogin, message, sender);
        receiver.addMessage(m);
    }

    /**
     * Gets the first message in a user's inbox.
     *
     * @param sessionId The user's session ID
     * @return The message content
     * @throws UserNotFoundException If the user doesn't exist
     * @throws NoMessagesException If there are no messages
     */
    public String getFirstMessage(String sessionId) throws UserNotFoundException, NoMessagesException {
        User user = this.repository.getUserBySessionId(sessionId);
        return user.getFirstMessage();
    }

    /**
     * Creates a new community.
     *
     * @param sessionId The creator's session ID
     * @param name The community name
     * @param description The community description
     * @throws UserNotFoundException If the creator doesn't exist
     * @throws CommunityAlreadyExistsException If the community already exists
     */
    public void createCommunity(String sessionId, String name, String description) throws UserNotFoundException, CommunityAlreadyExistsException {
        if (this.repository.isCommunityCreated(name)) {
            throw new CommunityAlreadyExistsException();
        }

        User owner = this.repository.getUserBySessionId(sessionId);
        Community newCommunity = new Community(name, description, owner);

        newCommunity.addMember(owner);

        this.repository.newCommunity(name, newCommunity);
    }

    /**
     * Gets a community's description.
     *
     * @param communityName The community name
     * @return The community description
     * @throws CommunityDoesntExistException If the community doesn't exist
     */
    public String getCommunityDescription(String communityName) throws CommunityDoesntExistException {
        return this.repository.getCommunityDescription(communityName);
    }

    /**
     * Gets a community's owner.
     *
     * @param communityName The community name
     * @return The owner's login
     * @throws CommunityDoesntExistException If the community doesn't exist
     */
    public String getCommunityOwner(String communityName) throws CommunityDoesntExistException {
        return this.repository.getCommunityOwner(communityName);
    }

    /**
     * Gets a community's members.
     *
     * @param communityName The community name
     * @return Formatted string containing members list
     * @throws CommunityDoesntExistException If the community doesn't exist
     */
    public String getCommunityMembers(String communityName) throws CommunityDoesntExistException {
        Community community = this.repository.getCommunityByName(communityName);
        return "{" + String.join(",", community.getMembers()) + "}";
    }

    /**
     * Gets communities owned by a user.
     *
     * @param ownerLogin The owner's login
     * @return Formatted string containing communities list
     * @throws UserNotFoundException If the user doesn't exist
     */
    public String getCommunitiesByLogin(String ownerLogin) throws UserNotFoundException {
        ArrayList<String> communities = this.repository.getCommunitiesByLogin(ownerLogin);
        return "{" + String.join(",", communities) + "}";
    }

    /**
     * Adds a member to a community.
     *
     * @param session The new member's session ID
     * @param communityName The community name
     * @throws UserNotFoundException If the user doesn't exist
     * @throws CommunityDoesntExistException If the community doesn't exist
     * @throws UserAlreadyCommunityMemberException If the user is already a member
     */
    public void addMemberToCommunity(String session, String communityName) throws UserNotFoundException, CommunityDoesntExistException, UserAlreadyCommunityMemberException {
        User newMember = this.repository.getUserBySessionId(session);
        Community community = this.repository.getCommunityByName(communityName);

        if(community.isAlreadyMember(newMember)) {
            throw new UserAlreadyCommunityMemberException();
        }

        community.addMember(newMember);
    }

    /**
     * Gets the first community message for a user.
     *
     * @param sessionId The user's session ID
     * @return The message content
     * @throws UserNotFoundException If the user doesn't exist
     * @throws NoMessagesException If there are no private messages
     * @throws NoCommunityMessagesException If there are no community messages
     */
    public String getMessage(String sessionId) throws UserNotFoundException, NoMessagesException, NoCommunityMessagesException {
        User user = this.repository.getUserBySessionId(sessionId);
        return user.getFirstCommunityMessage();
    }

    /**
     * Sends a message to a community.
     *
     * @param sessionId The sender's session ID
     * @param communityName The community name
     * @param message The message content
     * @throws UserNotFoundException If the sender doesn't exist
     * @throws CommunityDoesntExistException If the community doesn't exist
     */
    public void sendCommunityMessage(String sessionId, String communityName, String message) throws UserNotFoundException, CommunityDoesntExistException {
        Community community = this.repository.getCommunityByName(communityName);
        User sender = this.repository.getUserBySessionId(sessionId);

        CommunityMessage communityMessage = new CommunityMessage(sender, community, message);

        community.sendMessageToMembers(communityMessage);
    }

    /**
     * Checks if a user is a fan of another user.
     *
     * @param fanLogin The fan's login
     * @param idolLogin The idol's login
     * @return true if the fan follows the idol, false otherwise
     * @throws UserNotFoundException If either user doesn't exist
     */
    public boolean isFan(String fanLogin, String idolLogin) throws UserNotFoundException {
        User fan = this.repository.getUser(fanLogin);
        return fan.doesUserFollow(idolLogin);
    }

    /**
     * Makes a user follow another user.
     *
     * @param userId The follower's session ID
     * @param idolLogin The idol's login
     * @throws UserNotFoundException If either user doesn't exist
     * @throws SelfFanException If trying to follow self
     * @throws InvalidFunctionEnemyException If trying to follow an enemy
     */
    public void follow(String userId, String idolLogin) throws UserNotFoundException, SelfFanException, InvalidFunctionEnemyException {
        User user = this.repository.getUserBySessionId(userId);
        User idol = this.repository.getUser(idolLogin);

        if(user.doesUserFollow(idolLogin)) {
            throw new AlreadyFollowsException();
        }

        if(user.getLogin().equals(idolLogin)) {
            throw new SelfFanException();
        }

        if(user.isEnemy(idolLogin) || idol.isEnemy(user.getLogin())) {
            throw new InvalidFunctionEnemyException(idol.getName());
        }
        user.follow(idolLogin);
        idol.setFollower(user.getLogin());
    }

    /**
     * Gets a user's followers.
     *
     * @param userLogin The user's login
     * @return Formatted string containing followers list
     * @throws UserNotFoundException If the user doesn't exist
     */
    public String getFollowers(String userLogin) throws UserNotFoundException {
        User user = this.repository.getUser(userLogin);
        return "{" + String.join(",", user.getFollowers()) + "}";
    }

    /**
     * Checks if a user has a crush on another user.
     *
     * @param userId The user's session ID
     * @param crushLogin The crush's login
     * @return true if the crush exists, false otherwise
     * @throws UserNotFoundException If either user doesn't exist
     */
    public boolean isCrush(String userId, String crushLogin) throws UserNotFoundException {
        User user = this.repository.getUserBySessionId(userId);
        return user.isCrush(crushLogin);
    }

    /**
     * Adds a crush relationship between users.
     *
     * @param userId The user's session ID
     * @param crushLogin The crush's login
     * @throws UserNotFoundException If either user doesn't exist
     * @throws AlreadyCrushException If the crush already exists
     * @throws CrushItselfException If trying to add self as crush
     * @throws InvalidFunctionEnemyException If trying to add an enemy as crush
     */
    public void addCrush(String userId, String crushLogin) throws UserNotFoundException, AlreadyCrushException,
            CrushItselfException, InvalidFunctionEnemyException {
        User user = this.repository.getUserBySessionId(userId);
        User crush = this.repository.getUser(crushLogin);

        if(user.isCrush(crushLogin)) {
            throw new AlreadyCrushException();
        }

        if (user.getLogin().equals(crushLogin)) {
            throw new CrushItselfException();
        }

        if(user.isEnemy(crushLogin) || crush.isEnemy(user.getLogin())) {
            throw new InvalidFunctionEnemyException(crush.getName());
        }

        user.addCrush(crushLogin);

        if(user.isCrush(crushLogin) && crush.isCrush(user.getLogin())) {
            String stringMsg = String.format("%s é seu paquera - Recado do Jackut.", user.getName());
            Message msg = new Message(userId, crushLogin, stringMsg, user);
            crush.addMessage(msg);

            String anotherStringMsg = String.format("%s é seu paquera - Recado do Jackut.", crush.getName());
            Message anotherMsg = new Message(crushLogin, userId, anotherStringMsg, crush);
            user.addMessage(anotherMsg);
        }
    }

    /**
     * Gets a user's crushes.
     *
     * @param userId The user's session ID
     * @return Formatted string containing crushes list
     * @throws UserNotFoundException If the user doesn't exist
     */
    public String getCrushes(String userId) throws UserNotFoundException {
        User user = this.repository.getUserBySessionId(userId);
        return "{" + String.join(",", user.getCrushes()) + "}";
    }

    /**
     * Adds an enemy relationship between users.
     * Enemies cannot add each other as friends, crushes, or fans.
     * Messages from enemies are automatically discarded.
     *
     * @param sessionId The user's session ID
     * @param enemyLogin The enemy's login
     * @throws UserNotFoundException If either user doesn't exist
     * @throws AlreadyEnemyException If they are already enemies
     * @throws SelfEnemyException If trying to add self as enemy
     */
    public void addEnemy(String sessionId, String enemyLogin) throws UserNotFoundException, AlreadyEnemyException, SelfEnemyException {
        User user = this.repository.getUserBySessionId(sessionId);
        this.repository.getUser(enemyLogin);

        if(user.isEnemy(enemyLogin)) {
            throw new AlreadyEnemyException();
        }

        if(user.getLogin().equals(enemyLogin)) {
            throw new SelfEnemyException();
        }

        user.addEnemy(enemyLogin);
    }

    /**
     * Removes a user account from the system.
     * All user information is deleted: relationships, sent messages, profile.
     *
     * @param userId The user's session ID
     * @throws UserNotFoundException If the user doesn't exist
     */
    public void removeUser(String userId) throws UserNotFoundException {
        User user = this.repository.getUserBySessionId(userId);
        this.repository.deleteUser(user.getLogin());
    }
}