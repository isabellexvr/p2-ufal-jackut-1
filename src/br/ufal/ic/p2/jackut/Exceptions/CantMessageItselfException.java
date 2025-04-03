package br.ufal.ic.p2.jackut.Exceptions;

public class CantMessageItselfException extends Exception {
    public CantMessageItselfException() {
        super("Usuário não pode enviar recado para si mesmo.");
    }
}
