package br.ufal.ic.p2.jackut.Exceptions;

/**
 * Exce��o lan�ada quando um usu�rio tenta enviar mensagem para si mesmo.
 */
public class CantMessageItselfException extends Exception {
    /**
     * Cria uma nova inst�ncia de CantMessageItselfException com a mensagem padr�o.
     */
    public CantMessageItselfException() {
        super("Usu�rio n�o pode enviar recado para si mesmo.");
    }
}
