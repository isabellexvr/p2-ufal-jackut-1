package br.ufal.ic.p2.jackut.Exceptions;

public class AlreadyFriendException extends Exception{
    public AlreadyFriendException(){
        super("Usu�rio j� est� adicionado como amigo.");
    }
}