package br.ufal.ic.p2.jackut;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements Serializable {
    private String name, password, login, authToken, id;
    private static final long serialVersionUID = 1L;
    private List<User> friends, invitations;

    User(String name, String password, String login) {
        this.name = name;
        this.password = password;
        this.login = login;
        this.friends = new ArrayList<>();
        this.invitations = new ArrayList<>();
        saveUser();
    }

    public static User loadUser(String login) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(login + ".ser"))) {
            return (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User authenticateUser(String login, String password) {
        User user = loadUser(login); // Load user by login
        if (user != null && user.getPassword().equals(password)) {
            return user; // Return user if password matches
        }
        return null; // Return null if authentication fails
    }


    private void saveUser() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(login + ".ser"))) {
            oos.writeObject(this);
            System.out.println("User " + login + " saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newSession(String login, String password) {
        if (this.login.equals(login) && this.password.equals(password)) {
            String authToken = UUID.randomUUID().toString();
            this.authToken = authToken;
        }
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getAuthToken(){return authToken;}

    public String getPassword() {
        return password;
    }

    public List<User> getFriends() { return friends;}

    public List<User> getInvitations() {return invitations;}

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void sendInvitation(User sender){
        invitations.add(sender);
    }

    public void receiveMessage(User sender, String message){
        System.out.println(sender.getName() + " : " + message);
    }

    public void receiveMessage(String message){
        System.out.println(message);
    }

    public void acceptInvitation(User sender){
        if(invitations.contains(sender)){
            friends.add(sender);
            sender.friends.remove(this);
            invitations.remove(sender);
        }
    }
}
