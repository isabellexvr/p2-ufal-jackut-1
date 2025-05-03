package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;

import java.io.*;
import java.util.*;

/**
 * The Repository class is responsible for managing users and sessions in the system.
 * It follows the Singleton pattern to ensure a single instance is used throughout the application.
 * This class also handles data persistence by saving and loading users and sessions from files.
 */
public class Repository implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String USERS_FILE = "jackut_users.dat";
    private static final String SESSIONS_FILE = "jackut_sessions.dat";
    private static final String COMMUNITIES_FILE = "jackut_communities.dat";

    private static Repository instance; // Singleton instance

    private Map<String, User> users = new HashMap<>();
    private Map<String, Session> sessions = new HashMap<>();
    private Map<String, Community> communities = new HashMap<>(); // name pra comunidade, único


    /**
     * Private constructor that loads saved data upon initialization.
     */
    private Repository() {
        loadData();
    }

    /**
     * Returns the single instance of Repository, creating it if necessary.
     *
     * @return The singleton instance of Repository.
     */
    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    /**
     * Checks if a community with the given name exists in the system.
     * @param communityName The name of the community to check.
     * @return true if the community exists, false otherwise.
     */
    public boolean isCommunityCreated(String communityName) {
        Community community = communities.get(communityName);
        return community != null;
    }

    /**
     * Creates a new community in the system.
     * @param name The name of the new community.
     * @param community The Community object to be added.
     * @throws IllegalArgumentException if either parameter is null.
     */
    public void newCommunity(String name, Community community) {
        if (name == null || community == null) {
            throw new IllegalArgumentException("Community name and object cannot be null");
        }
        this.communities.put(name, community);
    }

    /**
     * Retrieves a community by its name.
     * @param communityName The name of the community to retrieve.
     * @return The Community object.
     * @throws CommunityDoesntExistException if no community with the given name exists.
     */
    public Community getCommunityByName(String communityName) throws CommunityDoesntExistException {
        Community community = this.communities.get(communityName);

        if (community == null) {
            throw new CommunityDoesntExistException();
        }
        return community;
    }

    /**
     * Gets the description of a specific community.
     * @param communityName The name of the community.
     * @return The community description.
     * @throws CommunityDoesntExistException if no community with the given name exists.
     */
    public String getCommunityDescription(String communityName) throws CommunityDoesntExistException {
        Community community = this.communities.get(communityName);

        if (community == null) {
            throw new CommunityDoesntExistException();
        }

        return community.getDescription();
    }

    /**
     * Gets the owner of a specific community.
     * @param communityName The name of the community.
     * @return The login of the community owner.
     * @throws CommunityDoesntExistException if no community with the given name exists.
     */
    public String getCommunityOwner(String communityName) throws CommunityDoesntExistException {
        Community community = this.communities.get(communityName);

        if (community == null) {
            throw new CommunityDoesntExistException();
        }

        return community.getOwner();
    }

    /**
     * Retrieves all communities that a user belongs to.
     * @param userLogin The login of the user.
     * @return ArrayList containing the names of all communities the user belongs to.
     * @throws UserNotFoundException if the user doesn't exist or if an empty login is provided.
     */
    public ArrayList<String> getCommunitiesByLogin(String userLogin) throws UserNotFoundException {
        User user = this.users.get(userLogin);

        if (Objects.equals(userLogin, "")) {
            throw new UserNotFoundException();
        }

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user.getCommunities();
    }

    /**
     * Creates a new session for a user given their login and password.
     *
     * @param login    The user's login.
     * @param password The user's password.
     * @return A new Session object.
     * @throws UserNotFoundException         If the user does not exist.
     * @throws InvalidPasswordOrLoginException If the password is incorrect.
     */
    public Session newSession(String login, String password) throws UserNotFoundException, InvalidPasswordOrLoginException {
        User user = users.get(login);

        if (user == null || !user.getPassword().equals(password)) {
            throw new InvalidPasswordOrLoginException();
        }

        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, new Session(sessionId, user));
        saveData();
        return sessions.get(sessionId);
    }

    /**
     * Retrieves a session based on the session ID.
     *
     * @param sessionId The session ID.
     * @return The corresponding Session object, or null if not found.
     */
    public Session getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    /**
     * Retrieves a user by their session ID.
     *
     * @param id The session ID.
     * @return The corresponding User object.
     * @throws UserNotFoundException If the session ID does not exist.
     */
    public User getUserBySessionId(String id) throws UserNotFoundException {
        if (!sessions.containsKey(id)) {
            throw new UserNotFoundException();
        }
        return sessions.get(id).getUser();
    }

    /**
     * Adds a new user to the repository.
     *
     * @param login    The user's login.
     * @param password The user's password.
     * @param name     The user's name.
     * @throws UserAlreadyExistsException If a user with the same login already exists.
     */
    public void addUser(String login, String password, String name) throws UserAlreadyExistsException {
        if (users.containsKey(login)) {
            throw new UserAlreadyExistsException();
        }
        users.put(login, new User(login, password, name));
        saveData();
    }

    /**
     * Deletes all users, sessions and communities from the repository.
     */
    public void eraseEverything() {
        users.clear();
        sessions.clear();
        communities.clear();
        new File(USERS_FILE).delete();
        new File(SESSIONS_FILE).delete();
        new File(COMMUNITIES_FILE).delete();
        saveData();
    }


    /**
     * Saves users, sessions and communities to their respective files.
     */
    public void saveData() {
        saveToFile(USERS_FILE, users);
        saveToFile(SESSIONS_FILE, sessions);
        saveToFile(COMMUNITIES_FILE, communities);
    }

    /**
     * Saves a given object to a file.
     *
     * @param fileName The name of the file.
     * @param data     The object to save.
     */
    private void saveToFile(String fileName, Object data) {
        try (FileOutputStream fileStream = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fileStream)) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Error saving data to " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Loads data from a file.
     *
     * @param <T>         The type of object to load.
     * @param fileName    The name of the file.
     * @param defaultValue The default value if the file does not exist or an error occurs.
     * @return The loaded object or the default value.
     */
    @SuppressWarnings("unchecked")
    private <T> T loadFromFile(String fileName, T defaultValue) {
        File file = new File(fileName);
        if (!file.exists()) {
            return defaultValue;
        }
        try (FileInputStream fileStream = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fileStream)) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data from " + fileName + ": " + e.getMessage());
            return defaultValue;
        }
    }

    /**
     * Edits a user profile attribute.
     *
     * @param session  The session of the user.
     * @param atributo The attribute to modify.
     * @param valor    The new value.
     * @throws EmptyAttributeException If the provided attribute value is empty.
     */
    public void editProfile(Session session, String atributo, String valor) throws EmptyAttributeException {
        session.getUser().setAtributo(atributo, valor);
    }


    /**
     * Loads all application data from persistent storage files.
     * Initializes the users, sessions and communities maps by reading from their respective files.
     * If any file is not found or corrupted, initializes with empty HashMaps.
     *
     * The method loads three separate data structures:
     * - Users data from USERS_FILE
     * - Sessions data from SESSIONS_FILE
     * - Communities data from COMMUNITIES_FILE
     */
    private void loadData() {
        users = loadFromFile(USERS_FILE, new HashMap<>());
        sessions = loadFromFile(SESSIONS_FILE, new HashMap<>());
        communities = loadFromFile(COMMUNITIES_FILE, new HashMap<>());
    }

    /**
     * Retrieves a user by their login.
     *
     * @param login The login of the user.
     * @return The User object.
     * @throws UserNotFoundException If the user does not exist.
     */
    public User getUser(String login) throws UserNotFoundException {
        if (!users.containsKey(login)) {
            throw new UserNotFoundException();
        }
        return users.get(login);
    }

    /**
     * Deletes a user and all associated data from the system.
     * Performs a complete cleanup including:
     * - Removing all user messages and community messages
     * - Removing references from other users' messages
     * - Handling owned communities (deletes them if user is owner)
     * - Removing from friends lists
     * - Cleaning session data
     *
     * @param login The login of the user to be deleted
     * @throws UserNotFoundException If no user with the specified login exists
     *
     * Operation steps:
     * 1. Verifies user existence
     * 2. Clears all user messages
     * 3. Removes user references from other users' messages
     * 4. Handles communities owned by the user
     * 5. Updates all users' community memberships
     * 6. Removes user from friends lists
     * 7. Cleans up user and session data
     */
    public void deleteUser(String login) throws UserNotFoundException {
        if (!users.containsKey(login)) {
            throw new UserNotFoundException();
        }

        User userToDelete = users.get(login);
        userToDelete.getMessages().clear();
        userToDelete.getCommunityMessages().clear();

        for (User otherUser : users.values()) {
            otherUser.getMessages().removeIf(msg -> msg.getSender().getLogin().equals(login));
            otherUser.getCommunityMessages().removeIf(msg -> msg.getSender().getLogin().equals(login));
        }

        ArrayList<String> removedCommunities = new ArrayList<>();

        for (Community community : new ArrayList<>(communities.values())) {
            if (community.getOwner().equals(login)) {
                communities.remove(community.getName());
                removedCommunities.add(community.getName());
            }
        }

        for (User user : users.values()) {
            for (String communityName : removedCommunities) {
                user.removeCommunity(communityName);
            }
        }

        for (User user : users.values()) {
            user.removeFriend(login);
        }

        users.remove(login);
        sessions.remove(login);
    }

}
