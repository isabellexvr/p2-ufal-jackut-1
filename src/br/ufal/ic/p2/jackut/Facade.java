package br.ufal.ic.p2.jackut;

import br.ufal.ic.p2.jackut.Exceptions.*;

import java.util.Set;

/**
 * Classe de fachada para o sistema Jackut.
 * Respons�vel por fornecer m�todos de acesso ao sistema.
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
     * @throws Exception                 Outras exce��es gen�ricas.
     */
    public void adicionarAmigo(String id, String amigo)
            throws Exception, UserNotFoundException, WaitingToAcceptException, AlreadyFriendException {
        this.jackut.addFriend(id, amigo);
    }

    /**
     * Obt�m a lista de amigos de um usu�rio.
     *
     * @param login O login do usu�rio.
     * @return Uma String representando os amigos do usu�rio.
     * @throws UserNotFoundException Se o usu�rio n�o for encontrado.
     */
    public String getAmigos(String login) throws UserNotFoundException {
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
     */
    public void enviarRecado(String id, String destinatario, String mensagem)
            throws CantMessageItselfException, UserNotFoundException {
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
