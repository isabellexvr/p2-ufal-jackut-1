package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um usu�rio tenta fazer alguma a��o bloqueada por conta do status de inimizade.
 */
public class InvalidFunctionEnemyException extends Exception {
    /**
     * Cria uma nova inst�ncia de InvalidFunctionEnemyException com a mensagem padr�o.
     */
    public InvalidFunctionEnemyException(String enemy) {
        super(String.format("Fun��o inv�lida: %s � seu inimigo.", enemy));
    }
}
