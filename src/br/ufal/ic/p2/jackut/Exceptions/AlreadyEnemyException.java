package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um usuário tenta adicionar como inimigo alguém que já foi adicionado.
 */
public class AlreadyEnemyException extends Exception {
    /**
     * Cria uma nova instância de AlreadyEnemyException com a mensagem padrão.
     */
    public AlreadyEnemyException() {
        super("Usuário já está adicionado como inimigo.");
    }
}
