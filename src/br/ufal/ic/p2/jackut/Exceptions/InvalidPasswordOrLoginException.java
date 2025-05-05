package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando se tenta iniciar uma sessão com as credenciais erradas.
 */
public class InvalidPasswordOrLoginException extends Exception {
    /**
     * Cria uma nova instância de InvalidPasswordOrLoginException com a mensagem padrão.
     */
    public InvalidPasswordOrLoginException() {
        super("Login ou senha inválidos.");
    }
}
