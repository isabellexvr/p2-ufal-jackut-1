package br.ufal.ic.p2.jackut.Exceptions;

/**
 * Exceção lançada quando um usuário tenta adicionar como paquera alguém que já foi adicionado.
 */
public class AlreadyCrushException extends Exception {
    /**
     * Cria uma nova instância de AlreadyCrushException com a mensagem padrão.
     */
    public AlreadyCrushException() {
        super("Usuário já está adicionado como paquera.");
    }
}