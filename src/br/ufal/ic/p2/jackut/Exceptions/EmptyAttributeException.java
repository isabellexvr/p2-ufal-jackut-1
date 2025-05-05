package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um se tenta acessar um atributo de usu�rio que n�o existe.
 */
public class EmptyAttributeException extends Exception {
    /**
     * Cria uma nova inst�ncia de EmptyAttributeException com a mensagem padr�o.
     */
    public EmptyAttributeException() {
        super("Atributo n�o preenchido.");
    }
}
