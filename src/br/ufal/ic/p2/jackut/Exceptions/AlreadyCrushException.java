package br.ufal.ic.p2.jackut.Exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar como paquera algu�m que j� foi adicionado.
 */
public class AlreadyCrushException extends Exception {
    /**
     * Cria uma nova inst�ncia de AlreadyCrushException com a mensagem padr�o.
     */
    public AlreadyCrushException() {
        super("Usu�rio j� est� adicionado como paquera.");
    }
}