package br.ufal.ic.p2.jackut.Exceptions;

import java.nio.charset.StandardCharsets;
/**
 * Exce��o lan�ada quando se tenta buscar por um usu�rio que n�o est� cadastrado no sistema.
 */
public class UserNotFoundException extends Exception {
    /**
     * Cria uma nova inst�ncia de UserNotFoundException com a mensagem padr�o.
     */
    public UserNotFoundException() {
        super("Usu�rio n�o cadastrado.");
    }
}

