package br.ufal.ic.p2.jackut.Exceptions;

public class CantAddItselfException extends Exception {
    public CantAddItselfException() {
        super("Usuário não pode adicionar a si mesmo como amigo.");
    }
}
