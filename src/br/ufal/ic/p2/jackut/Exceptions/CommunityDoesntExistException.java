package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando se tenta acessar uma comunidade que não existe.
 */
public class CommunityDoesntExistException extends Exception {
    /**
     * Cria uma nova instância de CommunityDoesntExistException com a mensagem padrão.
     */
    public CommunityDoesntExistException() {
        super("Comunidade não existe.");
    }
}
