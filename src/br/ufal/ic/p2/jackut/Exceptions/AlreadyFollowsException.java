package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um usuário tenta adicionar como ídolo alguém que já foi adicionado.
 */
public class AlreadyFollowsException extends RuntimeException {
    /**
     * Cria uma nova instância de AlreadyFollowsException com a mensagem padrão.
     */
    public AlreadyFollowsException() {
        super("Usuário já está adicionado como ídolo.");
    }
}
