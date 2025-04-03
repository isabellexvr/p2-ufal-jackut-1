package br.ufal.ic.p2.jackut.Exceptions;

public class InvalidLoginException  extends Exception {
    public InvalidLoginException() {
        super("Login inválido.");
    }
}
