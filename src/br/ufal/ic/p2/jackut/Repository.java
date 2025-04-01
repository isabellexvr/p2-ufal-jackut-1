package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.EmptyAttributeException;
import br.ufal.ic.p2.jackut.Exceptions.UserAlreadyExistsException;
import br.ufal.ic.p2.jackut.Exceptions.UserNotFoundException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Repository implements Serializable {

    private static final AtomicInteger sessionCounter = new AtomicInteger(1);
    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "jackut.dat";
    private Map<String, User> users = new HashMap<>();
    private Map<Integer, Session> sessions = new HashMap<>();

    public Repository() {
        loadUsers();
    }

    public Session newSession(String login, String senha) throws UserNotFoundException, EmptyAttributeException {

        User user = users.get(login);
        if (user == null) {
            throw new UserNotFoundException();
        }

        int sessionId = sessionCounter.getAndIncrement();
        sessions.put(sessionId, new Session(sessionId, user));

        return sessions.get(sessionId);
    }

    public User getUserByName(String name) throws UserNotFoundException {
        for (User user : users.values()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    public void addUser(String login, String password, String name) throws UserAlreadyExistsException {
        if(users.containsKey(login)) { // se o login já existe
            throw new UserAlreadyExistsException();
        }

        for (User user : users.values()) { // se o nome já existe
            if (user.getName().equals(name) && user.getLogin().equals(login)) {
                throw new UserAlreadyExistsException();
            }
        }

        users.put(login, new User(login, password, name));
    }

    public void eraseEverything() {
        users.clear();
        sessions.clear();
        File file = new File(FILE_NAME);

        if (file.exists()) {
            file.delete(); // Remove the file completely
        }

        saveUsers(); // Create a fresh empty file
    }

    public User getUser(String login) throws UserNotFoundException {

        if(!users.containsKey(login)) {
            throw new UserNotFoundException();
        }
        return users.get(login);
    }

    private void saveUsers() {
        try (OutputStream fileStream = new FileOutputStream(FILE_NAME);
             OutputStreamWriter writer = new OutputStreamWriter(fileStream);
             ObjectOutputStream oos = new ObjectOutputStream(fileStream)) {

            oos.writeObject(users);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsers() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            users = new HashMap<>();
            return;
        }

        try (InputStream fileStream = new FileInputStream(FILE_NAME);
             InputStreamReader reader = new InputStreamReader(fileStream);
             ObjectInputStream ois = new ObjectInputStream(fileStream)) {

            users = (HashMap<String, User>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            users = new HashMap<>();
        }
    }
}
