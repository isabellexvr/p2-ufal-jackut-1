package br.ufal.ic.p2.jackut.Exceptions;

public class CantMessageItselfException extends Exception {
    public CantMessageItselfException() {
        super("Usu�rio n�o pode enviar recado para si mesmo.");
    }
}
