package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um usuário tenta adicionar como amigo a si mesmo.
 */
public class CantAddItselfException extends Exception {
    /**
     * Cria uma nova instância de CantAddItselfException com a mensagem padrão.
     */
    public CantAddItselfException() {
        super("Usuário não pode adicionar a si mesmo como amigo.");
    }
}
