package br.ufal.ic.p2.jackut.Exceptions;

public class UserAlreadyFriendException extends Exception {
    public UserAlreadyFriendException(){
        super("Usu�rio j� est� adicionado como amigo, esperando aceita��o do convite.");
    }
}
