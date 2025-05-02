package br.ufal.ic.p2.jackut.Exceptions;

public class CrushItselfException extends Exception {
    public CrushItselfException() {
        super("Usuário não pode ser paquera de si mesmo.");
    }
}
