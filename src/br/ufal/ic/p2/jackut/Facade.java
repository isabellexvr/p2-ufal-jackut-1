package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;

/**
 * Provides a simplified interface to the Jackut system, exposing core functionality
 * for managing users, sessions, communities, and messages.
 * This class acts as a facade in the Facade Design Pattern.
 */
public class Facade {

    /**
     * The core system object that contains the business logic and state.
     */
    private final Jackut jackut = new Jackut();

    /**
     * Reseta o sistema, removendo todos os dados.
     */
    public void zerarSistema() {
        this.jackut.resetSystem();
    }

    /**
     * Cria um novo usu�rio no sistema.
     *
     * @param login    O login do usu�rio.
     * @param password A senha do usu�rio.
     * @param nome     O nome do usu�rio.
     * @throws InvalidPasswordException   Se a senha for inv�lida.
     * @throws InvalidLoginException      Se o login for inv�lido.
     * @throws UserAlreadyExistsException Se o usu�rio j� existir.
     */
    public void criarUsuario(String login, String password, String nome)
            throws InvalidPasswordException, InvalidLoginException, UserAlreadyExistsException {
        this.jackut.createUser(login, password, nome);
    }

    /**
     * Obt�m um atributo de um usu�rio.
     *
     * @param login    O login do usu�rio.
     * @param atributo O nome do atributo desejado.
     * @return O valor do atributo solicitado.
     * @throws EmptyAttributeException Se o atributo estiver vazio.
     * @throws UserNotFoundException   Se o usu�rio n�o for encontrado.
     */
    public String getAtributoUsuario(String login, String atributo)
            throws EmptyAttributeException, UserNotFoundException {
        return this.jackut.getUserAttribute(login, atributo);
    }

    /**
     * Abre uma sess�o para um usu�rio autenticado.
     *
     * @param login O login do usu�rio.
     * @param senha A senha do usu�rio.
     * @return O ID da sess�o criada.
     * @throws UserNotFoundException           Se o usu�rio n�o for encontrado.
     * @throws InvalidPasswordOrLoginException Se login ou senha estiverem incorretos.
     */
    public String abrirSessao(String login, String senha)
            throws UserNotFoundException, InvalidPasswordOrLoginException {
        return this.jackut.newSession(login, senha);
    }

    /**
     * Encerra o sistema.
     */
    public void encerrarSistema() {
        this.jackut.endSystem();
    }

    /**
     * Edita o perfil de um usu�rio.
     *
     * @param id       O ID do usu�rio.
     * @param atributo O atributo a ser modificado.
     * @param valor    O novo valor do atributo.
     * @throws UserNotFoundException   Se o usu�rio n�o for encontrado.
     * @throws EmptyAttributeException Se o atributo estiver vazio.
     */
    public void editarPerfil(String id, String atributo, String valor)
            throws UserNotFoundException, EmptyAttributeException {
        try {
            this.jackut.editProfile(id, atributo, valor);
        } catch (EmptyAttributeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adiciona um amigo � lista de amigos do usu�rio.
     *
     * @param id    O ID do usu�rio.
     * @param amigo O login do amigo a ser adicionado.
     * @throws UserNotFoundException     Se o usu�rio n�o for encontrado.
     * @throws WaitingToAcceptException  Se o convite j� foi enviado e aguarda aceita��o.
     * @throws AlreadyFriendException    Se os usu�rios j� s�o amigos.
     * @throws InvalidFunctionEnemyException Se o usu�rio for inimigo.
     */
    public void adicionarAmigo(String id, String amigo)
            throws Exception, UserNotFoundException, WaitingToAcceptException, AlreadyFriendException, InvalidFunctionEnemyException {
        this.jackut.addFriend(id, amigo);
    }

    /**
     * Obt�m a lista de amigos de um usu�rio.
     *
     * @param login O login do usu�rio.
     * @return Uma String representando os amigos do usu�rio.
     */
    public String getAmigos(String login)  {
        return this.jackut.getFriends(login);
    }

    /**
     * Verifica se dois usu�rios s�o amigos.
     *
     * @param login O login do usu�rio.
     * @param amigo O login do poss�vel amigo.
     * @return `true` se forem amigos, `false` caso contr�rio.
     * @throws UserNotFoundException Se o usu�rio n�o for encontrado.
     */
    public boolean ehAmigo(String login, String amigo) throws UserNotFoundException {
        return this.jackut.isFriend(login, amigo);
    }

    /**
     * Envia um recado para outro usu�rio.
     *
     * @param id           O ID do remetente.
     * @param destinatario O login do destinat�rio.
     * @param mensagem     A mensagem a ser enviada.
     * @throws CantMessageItselfException Se o usu�rio tentar enviar mensagem para si mesmo.
     * @throws UserNotFoundException      Se o destinat�rio n�o for encontrado.
     * @throws InvalidFunctionEnemyException Se o usu�rio for inimigo.
     */
    public void enviarRecado(String id, String destinatario, String mensagem)
            throws CantMessageItselfException, UserNotFoundException, InvalidFunctionEnemyException {
        this.jackut.sendMessage(id, destinatario, mensagem);
    }

    /**
     * L� o primeiro recado dispon�vel para um usu�rio.
     *
     * @param sessionId O ID da sess�o do usu�rio.
     * @return O conte�do do recado.
     * @throws UserNotFoundException Se o usu�rio n�o for encontrado.
     * @throws NoMessagesException   Se n�o houver mensagens dispon�veis.
     */
    public String lerRecado(String sessionId)
            throws UserNotFoundException, NoMessagesException {
        return this.jackut.getFirstMessage(sessionId);
    }

    /**
     * Creates a new community.
     * @param sessao The current user's session ID.
     * @param nome The name of the community to create.
     * @param descricao The description of the community.
     * @throws UserNotFoundException If the session user is not found.
     * @throws CommunityAlreadyExistsException If a community with this name already exists.
     */
    public void criarComunidade(String sessao, String nome, String descricao) throws UserNotFoundException, CommunityAlreadyExistsException {
        this.jackut.createCommunity(sessao, nome, descricao);
    }

    /**
     * Gets a community's description.
     * @param nome The name of the community.
     * @return The community description.
     * @throws CommunityDoesntExistException If the community doesn't exist.
     */
    public String getDescricaoComunidade(String nome) throws CommunityDoesntExistException {
        return this.jackut.getCommunityDescription(nome);
    }

    /**
     * Gets a community's owner.
     * @param nome The name of the community.
     * @return The login of the community owner.
     * @throws CommunityDoesntExistException If the community doesn't exist.
     */
    public String getDonoComunidade(String nome) throws CommunityDoesntExistException {
        return this.jackut.getCommunityOwner(nome);
    }

    /**
     * Gets all members of a community.
     * @param nome The name of the community.
     * @return A formatted string with all community members.
     * @throws CommunityDoesntExistException If the community doesn't exist.
     */
    public String getMembrosComunidade(String nome) throws CommunityDoesntExistException {
        return this.jackut.getCommunityMembers(nome);
    }

    /**
     * Gets all communities a user belongs to.
     * @param login The user's login.
     * @return A formatted string with all user's communities.
     * @throws UserNotFoundException If the user doesn't exist.
     */
    public String getComunidades(String login) throws UserNotFoundException {
        return this.jackut.getCommunitiesByLogin(login);
    }

    /**
     * Adds a user to a community.
     * @param sessao The current user's session ID.
     * @param nome The name of the community to join.
     * @throws UserNotFoundException If the session user is not found.
     * @throws CommunityDoesntExistException If the community doesn't exist.
     * @throws UserAlreadyCommunityMemberException If the user is already a member.
     */
    public void adicionarComunidade(String sessao, String nome) throws UserNotFoundException, CommunityDoesntExistException, UserAlreadyCommunityMemberException {
        this.jackut.addMemberToCommunity(sessao, nome);
    }

    /**
     * Reads a message (either community or private).
     * @param id The user's session ID requesting the message.
     * @return The message content.
     * @throws UserNotFoundException If the user is not found.
     * @throws NoCommunityMessagesException If there are no community messages to read.
     * @throws NoMessagesException If there are no private messages to read.
     */
    public String lerMensagem(String id) throws UserNotFoundException, NoCommunityMessagesException, NoMessagesException {
        return this.jackut.getMessage(id);
    }

    /**
     * Sends a message to a community.
     * @param id The sender's session ID.
     * @param comunidade The target community name.
     * @param mensagem The message content.
     * @throws UserNotFoundException If the sender is not found.
     * @throws CommunityDoesntExistException If the community doesn't exist.
     */
    public void enviarMensagem(String id, String comunidade, String mensagem) throws UserNotFoundException, CommunityDoesntExistException {
        this.jackut.sendCommunityMessage(id, comunidade, mensagem);
    }

    /**
     * Checks if a user is a fan of another user.
     * @param login The potential fan's login.
     * @param idolo The potential idol's login.
     * @return True if the user is a fan, false otherwise.
     * @throws UserNotFoundException If either user is not found.
     */
    public boolean ehFa(String login, String idolo) throws UserNotFoundException {
        return this.jackut.isFan(login, idolo);
    }

    /**
     * Makes a user follow another user (become a fan).
     * @param id The fan's session ID.
     * @param idolo The idol's login to follow.
     * @throws UserNotFoundException If either user is not found.
     * @throws SelfFanException If trying to follow oneself.
     * @throws InvalidFunctionEnemyException If trying to follow an enemy.
     */
    public void adicionarIdolo(String id, String idolo) throws UserNotFoundException, SelfFanException, InvalidFunctionEnemyException {
        this.jackut.follow(id, idolo);
    }

    /**
     * Gets all followers of a user.
     * @param login The user's login.
     * @return A formatted string with all followers.
     * @throws UserNotFoundException If the user is not found.
     */
    public String getFas(String login) throws UserNotFoundException {
        return this.jackut.getFollowers(login);
    }

    /**
     * Checks if a user has a crush on another user.
     * @param id The first user's session ID.
     * @param paquera The potential crush's login.
     * @return True if there is a crush, false otherwise.
     * @throws UserNotFoundException If either user is not found.
     */
    public boolean ehPaquera(String id, String paquera) throws UserNotFoundException {
        return this.jackut.isCrush(id, paquera);
    }

    /**
     * Adds a crush relationship between users.
     * @param id The user's session ID adding the crush.
     * @param paquera The crush's login to add.
     * @throws UserNotFoundException If either user is not found.
     * @throws AlreadyCrushException If the crush already exists.
     * @throws CrushItselfException If trying to add oneself as a crush.
     * @throws InvalidFunctionEnemyException If trying to add an enemy as a crush.
     */
    public void adicionarPaquera(String id, String paquera) throws UserNotFoundException, AlreadyCrushException,
            CrushItselfException, InvalidFunctionEnemyException {
        this.jackut.addCrush(id, paquera);
    }

    /**
     * Gets all crushes of a user.
     * @param id The user's login.
     * @return A formatted string with all crushes.
     * @throws UserNotFoundException If the user is not found.
     */
    public String getPaqueras(String id) throws UserNotFoundException {
        return this.jackut.getCrushes(id);
    }

    /**
     * Adds an enemy relationship between users.
     * @param id The user's session ID adding the enemy.
     * @param inimigo The enemy's login to add.
     * @throws UserNotFoundException If either user is not found.
     * @throws AlreadyEnemyException If the enemy already exists.
     * @throws SelfEnemyException If trying to add oneself as an enemy.
     */
    public void adicionarInimigo(String id, String inimigo) throws UserNotFoundException, AlreadyEnemyException, SelfEnemyException {
        this.jackut.addEnemy(id, inimigo);
    }

    /**
     * Removes a user from the system.
     * @param id The user's session ID to remove.
     * @throws UserNotFoundException If the user is not found.
     */
    public void removerUsuario(String id) throws UserNotFoundException {
        this.jackut.removeUser(id);
    }

}
