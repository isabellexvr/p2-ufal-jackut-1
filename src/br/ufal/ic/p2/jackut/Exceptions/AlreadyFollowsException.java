package br.ufal.ic.p2.jackut.Exceptions;

public class AlreadyFollowsException extends RuntimeException {
    public AlreadyFollowsException() {
        super("Usu�rio j� est� adicionado como �dolo.");
    }
}
