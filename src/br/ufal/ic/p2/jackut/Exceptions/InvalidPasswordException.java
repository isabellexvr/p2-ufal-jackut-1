package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando se tenta cadastrar um usu�rio no sistema e a senha inserida � nula.
 */
public class InvalidPasswordException extends Exception {
    /**
     * Cria uma nova inst�ncia de InvalidPasswordException com a mensagem padr�o.
     */
    public InvalidPasswordException() {
        super("Senha inv�lida.");
    }
}
