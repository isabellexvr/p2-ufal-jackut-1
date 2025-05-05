package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um usuário tenta adicionar como amigo a si mesmo.
 */
public class WaitingToAcceptException extends Exception {
    /**
     * Cria uma nova instância de WaitingToAcceptException com a mensagem padrão.
     */
    public WaitingToAcceptException() {
        super("Usuário já está adicionado como amigo, esperando aceitação do convite.");
    }
}
