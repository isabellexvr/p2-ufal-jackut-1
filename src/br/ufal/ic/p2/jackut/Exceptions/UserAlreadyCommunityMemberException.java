package br.ufal.ic.p2.jackut.Exceptions;

public class UserAlreadyCommunityMemberException extends Exception {
    public UserAlreadyCommunityMemberException() {
        super("Usuario j� faz parte dessa comunidade.");
    }
}
