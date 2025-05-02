package br.ufal.ic.p2.jackut.Exceptions;

public class SelfEnemyException extends Exception {
    public SelfEnemyException(){
        super("Usuário não pode ser inimigo de si mesmo.");
    }
}
