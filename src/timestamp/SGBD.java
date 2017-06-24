/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timestamp;

import static java.lang.Thread.sleep;
import telas.Tela;

/**
 *
 * @author Adriano
 */
public class SGBD {

    private Operador operador;
    private Escalonador escalonador;
    private Gerador gerador;

    private Tabela tabela;

    public SGBD() {
        this.tabela = new Tabela();

        this.escalonador = Escalonador.getInstance();
        this.gerador = new Gerador();
        this.operador = new Operador(tabela);

    }

    public void executar() throws InterruptedException {

        this.gerarTransacoesIniciais();

        operador.atualizar();

        operador.executar();
    }

    public void gerarTransacoesIniciais() {
        for (int i = 0; i < 3; i++) {
            Transacao transacao = this.gerador.gerarTransacao();
            System.out.println("TS" + transacao.getId() + ": " + transacao.getTimeStamp());
            this.escalonador.escalonar(transacao);
        }
    }

}
