package br.ufal.ic.p2.jackut.Exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("Conta com esse nome já existe.");
    }
}
