package br.ufal.ic.p2.jackut.Exceptions;

public class NoMessagesException extends Exception {
    public NoMessagesException() {
        super("Não há recados.");
    }
}
