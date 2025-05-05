package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando se tenta cadastrar um usu�rio no sistema e o login inserido � nulo.
 */
public class InvalidLoginException  extends Exception {
    /**
     * Cria uma nova inst�ncia de InvalidLoginException com a mensagem padr�o.
     */
    public InvalidLoginException() {
        super("Login inv�lido.");
    }
}
