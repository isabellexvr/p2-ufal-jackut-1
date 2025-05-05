package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar como f� a si mesmo.
 */
public class SelfFanException extends Exception {
    /**
     * Cria uma nova inst�ncia de SelfFanException com a mensagem padr�o.
     */
    public SelfFanException() {
        super("Usu�rio n�o pode ser f� de si mesmo.");
    }
}
