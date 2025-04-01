package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;

import javax.security.auth.login.LoginException;
import java.io.Serializable;
import java.util.List;

public class Jackut implements Serializable {
    private Repository repository;

    public Jackut() {
        this.repository = new Repository();
        this.repository.eraseEverything();
    }

    public String getUserAttribute(String login, String atributo) throws EmptyAttributeException, UserNotFoundException {
        // pelo repo pegar o atributo
        User user = this.repository.getUser(login);

        return user.getAtributo(atributo);
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

    public int newSession(String login, String senha) throws UserNotFoundException, InvalidPasswordOrLoginException {
        Session session = this.repository.newSession(login, senha);
        return session.getId();
    }

//    public void sendFriendRequest(String senderLogin, String receiverLogin) {
//        User sender = repository.getUser(senderLogin);
//        User receiver = repository.getUser(receiverLogin);
//
//        if (sender != null && receiver != null) {
//            receiver.getReceivedInvitations().add(new FriendRequest(sender, receiver));
//        }
//    }
//
//    public void acceptFriendRequest(String receiverLogin, String senderLogin) {
//        User receiver = repository.getUser(receiverLogin);
//        User sender = repository.getUser(senderLogin);
//
//        if (receiver != null && sender != null) {
//            List<FriendRequest> requests = receiver.getReceivedInvitations();
//            requests.removeIf(request -> request.getSender().equals(sender));
//            receiver.addFriend(sender);
//            sender.addFriend(receiver);
//        }
//    }


}
