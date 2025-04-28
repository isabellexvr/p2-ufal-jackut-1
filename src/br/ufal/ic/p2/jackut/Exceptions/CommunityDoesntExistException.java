package br.ufal.ic.p2.jackut.Exceptions;

public class CommunityDoesntExistException extends Exception {
    public CommunityDoesntExistException() {
        super("Comunidade não existe.");
    }
}
