package br.ufal.ic.p2.jackut.Exceptions;

public class SelfFanException extends Exception {
    public SelfFanException() {
        super("Usu�rio n�o pode ser f� de si mesmo.");
    }
}
