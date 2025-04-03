package br.ufal.ic.p2.jackut.Exceptions;

public class WaitingToAcceptException extends Exception {
    public WaitingToAcceptException() {
        super("Usuário já está adicionado como amigo, esperando aceitação do convite.");
    }
}
