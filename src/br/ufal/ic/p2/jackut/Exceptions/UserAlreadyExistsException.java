package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando se tenta cadastrar um usuário com um nome já existente.
 */
public class UserAlreadyExistsException extends Exception {
    /**
     * Cria uma nova instância de UserAlreadyExistsException com a mensagem padrão.
     */
    public UserAlreadyExistsException() {
        super("Conta com esse nome já existe.");
    }
}
