package br.ufal.ic.p2.jackut.Exceptions;

public class AlreadyFollowsException extends RuntimeException {
    public AlreadyFollowsException() {
        super("Usuário já está adicionado como ídolo.");
    }
}
