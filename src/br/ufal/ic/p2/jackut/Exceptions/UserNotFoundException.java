package br.ufal.ic.p2.jackut.Exceptions;

import java.nio.charset.StandardCharsets;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("Usuário não cadastrado.");
    }
}

