package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando se tenta cadastrar um usu�rio com um nome j� existente.
 */
public class UserAlreadyExistsException extends Exception {
    /**
     * Cria uma nova inst�ncia de UserAlreadyExistsException com a mensagem padr�o.
     */
    public UserAlreadyExistsException() {
        super("Conta com esse nome j� existe.");
    }
}
