package br.ufal.ic.p2.jackut;

import java.io.Serializable;

/**
 * Representa uma sess�o de um usu�rio no sistema Jackut.
 * Cada sess�o � identificada por um ID �nico e est� associada a um usu�rio espec�fico.
 */
public class Session implements Serializable {
    private String id;
    private User user;

    /**
     * Constr�i uma nova sess�o para um usu�rio.
     *
     * @param id   O identificador �nico da sess�o.
     * @param user O usu�rio associado a esta sess�o.
     */
    public Session(String id, User user) {
        this.id = id;
        this.user = user;
    }

    /**
     * Obt�m o usu�rio associado a esta sess�o.
     *
     * @return O usu�rio desta sess�o.
     */
    public User getUser() {
        return user;
    }

    /**
     * Obt�m o identificador �nico desta sess�o.
     *
     * @return O ID da sess�o.
     */
    public String getId() {
        return id;
    }
}
