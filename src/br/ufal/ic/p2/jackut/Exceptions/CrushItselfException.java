package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um usuário tenta adicionar como paquera a si mesmo.
 */
public class CrushItselfException extends Exception {
    /**
     * Cria uma nova instância de CrushItselfException com a mensagem padrão.
     */
    public CrushItselfException() {
        super("Usuário não pode ser paquera de si mesmo.");
    }
}
