package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um usu�rio tenta criar uma comunidade com um nome j� existente.
 */
public class CommunityAlreadyExistsException extends Exception {
    /**
     * Cria uma nova inst�ncia de CommunityAlreadyExistsException com a mensagem padr�o.
     */
     public CommunityAlreadyExistsException() {
         super("Comunidade com esse nome j� existe.");
     }
}
