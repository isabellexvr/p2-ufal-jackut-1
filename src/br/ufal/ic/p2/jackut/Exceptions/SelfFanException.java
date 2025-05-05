package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um usuário tenta adicionar como fã a si mesmo.
 */
public class SelfFanException extends Exception {
    /**
     * Cria uma nova instância de SelfFanException com a mensagem padrão.
     */
    public SelfFanException() {
        super("Usuário não pode ser fã de si mesmo.");
    }
}
