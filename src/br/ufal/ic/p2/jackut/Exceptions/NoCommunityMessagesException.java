package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando o usu�rio tenta ler mensagens, mas n�o h� mais nenhuma para se ser lida.
 */
public class NoCommunityMessagesException extends Exception {
    /**
     * Cria uma nova inst�ncia de NoCommunityMessagesException com a mensagem padr�o.
     */
    public NoCommunityMessagesException() {
        super("N�o h� mensagens.");
    }
}
