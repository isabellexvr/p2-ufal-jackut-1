package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um se tenta acessar um atributo de usuário que não existe.
 */
public class EmptyAttributeException extends Exception {
    /**
     * Cria uma nova instância de EmptyAttributeException com a mensagem padrão.
     */
    public EmptyAttributeException() {
        super("Atributo não preenchido.");
    }
}
