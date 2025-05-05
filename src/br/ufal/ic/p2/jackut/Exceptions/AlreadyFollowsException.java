package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar como �dolo algu�m que j� foi adicionado.
 */
public class AlreadyFollowsException extends RuntimeException {
    /**
     * Cria uma nova inst�ncia de AlreadyFollowsException com a mensagem padr�o.
     */
    public AlreadyFollowsException() {
        super("Usu�rio j� est� adicionado como �dolo.");
    }
}
