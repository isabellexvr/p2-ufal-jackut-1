package br.ufal.ic.p2.jackut.Exceptions;

public class AlreadyFriendException extends Exception{
    public AlreadyFriendException(){
        super("Usuário já está adicionado como amigo.");
    }
}