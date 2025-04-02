package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;

import java.io.*;
import java.util.*;

public class Repository implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String USERS_FILE = "jackut_users.dat";
    private static final String SESSIONS_FILE = "jackut_sessions.dat";

    private static Repository instance; // Singleton

    private Map<String, User> users = new HashMap<>();
    private Map<String, Session> sessions = new HashMap<>();

    public Repository() {
        loadData();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public Session newSession(String login, String password) throws UserNotFoundException, InvalidPasswordOrLoginException {
        User user = users.get(login);


        if (user == null || !user.getPassword().equals(password)) {
            throw new InvalidPasswordOrLoginException();
        }

        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, new Session(sessionId, user));

        saveData(); // Salvar sessões após criar uma nova
        return sessions.get(sessionId);
    }

    public Session getSession(String sessionId) {
        return sessions.get(sessionId);
    }

    public User getUserBySessionId(String id) throws Exception {
        if (!sessions.containsKey(id)) {
            throw new Exception("Sessão não encontrada: " + id);
        }
        return sessions.get(id).getUser();
    }

    public void addUser(String login, String password, String name) throws UserAlreadyExistsException {
        if (users.containsKey(login)) {
            throw new UserAlreadyExistsException();
        }

        for (User user : users.values()) {
            if (user.getName().equals(name) && user.getLogin().equals(login)) {
                throw new UserAlreadyExistsException();
            }
        }

        users.put(login, new User(login, password, name));
        saveData(); // Salvar usuários após adicionar um novo
    }

    public void eraseEverything() {
        users.clear();
        sessions.clear();
        new File(USERS_FILE).delete();
        new File(SESSIONS_FILE).delete();
        saveData(); // Criar arquivos vazios novamente
    }

    public void saveData() {
        saveToFile(USERS_FILE, users);
        saveToFile(SESSIONS_FILE, sessions);
    }

    private void saveToFile(String fileName, Object data) {
        try (FileOutputStream fileStream = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fileStream)) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados em " + fileName + ": " + e.getMessage());
        }
    }

    private void loadData() {
        users = loadFromFile(USERS_FILE, new HashMap<String, User>());
        sessions = loadFromFile(SESSIONS_FILE, new HashMap<String, Session>());

//        System.out.println("Usuários carregados:");
//        for (User user : users.values()) {
//            System.out.println("- " + user.getLogin() + " (" + user.getName() + ")");
//        }
//
//        System.out.println("Sessões carregadas:");
//        for (Session session : sessions.values()) {
//            System.out.println("- Sessão: " + session.getId() + " -> Usuário: " + session.getUser().getLogin());
//        }
    }
    public void editProfile(Session session, String atributo, String valor) throws EmptyAttributeException{
        session.getUser().setAtributo(atributo, valor);
    }

    public User getUserByName(String name) throws UserNotFoundException {
        for (User user : users.values()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        throw new UserNotFoundException();
    }

    public User getUser(String login) throws UserNotFoundException {

        if(!users.containsKey(login)) {
            if(login.equals("oabath")){
                System.out.println(users.get(login));
                System.out.println("nao achou o login do oabath");
            }
            throw new UserNotFoundException();
        }
        return users.get(login);
    }


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
            System.err.println("Erro ao carregar dados de " + fileName + ": " + e.getMessage());
            return defaultValue;
        }
    }
}
