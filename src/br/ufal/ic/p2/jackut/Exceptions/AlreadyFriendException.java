package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar como amigo algu�m que j� foi adicionado.
 */
public class AlreadyFriendException extends Exception{
    /**
     * Cria uma nova inst�ncia de AlreadyFriendException com a mensagem padr�o.
     */
    public AlreadyFriendException(){
        super("Usu�rio j� est� adicionado como amigo.");
    }
}