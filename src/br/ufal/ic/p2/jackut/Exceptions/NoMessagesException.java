package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando o usu�rio tenta ler recados, mas n�o h� mais nenhum para se ser lida.
 */
public class NoMessagesException extends Exception {
    /**
     * Cria uma nova inst�ncia de NoMessagesException com a mensagem padr�o.
     */
    public NoMessagesException() {
        super("N�o h� recados.");
    }
}
