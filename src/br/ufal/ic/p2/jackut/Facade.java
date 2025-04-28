package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;

import java.util.Set;

/**
 * Classe de fachada para o sistema Jackut.
 * Responsável por fornecer métodos de acesso ao sistema.
 */
public class Facade {

    private final Jackut jackut = new Jackut();

    /**
     * Reseta o sistema, removendo todos os dados.
     */
    public void zerarSistema() {
        this.jackut.resetSystem();
    }

    /**
     * Cria um novo usuário no sistema.
     *
     * @param login    O login do usuário.
     * @param password A senha do usuário.
     * @param nome     O nome do usuário.
     * @throws InvalidPasswordException   Se a senha for inválida.
     * @throws InvalidLoginException      Se o login for inválido.
     * @throws UserAlreadyExistsException Se o usuário já existir.
     */
    public void criarUsuario(String login, String password, String nome)
            throws InvalidPasswordException, InvalidLoginException, UserAlreadyExistsException {
        this.jackut.createUser(login, password, nome);
    }

    /**
     * Obtém um atributo de um usuário.
     *
     * @param login    O login do usuário.
     * @param atributo O nome do atributo desejado.
     * @return O valor do atributo solicitado.
     * @throws EmptyAttributeException Se o atributo estiver vazio.
     * @throws UserNotFoundException   Se o usuário não for encontrado.
     */
    public String getAtributoUsuario(String login, String atributo)
            throws EmptyAttributeException, UserNotFoundException {
        return this.jackut.getUserAttribute(login, atributo);
    }

    /**
     * Abre uma sessão para um usuário autenticado.
     *
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @return O ID da sessão criada.
     * @throws UserNotFoundException           Se o usuário não for encontrado.
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
     * Edita o perfil de um usuário.
     *
     * @param id       O ID do usuário.
     * @param atributo O atributo a ser modificado.
     * @param valor    O novo valor do atributo.
     * @throws UserNotFoundException   Se o usuário não for encontrado.
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
     * Adiciona um amigo à lista de amigos do usuário.
     *
     * @param id    O ID do usuário.
     * @param amigo O login do amigo a ser adicionado.
     * @throws UserNotFoundException     Se o usuário não for encontrado.
     * @throws WaitingToAcceptException  Se o convite já foi enviado e aguarda aceitação.
     * @throws AlreadyFriendException    Se os usuários já são amigos.
     * @throws Exception                 Outras exceções genéricas.
     */
    public void adicionarAmigo(String id, String amigo)
            throws Exception, UserNotFoundException, WaitingToAcceptException, AlreadyFriendException {
        this.jackut.addFriend(id, amigo);
    }

    /**
     * Obtém a lista de amigos de um usuário.
     *
     * @param login O login do usuário.
     * @return Uma String representando os amigos do usuário.
     * @throws UserNotFoundException Se o usuário não for encontrado.
     */
    public String getAmigos(String login) throws UserNotFoundException {
        return this.jackut.getFriends(login);
    }

    /**
     * Verifica se dois usuários são amigos.
     *
     * @param login O login do usuário.
     * @param amigo O login do possível amigo.
     * @return `true` se forem amigos, `false` caso contrário.
     * @throws UserNotFoundException Se o usuário não for encontrado.
     */
    public boolean ehAmigo(String login, String amigo) throws UserNotFoundException {
        return this.jackut.isFriend(login, amigo);
    }

    /**
     * Envia um recado para outro usuário.
     *
     * @param id           O ID do remetente.
     * @param destinatario O login do destinatário.
     * @param mensagem     A mensagem a ser enviada.
     * @throws CantMessageItselfException Se o usuário tentar enviar mensagem para si mesmo.
     * @throws UserNotFoundException      Se o destinatário não for encontrado.
     */
    public void enviarRecado(String id, String destinatario, String mensagem)
            throws CantMessageItselfException, UserNotFoundException {
        this.jackut.sendMessage(id, destinatario, mensagem);
    }

    /**
     * Lê o primeiro recado disponível para um usuário.
     *
     * @param sessionId O ID da sessão do usuário.
     * @return O conteúdo do recado.
     * @throws UserNotFoundException Se o usuário não for encontrado.
     * @throws NoMessagesException   Se não houver mensagens disponíveis.
     */
    public String lerRecado(String sessionId)
            throws UserNotFoundException, NoMessagesException {
        return this.jackut.getFirstMessage(sessionId);
    }

    public void criarComunidade(String sessao, String nome, String descricao) throws UserNotFoundException, CommunityAlreadyExistsException {
        this.jackut.createCommunity(sessao, nome, descricao);
    }

    public String getDescricaoComunidade(String nome) throws CommunityDoesntExistException {
        return this.jackut.getCommunityDescription(nome);
    }

    public String getDonoComunidade(String nome) throws CommunityDoesntExistException {
        return this.jackut.getCommunityOwner(nome);
    }

    public String getMembrosComunidade(String nome) throws CommunityDoesntExistException {
        return this.jackut.getMembrosComunidade(nome);
    }

    public String getComunidades(String login) throws UserNotFoundException {
        return this.jackut.getCommunitiesByLogin(login);
    }

    public void adicionarComunidade(String sessao, String nome) throws UserNotFoundException, CommunityDoesntExistException, UserAlreadyCommunityMemberException {
        this.jackut.addMemberToCommunity(sessao, nome);
    }
}
