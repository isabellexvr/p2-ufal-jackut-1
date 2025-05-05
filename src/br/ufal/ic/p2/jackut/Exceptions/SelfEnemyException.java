package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um usuário tenta adicionar como inimigo a si mesmo.
 */
public class SelfEnemyException extends Exception {
    /**
     * Cria uma nova instância de SelfEnemyException com a mensagem padrão.
     */
    public SelfEnemyException(){
        super("Usuário não pode ser inimigo de si mesmo.");
    }
}
