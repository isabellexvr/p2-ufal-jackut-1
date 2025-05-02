package br.ufal.ic.p2.jackut.Exceptions;

public class AlreadyCrushException extends Exception {
    public AlreadyCrushException() {
        super("Usuário já está adicionado como paquera.");
    }
}
