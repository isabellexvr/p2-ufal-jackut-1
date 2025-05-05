package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando se tenta iniciar uma sess�o com as credenciais erradas.
 */
public class InvalidPasswordOrLoginException extends Exception {
    /**
     * Cria uma nova inst�ncia de InvalidPasswordOrLoginException com a mensagem padr�o.
     */
    public InvalidPasswordOrLoginException() {
        super("Login ou senha inv�lidos.");
    }
}
