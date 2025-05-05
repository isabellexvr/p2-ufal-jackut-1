package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar como inimigo algu�m que j� foi adicionado.
 */
public class AlreadyEnemyException extends Exception {
    /**
     * Cria uma nova inst�ncia de AlreadyEnemyException com a mensagem padr�o.
     */
    public AlreadyEnemyException() {
        super("Usu�rio j� est� adicionado como inimigo.");
    }
}
