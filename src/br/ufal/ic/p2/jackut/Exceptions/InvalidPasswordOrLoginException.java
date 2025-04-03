package br.ufal.ic.p2.jackut.Exceptions;

public class InvalidPasswordOrLoginException extends Exception {
    public InvalidPasswordOrLoginException() {
        super("Login ou senha inválidos.");
    }
}
