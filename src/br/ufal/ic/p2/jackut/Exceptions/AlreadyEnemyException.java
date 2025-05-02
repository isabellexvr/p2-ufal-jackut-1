package br.ufal.ic.p2.jackut.Exceptions;

public class AlreadyEnemyException extends Exception {
    public AlreadyEnemyException() {
        super("Usuário já está adicionado como inimigo.");
    }
}
