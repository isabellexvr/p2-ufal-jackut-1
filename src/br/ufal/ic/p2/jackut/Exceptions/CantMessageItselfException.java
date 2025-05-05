package br.ufal.ic.p2.jackut.Exceptions;

/**
 * Exceção lançada quando um usuário tenta enviar mensagem para si mesmo.
 */
public class CantMessageItselfException extends Exception {
    /**
     * Cria uma nova instância de CantMessageItselfException com a mensagem padrão.
     */
    public CantMessageItselfException() {
        super("Usuário não pode enviar recado para si mesmo.");
    }
}
