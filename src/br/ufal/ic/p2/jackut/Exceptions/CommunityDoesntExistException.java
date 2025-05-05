package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando se tenta acessar uma comunidade que n�o existe.
 */
public class CommunityDoesntExistException extends Exception {
    /**
     * Cria uma nova inst�ncia de CommunityDoesntExistException com a mensagem padr�o.
     */
    public CommunityDoesntExistException() {
        super("Comunidade n�o existe.");
    }
}
