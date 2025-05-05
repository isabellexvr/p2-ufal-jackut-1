package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um usuário tenta criar uma comunidade com um nome já existente.
 */
public class CommunityAlreadyExistsException extends Exception {
    /**
     * Cria uma nova instância de CommunityAlreadyExistsException com a mensagem padrão.
     */
     public CommunityAlreadyExistsException() {
         super("Comunidade com esse nome já existe.");
     }
}
