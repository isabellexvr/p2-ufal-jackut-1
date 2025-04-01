package br.ufal.ic.p2.jackut;
import br.ufal.ic.p2.jackut.Exceptions.EmptyAttributeException;

import java.io.*;
import java.util.*;

public class User implements Serializable {
    private String login;
    private String password;
    private String name;
    private List<User> friends;
    private List<FriendRequest> receivedInvitations;
    private List<Message> messages;
    private Map<String, String> atributosExtras;

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.friends = new ArrayList<>();
        this.receivedInvitations = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.atributosExtras = new HashMap<>();
    }

    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getName() { return name; }
    public List<User> getFriends() { return friends; }
    public List<FriendRequest> getReceivedInvitations() { return receivedInvitations; }
    public List<Message> getMessages() { return messages; }

    public void addFriend(User friend) {
        if (!friends.contains(friend)) {
            friends.add(friend);
        }
    }

    public void receiveMessage(User sender, String text) {
        messages.add(new Message(sender, this, text));
    }

    public boolean isFriend(User user) {
        return friends.contains(user);
    }

    public String getAtributo(String chave) {
        switch (chave.toLowerCase()) {
            case "login": return login;
            case "password": return password;
            case "nome": return name;
            default: return atributosExtras.get(chave);
        }
    }

    public void setAtributo(String chave, String valor) {
        switch (chave.toLowerCase()) {
            case "login": this.login = valor; break;
            case "password": this.password = valor; break;
            case "name": this.name = valor; break;
            default: atributosExtras.put(chave, valor); break;
        }
    }

    public void removeAtributo(String chave) throws EmptyAttributeException {
        if(atributosExtras.containsKey(chave)) {
            atributosExtras.remove(chave);
        }else{
            throw new EmptyAttributeException();
        }
    }
}
