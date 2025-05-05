package br.ufal.ic.p2.jackut.Exceptions;
/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar como inimigo a si mesmo.
 */
public class SelfEnemyException extends Exception {
    /**
     * Cria uma nova inst�ncia de SelfEnemyException com a mensagem padr�o.
     */
    public SelfEnemyException(){
        super("Usu�rio n�o pode ser inimigo de si mesmo.");
    }
}
