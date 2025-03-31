package br.ufal.ic.p2.jackut;

import easyaccept.EasyAccept;

public class Facade {

    public static void zerarSistema() {

    }

    public static void criarUsuario(String login, String password, String nome) {
        User user = new User(login, password, nome);

    }

    public static String abrirSessao(String login, String password) {
        User user = User.loadUser(login);

        if (user != null) {
            user.newSession(login, password);
            return user.getAuthToken();
        }
        return null;
    }


}
