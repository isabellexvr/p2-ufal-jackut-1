package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar como amigo a si mesmo.
 */
public class CantAddItselfException extends Exception {
    /**
     * Cria uma nova inst�ncia de CantAddItselfException com a mensagem padr�o.
     */
    public CantAddItselfException() {
        super("Usu�rio n�o pode adicionar a si mesmo como amigo.");
    }
}
