package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar como amigo a si mesmo.
 */
public class WaitingToAcceptException extends Exception {
    /**
     * Cria uma nova inst�ncia de WaitingToAcceptException com a mensagem padr�o.
     */
    public WaitingToAcceptException() {
        super("Usu�rio j� est� adicionado como amigo, esperando aceita��o do convite.");
    }
}
