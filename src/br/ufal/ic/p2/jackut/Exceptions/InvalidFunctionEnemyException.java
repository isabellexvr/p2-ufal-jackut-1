package br.ufal.ic.p2.jackut.Exceptions;

public class InvalidFunctionEnemyException extends Exception {
    public InvalidFunctionEnemyException(String enemy) {
        super(String.format("Fun��o inv�lida: %s � seu inimigo.", enemy));
    }
}
