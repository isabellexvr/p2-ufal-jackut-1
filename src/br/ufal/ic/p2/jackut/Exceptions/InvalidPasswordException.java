package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando se tenta cadastrar um usuário no sistema e a senha inserida é nula.
 */
public class InvalidPasswordException extends Exception {
    /**
     * Cria uma nova instância de InvalidPasswordException com a mensagem padrão.
     */
    public InvalidPasswordException() {
        super("Senha inválida.");
    }
}
