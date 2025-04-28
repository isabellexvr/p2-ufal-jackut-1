package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;

import java.io.Serializable;

/**
 * Classe principal do sistema Jackut, respons?vel por gerenciar usu?rios, sess?es, amizades e mensagens.
 * Esta classe atua como a interface do sistema, interagindo com o {@link Repository}.
 */
public class Jackut implements Serializable {
    private Repository repository;

    /**
     * Construtor da classe Jackut. Inicializa o reposit?rio.
     */
    public Jackut() {
        this.repository = Repository.getInstance();
    }

    /**
     * Reinicia o sistema, apagando todos os dados armazenados.
     */
    public void resetSystem() {
        this.repository.eraseEverything();
    }

    /**
     * Obt?m um atributo espec?fico de um usu?rio.
     *
     * @param login Login do usu?rio.
     * @param atributo Nome do atributo a ser recuperado.
     * @return O valor do atributo.
     * @throws EmptyAttributeException Se o atributo estiver vazio.
     * @throws UserNotFoundException Se o usu?rio n?o for encontrado.
     */
    public String getUserAttribute(String login, String atributo) throws EmptyAttributeException, UserNotFoundException {
        User user = this.repository.getUser(login);
        String attribute = user.getAtributo(atributo);

        if (attribute == null) {
            throw new EmptyAttributeException();
        }

        return attribute;
    }

    /**
     * Cria um novo usu?rio no sistema.
     *
     * @param login Login do usu?rio.
     * @param password Senha do usu?rio.
     * @param name Nome do usu?rio.
     * @throws UserAlreadyExistsException Se o usu?rio j? existir.
     * @throws InvalidLoginException Se o login for inv?lido.
     * @throws InvalidPasswordException Se a senha for inv?lida.
     */
    public void createUser(String login, String password, String name) throws UserAlreadyExistsException, InvalidLoginException, InvalidPasswordException {
        if (login == null) {
            throw new InvalidLoginException();
        } else if (password == null) {
            throw new InvalidPasswordException();
        } else {
            repository.addUser(login, password, name);
        }
    }

    /**
     * Cria uma nova sess?o para um usu?rio.
     *
     * @param login Login do usu?rio.
     * @param senha Senha do usu?rio.
     * @return O ID da nova sess?o.
     * @throws UserNotFoundException Se o usu?rio n?o for encontrado.
     * @throws InvalidPasswordOrLoginException Se o login ou a senha forem inv?lidos.
     */
    public String newSession(String login, String senha) throws UserNotFoundException, InvalidPasswordOrLoginException {
        Session session = this.repository.newSession(login, senha);
        return session.getId();
    }

    /**
     * Verifica se dois usu?rios s?o amigos.
     *
     * @param login Login do usu?rio.
     * @param friend Login do amigo.
     * @return true se forem amigos, false caso contr?rio.
     * @throws UserNotFoundException Se um dos usu?rios n?o for encontrado.
     */
    public boolean isFriend(String login, String friend) throws UserNotFoundException {
        User user = repository.getUser(login);
        return user.isFriend(friend);
    }

    /**
     * Adiciona um amigo a um usu?rio.
     *
     * @param userSessionId ID da sess?o do usu?rio.
     * @param friendLogin Login do amigo.
     * @throws UserNotFoundException Se o usu?rio ou amigo n?o forem encontrados.
     * @throws AlreadyFriendException Se j? forem amigos.
     * @throws WaitingToAcceptException Se h? uma solicita??o pendente.
     */
    public void addFriend(String userSessionId, String friendLogin) throws UserNotFoundException, AlreadyFriendException, WaitingToAcceptException, CantAddItselfException {
        User user = this.repository.getUserBySessionId(userSessionId);

        if (user.isFriend(friendLogin)) {
            throw new AlreadyFriendException();
        }
        user.addFriend(friendLogin);
    }

    /**
     * Salva os dados e encerra o sistema.
     */
    public void endSystem() {
        this.repository.saveData();
    }

    /**
     * Edita o perfil de um usu?rio.
     *
     * @param id ID da sess?o do usu?rio.
     * @param atributo Nome do atributo a ser editado.
     * @param valor Novo valor do atributo.
     * @throws UserNotFoundException Se o usu?rio n?o for encontrado.
     * @throws EmptyAttributeException Se o atributo for inv?lido.
     */
    public void editProfile(String id, String atributo, String valor) throws UserNotFoundException, EmptyAttributeException {
        if (id.isEmpty()) {
            throw new UserNotFoundException();
        }

        Session session = this.repository.getSession(id);

        if (session == null) {
            throw new UserNotFoundException();
        }

        this.repository.editProfile(session, atributo, valor);
    }

    /**
     * Retorna a lista de amigos de um usu?rio.
     *
     * @param login Login do usu?rio.
     * @return Lista de amigos formatada como uma string.
     * @throws UserNotFoundException Se o usu?rio n?o for encontrado.
     */
    public String getFriends(String login) throws UserNotFoundException {
        User user = this.repository.getUser(login);
        return "{" + String.join(",", user.getFriends()) + "}";
    }

    /**
     * Envia uma mensagem para outro usu?rio.
     *
     * @param sessionId ID da sess?o do remetente.
     * @param receiverLogin Login do destinat?rio.
     * @param message Conte?do da mensagem.
     * @throws UserNotFoundException Se o remetente ou destinat?rio n?o for encontrado.
     * @throws CantMessageItselfException Se o usu?rio tentar enviar uma mensagem para si mesmo.
     */
    public void sendMessage(String sessionId, String receiverLogin, String message) throws UserNotFoundException, CantMessageItselfException {
        User sender = this.repository.getUserBySessionId(sessionId);
        User receiver = this.repository.getUser(receiverLogin);

        if (sender.getLogin().equals(receiverLogin)) {
            throw new CantMessageItselfException();
        }

        Message m = new Message(sender, receiver, message);
        sender.sendMessage(m);
    }

    /**
     * Obt?m a primeira mensagem recebida por um usu?rio.
     *
     * @param sessionId ID da sess?o do usu?rio.
     * @return O conte?do da primeira mensagem.
     * @throws UserNotFoundException Se o usu?rio n?o for encontrado.
     * @throws NoMessagesException Se o usu?rio n?o tiver mensagens.
     */
    public String getFirstMessage(String sessionId) throws UserNotFoundException, NoMessagesException {
        User user = this.repository.getUserBySessionId(sessionId);
        return user.getFirstMessage();
    }

    public void createCommunity(String sessionId, String name, String descricao) throws UserNotFoundException, CommunityAlreadyExistsException {
        if (this.repository.isCommunityCreated(name)) {
            throw new CommunityAlreadyExistsException();
        }

        User owner = this.repository.getUserBySessionId(sessionId);

        Community newCommunity = new Community(name, descricao, owner);
        this.repository.newCommunity(name, newCommunity);
    }

    public String getCommunityDescription(String communityName) throws CommunityDoesntExistException{
        return this.repository.getCommunityDescription(communityName);
    }

    public String getCommunitOwner(String communityName) throws CommunityDoesntExistException{
        return this.repository.getCommunityOwner(communityName);
    }

    public String getMembrosComunidade(String communityName) throws CommunityDoesntExistException{
        Community community = this.repository.getCommunityByName(communityName);

        return "{" + String.join(",", community.getMembers()) + "}";


    }
}
