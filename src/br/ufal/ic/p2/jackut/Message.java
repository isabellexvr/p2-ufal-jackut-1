package br.ufal.ic.p2.jackut;

import java.io.Serializable;

/**
 * Representa uma mensagem enviada de um usuário para outro no sistema Jackut.
 * Esta classe implementa {@link Serializable} para permitir a persistência de mensagens.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private User senderSessionId;  // Usuário que enviou a mensagem
    private User receiverLogin;    // Usuário que recebeu a mensagem
    private String text;           // Conteúdo da mensagem

    /**
     * Constrói uma mensagem com um remetente, destinatário e conteúdo.
     *
     * @param senderSessionId O usuário que enviou a mensagem.
     * @param receiverLogin O usuário que receberá a mensagem.
     * @param text O conteúdo da mensagem.
     */
    public Message(User senderSessionId, User receiverLogin, String text) {
        this.senderSessionId = senderSessionId;
        this.receiverLogin = receiverLogin;
        this.text = text;
    }

    /**
     * Obtém o conteúdo da mensagem.
     *
     * @return O texto da mensagem.
     */
    public String getText() {
        return text;
    }

    /**
     * Obtém o usuário que enviou a mensagem.
     *
     * @return O remetente da mensagem.
     */
    public User getSender() {
        return senderSessionId;
    }

    /**
     * Obtém o usuário que recebeu a mensagem.
     *
     * @return O destinatário da mensagem.
     */
    public User getReceiver() {
        return receiverLogin;
    }
}
