package br.ufal.ic.p2.jackut.Exceptions;

public class UserAlreadyCommunityMemberException extends Exception {
    public UserAlreadyCommunityMemberException() {
        super("Usuario já faz parte dessa comunidade.");
    }
}
