package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.EmptyAttributeException;
import br.ufal.ic.p2.jackut.Exceptions.InvalidPasswordOrLoginException;
import br.ufal.ic.p2.jackut.Exceptions.UserAlreadyExistsException;
import br.ufal.ic.p2.jackut.Exceptions.UserNotFoundException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Repository implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String FILE_NAME = "jackut.dat";
    private Map<String, User> users = new HashMap<>();
    private Map<String, Session> sessions = new HashMap<>();

    public Repository() {
        loadUsers();
    }

    public Session newSession(String login, String password) throws UserNotFoundException, InvalidPasswordOrLoginException {
        User user = users.get(login);

        if (user == null || !user.getPassword().equals(password)) {
            throw new InvalidPasswordOrLoginException();
        }

        // Gera um ID único para a sessão
        String sessionId = UUID.randomUUID().toString();

        // Cria e armazena a sessão
        sessions.put(sessionId, new Session(sessionId, user));

        return sessions.get(sessionId);
    }


    public Session getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public void editProfile(Session session, String atributo, String valor) throws EmptyAttributeException{
        //System.out.println("setando atributo: " + atributo + " valor: " + valor);
        session.getUser().setAtributo(atributo, valor);

        //System.out.println("atributo editado: " + session.getUser().getAtributo(atributo));
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

    public void saveUsers() {
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
             ObjectInputStream ois = new ObjectInputStream(fileStream)) {

            users = (HashMap<String, User>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
            users = new HashMap<>();
        }
    }


}
