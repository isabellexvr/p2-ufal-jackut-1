package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Jackut implements Serializable {
    private Repository repository;

    public Jackut() {
        this.repository = Repository.getInstance();
    }

    public void resetSystem(){
        this.repository.eraseEverything();
    }

    public String getUserAttribute(String login, String atributo) throws EmptyAttributeException, UserNotFoundException {

        User user = this.repository.getUser(login);
        String attribute = user.getAtributo(atributo);


        if(attribute == null) {
            throw new EmptyAttributeException();
        }

        return attribute;
    }

    public void createUser(String login, String password, String name) throws UserAlreadyExistsException, InvalidLoginException, InvalidPasswordException {

        if(login == null) {
            throw new InvalidLoginException();
        }else if(password == null){
            throw new InvalidPasswordException();
        }else{
            repository.addUser(login, password, name);
        }

    }

    public String newSession(String login, String senha) throws UserNotFoundException, InvalidPasswordOrLoginException {
        Session session = this.repository.newSession(login, senha);
//        System.out.println("retornou isso como id: " + session.getId());
        return session.getId();
    }

    public boolean isFriend(String login, String friend)throws Exception, UserNotFoundException{
        User user = repository.getUser(login);
        return user.isFriend(friend);

    }

    public void addFriend(String userSessionId, String friendLogin) throws Exception, UserNotFoundException, AlreadyFriendException, WaitingToAcceptException {

        User user = this.repository.getUserBySessionId(userSessionId);

        if(user.isFriend(friendLogin)) {
            throw new AlreadyFriendException();
        }
        user.addFriend(friendLogin);
    }


    public void endSystem(){
        this.repository.saveData();
    }

    public void editProfile(String id, String atributo, String valor) throws UserNotFoundException, EmptyAttributeException {
        if(id.isEmpty()){
            throw new UserNotFoundException();
        }

        Session session = this.repository.getSession(id);

        if(session == null){
            throw new UserNotFoundException();
        }

        this.repository.editProfile(session, atributo, valor);
    }

    public String getFriends(String login) throws UserNotFoundException {
        User user = this.repository.getUser(login);

        return "{" + String.join(",", user.getFriends()) + "}";
    }



}
