package br.ufal.ic.p2.jackut.Exceptions;

public class NoCommunityMessagesException extends Exception {
    public NoCommunityMessagesException() {
        super("Não há mensagens.");
    }
}
