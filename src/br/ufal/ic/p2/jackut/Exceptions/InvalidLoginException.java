package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando se tenta cadastrar um usuário no sistema e o login inserido é nulo.
 */
public class InvalidLoginException  extends Exception {
    /**
     * Cria uma nova instância de InvalidLoginException com a mensagem padrão.
     */
    public InvalidLoginException() {
        super("Login inválido.");
    }
}
