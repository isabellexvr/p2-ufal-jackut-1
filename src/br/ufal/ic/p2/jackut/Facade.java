package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;
import easyaccept.EasyAccept;

public class Facade {

    private Jackut jackut = new Jackut();

    public void zerarSistema() {
        this.jackut = new Jackut();
    }

    public void criarUsuario(String login, String password, String nome) throws InvalidPasswordException, InvalidLoginException,UserAlreadyExistsException {
        this.jackut.createUser(login, password, nome);
    }


    public String getAtributoUsuario(String login, String atributo) throws EmptyAttributeException, UserNotFoundException {
        return this.jackut.getUserAttribute(login, atributo);
    }

    public int abrirSessao(String login, String senha) throws UserNotFoundException, InvalidPasswordOrLoginException {
        return this.jackut.newSession(login, senha);
    }




}
