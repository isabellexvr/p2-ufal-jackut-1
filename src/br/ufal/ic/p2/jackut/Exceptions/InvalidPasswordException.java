package br.ufal.ic.p2.jackut.Exceptions;

public class InvalidPasswordException extends Exception {

    public InvalidPasswordException() {
        super("Senha inválida.");
    }
}
