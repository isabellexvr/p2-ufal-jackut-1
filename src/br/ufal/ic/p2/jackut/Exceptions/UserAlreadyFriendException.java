package br.ufal.ic.p2.jackut.Exceptions;

public class UserAlreadyFriendException extends Exception {
    public UserAlreadyFriendException(){
        super("Usuário já está adicionado como amigo, esperando aceitação do convite.");
    }
}
