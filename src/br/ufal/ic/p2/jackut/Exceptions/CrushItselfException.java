package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar como paquera a si mesmo.
 */
public class CrushItselfException extends Exception {
    /**
     * Cria uma nova inst�ncia de CrushItselfException com a mensagem padr�o.
     */
    public CrushItselfException() {
        super("Usu�rio n�o pode ser paquera de si mesmo.");
    }
}
