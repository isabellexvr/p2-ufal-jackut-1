package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um usuário tenta entrar numa comunidade em que já está.
 */
public class UserAlreadyCommunityMemberException extends Exception {
    /**
     * Cria uma nova instância de UserAlreadyCommunityMemberException com a mensagem padrão.
     */
    public UserAlreadyCommunityMemberException() {
        super("Usuario já faz parte dessa comunidade.");
    }
}
