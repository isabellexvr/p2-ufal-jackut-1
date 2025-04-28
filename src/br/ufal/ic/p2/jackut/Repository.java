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

    public boolean isCommunityCreated(String communityName) {
        return communities.containsKey(communityName);
    }

    public void newCommunity(String name, Community community) {
        this.communities.put(name, community);
    }

    public Community getCommunityByName(String communityName) throws CommunityDoesntExistException {
        Community community = this.communities.get(communityName);

        if (community == null) {
            throw new CommunityDoesntExistException();
        }
        return community;
    }

    public String getCommunityDescription(String communityName) throws CommunityDoesntExistException {
        Community community = this.communities.get(communityName);

        if (community == null) {
            throw new CommunityDoesntExistException();
        }

        return community.getDescription();
    }

    public String getCommunityOwner(String communityName) throws CommunityDoesntExistException {
        Community community = this.communities.get(communityName);

        if (community == null) {
            throw new CommunityDoesntExistException();
        }

        return community.getOwner();
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
     * Deletes all users and sessions from the repository.
     */
    public void eraseEverything() {
        users.clear();
        sessions.clear();
        new File(USERS_FILE).delete();
        new File(SESSIONS_FILE).delete();
        saveData();
    }

    /**
     * Saves users and sessions to their respective files.
     */
    public void saveData() {
        saveToFile(USERS_FILE, users);
        saveToFile(SESSIONS_FILE, sessions);
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
     * Retrieves a user by their name.
     *
     * @param name The name of the user.
     * @return The User object.
     * @throws UserNotFoundException If no user with the given name is found.
     */
    public User getUserByName(String name) throws UserNotFoundException {
        for (User user : users.values()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    private void loadData() {
        users = loadFromFile(USERS_FILE, new HashMap<String, User>());
        sessions = loadFromFile(SESSIONS_FILE, new HashMap<String, Session>());
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
}
