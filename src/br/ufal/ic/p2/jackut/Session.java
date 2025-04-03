package br.ufal.ic.p2.jackut;

import java.io.Serializable;

/**
 * Representa uma sessão de um usuário no sistema Jackut.
 * Cada sessão é identificada por um ID único e está associada a um usuário específico.
 */
public class Session implements Serializable {
    private String id;
    private User user;

    /**
     * Constrói uma nova sessão para um usuário.
     *
     * @param id   O identificador único da sessão.
     * @param user O usuário associado a esta sessão.
     */
    public Session(String id, User user) {
        this.id = id;
        this.user = user;
    }

    /**
     * Obtém o usuário associado a esta sessão.
     *
     * @return O usuário desta sessão.
     */
    public User getUser() {
        return user;
    }

    /**
     * Obtém o identificador único desta sessão.
     *
     * @return O ID da sessão.
     */
    public String getId() {
        return id;
    }
}
