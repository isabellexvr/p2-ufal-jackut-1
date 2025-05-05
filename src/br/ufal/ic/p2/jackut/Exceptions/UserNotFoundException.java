package br.ufal.ic.p2.jackut.Exceptions;

import java.nio.charset.StandardCharsets;
/**
 * Exceção lançada quando se tenta buscar por um usuário que não está cadastrado no sistema.
 */
public class UserNotFoundException extends Exception {
    /**
     * Cria uma nova instância de UserNotFoundException com a mensagem padrão.
     */
    public UserNotFoundException() {
        super("Usuário não cadastrado.");
    }
}

