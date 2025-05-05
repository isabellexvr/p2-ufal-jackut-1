package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exceção lançada quando o usuário tenta ler recados, mas não há mais nenhum para se ser lida.
 */
public class NoMessagesException extends Exception {
    /**
     * Cria uma nova instância de NoMessagesException com a mensagem padrão.
     */
    public NoMessagesException() {
        super("Não há recados.");
    }
}
