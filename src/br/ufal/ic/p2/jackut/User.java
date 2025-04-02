package br.ufal.ic.p2.jackut;
import br.ufal.ic.p2.jackut.Exceptions.*;

import java.io.*;
import java.util.*;

public class User implements Serializable {
    private String login;
    private String password;
    private String name;
    private List<Message> messages;
    private List<String> friends;
    private Map<String, String> atributosExtras;
    private Repository repository;

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.friends = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.atributosExtras = new HashMap<>();
        this.repository = Repository.getInstance();
    }

    public String getLogin() { return login; }
    public String getPassword() { return password; }
    public String getName() { return name; }

    public List<String> getFriends() throws UserNotFoundException {
        List<String> finalFriends = new ArrayList<>();

        for (String friend : friends) {
            User user = this.repository.getUser(friend);
            if(user.friends.contains(this.login)) {
                finalFriends.add(friend);
            }
        }

        return finalFriends;
    }

    public List<Message> getMessages() { return messages; }

    public void addFriend(String friendLogin) throws CantAddItselfException, UserNotFoundException, WaitingToAcceptException, AlreadyFriendException {
        if(friendLogin.equals(this.login)) {
            throw new CantAddItselfException();
        }

        User possibleFriend = this.repository.getUser(friendLogin);

        boolean isAlreadyFriend = friends.contains(friendLogin);
        boolean possibleFriendAdded = possibleFriend.friends.contains(this.login);

        if (friends.contains(friendLogin)) {
            if (!possibleFriend.friends.contains(this.login)) {
                throw new WaitingToAcceptException();
            }
            throw new AlreadyFriendException();
        }
        friends.add(friendLogin);

        this.repository.saveData();

    }

    public boolean isFriend(String friendLogin) throws Exception {
        User possibleFriend = this.repository.getUser(friendLogin);

        return friends.contains(friendLogin) && possibleFriend.friends.contains(this.login);
    }

    public void receiveMessage(User sender, String text) {
        messages.add(new Message(sender, this, text));
    }

    public String getAtributo(String chave) {
        switch (chave.toLowerCase()) {
            case "login": return login;
            case "nome": return name;
            default: return atributosExtras.get(chave);
        }
    }

    public void setAtributo(String chave, String valor) {
        switch (chave.toLowerCase()) {
            case "login": this.login = valor; break;
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
