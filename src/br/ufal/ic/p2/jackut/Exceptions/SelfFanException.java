package br.ufal.ic.p2.jackut.Exceptions;

public class SelfFanException extends Exception {
    public SelfFanException() {
        super("Usuário não pode ser fã de si mesmo.");
    }
}
