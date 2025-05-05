package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um usu�rio tenta entrar numa comunidade em que j� est�.
 */
public class UserAlreadyCommunityMemberException extends Exception {
    /**
     * Cria uma nova inst�ncia de UserAlreadyCommunityMemberException com a mensagem padr�o.
     */
    public UserAlreadyCommunityMemberException() {
        super("Usuario j� faz parte dessa comunidade.");
    }
}
