package br.ufal.ic.p2.jackut.Exceptions;

public class WaitingToAcceptException extends Exception {
    public WaitingToAcceptException() {
        super("Usu�rio j� est� adicionado como amigo, esperando aceita��o do convite.");
    }
}
