package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;

import java.io.Serializable;

/**
 * Classe principal do sistema Jackut, responsável por gerenciar usuários, sessões, amizades e mensagens.
 * Esta classe atua como a interface do sistema, interagindo com o {@link Repository}.
 */
public class Jackut implements Serializable {
    private Repository repository;

    /**
     * Construtor da classe Jackut. Inicializa o repositório.
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
     * Obtém um atributo específico de um usuário.
     *
     * @param login Login do usuário.
     * @param atributo Nome do atributo a ser recuperado.
     * @return O valor do atributo.
     * @throws EmptyAttributeException Se o atributo estiver vazio.
     * @throws UserNotFoundException Se o usuário não for encontrado.
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
     * Cria um novo usuário no sistema.
     *
     * @param login Login do usuário.
     * @param password Senha do usuário.
     * @param name Nome do usuário.
     * @throws UserAlreadyExistsException Se o usuário já existir.
     * @throws InvalidLoginException Se o login for inválido.
     * @throws InvalidPasswordException Se a senha for inválida.
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
     * Cria uma nova sessão para um usuário.
     *
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @return O ID da nova sessão.
     * @throws UserNotFoundException Se o usuário não for encontrado.
     * @throws InvalidPasswordOrLoginException Se o login ou a senha forem inválidos.
     */
    public String newSession(String login, String senha) throws UserNotFoundException, InvalidPasswordOrLoginException {
        Session session = this.repository.newSession(login, senha);
        return session.getId();
    }

    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login Login do usuário.
     * @param friend Login do amigo.
     * @return true se forem amigos, false caso contrário.
     * @throws UserNotFoundException Se um dos usuários não for encontrado.
     */
    public boolean isFriend(String login, String friend) throws UserNotFoundException {
        User user = repository.getUser(login);
        return user.isFriend(friend);
    }

    /**
     * Adiciona um amigo a um usuário.
     *
     * @param userSessionId ID da sessão do usuário.
     * @param friendLogin Login do amigo.
     * @throws UserNotFoundException Se o usuário ou amigo não forem encontrados.
     * @throws AlreadyFriendException Se já forem amigos.
     * @throws WaitingToAcceptException Se há uma solicitação pendente.
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
     * Edita o perfil de um usuário.
     *
     * @param id ID da sessão do usuário.
     * @param atributo Nome do atributo a ser editado.
     * @param valor Novo valor do atributo.
     * @throws UserNotFoundException Se o usuário não for encontrado.
     * @throws EmptyAttributeException Se o atributo for inválido.
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
     * Retorna a lista de amigos de um usuário.
     *
     * @param login Login do usuário.
     * @return Lista de amigos formatada como uma string.
     * @throws UserNotFoundException Se o usuário não for encontrado.
     */
    public String getFriends(String login) throws UserNotFoundException {
        User user = this.repository.getUser(login);
        return "{" + String.join(",", user.getFriends()) + "}";
    }

    /**
     * Envia uma mensagem para outro usuário.
     *
     * @param sessionId ID da sessão do remetente.
     * @param receiverLogin Login do destinatário.
     * @param message Conteúdo da mensagem.
     * @throws UserNotFoundException Se o remetente ou destinatário não for encontrado.
     * @throws CantMessageItselfException Se o usuário tentar enviar uma mensagem para si mesmo.
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
     * Obtém a primeira mensagem recebida por um usuário.
     *
     * @param sessionId ID da sessão do usuário.
     * @return O conteúdo da primeira mensagem.
     * @throws UserNotFoundException Se o usuário não for encontrado.
     * @throws NoMessagesException Se o usuário não tiver mensagens.
     */
    public String getFirstMessage(String sessionId) throws UserNotFoundException, NoMessagesException {
        User user = this.repository.getUserBySessionId(sessionId);
        return user.getFirstMessage();
    }
}
