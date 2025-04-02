package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;
import easyaccept.EasyAccept;

import java.util.List;

public class Facade {

    private final Jackut jackut = new Jackut();

    public void zerarSistema() {
        this.jackut.resetSystem();
    }

    public void criarUsuario(String login, String password, String nome) throws InvalidPasswordException, InvalidLoginException,UserAlreadyExistsException {
        this.jackut.createUser(login, password, nome);
    }

    public String getAtributoUsuario(String login, String atributo) throws EmptyAttributeException, UserNotFoundException {
        return this.jackut.getUserAttribute(login, atributo);
    }

    public String abrirSessao(String login, String senha) throws UserNotFoundException, InvalidPasswordOrLoginException {
        return this.jackut.newSession(login, senha);
    }

    public void encerrarSistema() {
        this.jackut.endSystem();
    }

    public void editarPerfil(String id, String atributo, String valor) throws UserNotFoundException, EmptyAttributeException{
        // id é da sessão
        // deve estar logado, existir sessão, pra poder editar
        try{
            this.jackut.editProfile(id, atributo, valor);
        }catch(EmptyAttributeException e){
            System.out.println(e.getMessage());
        }

    }

    public void adicionarAmigo(String id, String amigo) throws Exception, UserNotFoundException, WaitingToAcceptException, AlreadyFriendException {
        this.jackut.addFriend(id, amigo);

    }

    public List<String> getAmigos(){
        return null;
    }

    public boolean ehAmigo(String login, String amigo) throws Exception{
        boolean isAmigo = false;
        try{
            isAmigo = this.jackut.isFriend(login, amigo);
            return isAmigo;

        }catch (UserNotFoundException e){
            System.out.println(e.getMessage());
            return false;
        }

    }




}
