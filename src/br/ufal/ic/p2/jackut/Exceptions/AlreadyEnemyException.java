package br.ufal.ic.p2.jackut.Exceptions;

public class AlreadyEnemyException extends Exception {
    public AlreadyEnemyException() {
        super("Usu�rio j� est� adicionado como inimigo.");
    }
}
