package br.ufal.ic.p2.jackut.Exceptions;

public class EmptyAttributeException extends Exception {
    public EmptyAttributeException() {
        super("Atributo n�o preenchido.");
    }
}
