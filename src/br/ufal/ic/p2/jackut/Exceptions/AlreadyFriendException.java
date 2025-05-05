package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando um usuário tenta adicionar como amigo alguém que já foi adicionado.
 */
public class AlreadyFriendException extends Exception{
    /**
     * Cria uma nova instância de AlreadyFriendException com a mensagem padrão.
     */
    public AlreadyFriendException(){
        super("Usuário já está adicionado como amigo.");
    }
}