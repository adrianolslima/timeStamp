/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timestamp;

import java.util.ArrayList;
import telas.Tela;

/**
 *
 * @author Adriano
 */
public class SGBD {

    private Operador operador;
    private Escalonador escalonador;
    private Gerador gerador;
    private Log log;
    
    private Tela tela;

    public SGBD() {

        this.escalonador = Escalonador.getInstance();
        this.gerador = new Gerador();
        this.operador = new Operador(this);
        this.log = new Log();

    }

    public void executar() {
        this.gerarTransacoesIniciais();
        operador.iniciar();
        operador.atualizar();
        operador.executar();
        
    }

    public void gerarTransacoesIniciais() {
        for (int i = 0; i < 10; i++) {
            Transacao transacao = this.gerador.gerarTransacao();
            System.out.println("TS" + transacao.getId() + ": " + transacao.getTimeStamp());
            this.escalonador.escalonar(transacao);
        }
    }

    public ArrayList<Operacao> getLog() {
        return log.getLog();
    }

    public void addOperacaoLog(Transacao transacaoAtiva, Operacao operacao) {
        log.addOperacao(transacaoAtiva, operacao);
    }

    public ArrayList<Transacao> getEscalonamento() {
        return escalonador.getTransacoes();
    }

}
