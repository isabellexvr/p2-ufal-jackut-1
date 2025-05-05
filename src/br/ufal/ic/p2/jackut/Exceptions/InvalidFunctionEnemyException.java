package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um usuário tenta fazer alguma ação bloqueada por conta do status de inimizade.
 */
public class InvalidFunctionEnemyException extends Exception {
    /**
     * Cria uma nova instância de InvalidFunctionEnemyException com a mensagem padrão.
     */
    public InvalidFunctionEnemyException(String enemy) {
        super(String.format("Função inválida: %s é seu inimigo.", enemy));
    }
}
