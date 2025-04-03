package br.ufal.ic.p2.jackut;

import java.io.Serializable;

/**
 * Representa uma mensagem enviada de um usu�rio para outro no sistema Jackut.
 * Esta classe implementa {@link Serializable} para permitir a persist�ncia de mensagens.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private User senderSessionId;  // Usu�rio que enviou a mensagem
    private User receiverLogin;    // Usu�rio que recebeu a mensagem
    private String text;           // Conte�do da mensagem

    /**
     * Constr�i uma mensagem com um remetente, destinat�rio e conte�do.
     *
     * @param senderSessionId O usu�rio que enviou a mensagem.
     * @param receiverLogin O usu�rio que receber� a mensagem.
     * @param text O conte�do da mensagem.
     */
    public Message(User senderSessionId, User receiverLogin, String text) {
        this.senderSessionId = senderSessionId;
        this.receiverLogin = receiverLogin;
        this.text = text;
    }

    /**
     * Obt�m o conte�do da mensagem.
     *
     * @return O texto da mensagem.
     */
    public String getText() {
        return text;
    }

    /**
     * Obt�m o usu�rio que enviou a mensagem.
     *
     * @return O remetente da mensagem.
     */
    public User getSender() {
        return senderSessionId;
    }

    /**
     * Obt�m o usu�rio que recebeu a mensagem.
     *
     * @return O destinat�rio da mensagem.
     */
    public User getReceiver() {
        return receiverLogin;
    }
}
