package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando o usuário tenta ler mensagens, mas não há mais nenhuma para se ser lida.
 */
public class NoCommunityMessagesException extends Exception {
    /**
     * Cria uma nova instância de NoCommunityMessagesException com a mensagem padrão.
     */
    public NoCommunityMessagesException() {
        super("Não há mensagens.");
    }
}
