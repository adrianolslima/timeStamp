/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timestamp;

import java.time.Instant;
import telas.Tela;

/**
 *
 * @author Adriano
 */
public class Operador {
    
    private SGBD sgbd;
    private Tabela tabela;
    private Escalonador escalonador;
    
    private Tela tela;
    
    private Transacao transacaoAtiva;
    
    public Operador(SGBD sgbd, Tabela tabela) {
        this.sgbd = sgbd;
        
        this.tabela = tabela;
        
        this.tela = new Tela(sgbd, tabela, this);
        tela.setVisible(true);
        
        this.escalonador = Escalonador.getInstance();
    }
    
    public void atualizar() {
        this.transacaoAtiva = this.escalonador.getTransacaoAtiva();
    }
    
    public void executar() {
                
        Operacao operacao;
        operacao = this.transacaoAtiva.getOperacao();
        
        long ts = transacaoAtiva.getTimeStamp();
        char dado = operacao.getDado();
        TipoOperacao tipo = operacao.getTipo();
        String proxima;
        
        if (tipo == TipoOperacao.R) {
            proxima = tipo + String.valueOf(transacaoAtiva.getId()) + "(" + dado + "); TS: " + ts;
        } else if (tipo == TipoOperacao.W) {
            proxima = tipo + String.valueOf(transacaoAtiva.getId()) + "(" + dado + ", " + operacao.getValor() + "); TS: " + ts;
        } else {
            proxima = tipo + String.valueOf(transacaoAtiva.getId());
        }
        
        tela.atualizarProxima(proxima);
        
        switch (tipo) {
            case S:
                sgbd.addOperacaoLog(transacaoAtiva, operacao);
//                log.addOperacao(transacaoAtiva, operacao);
                break;
            case R:
                if (ts <= tabela.getTSwrite(dado) || tabela.getTSwrite(dado) == 0) {
//                    escalonador.remover(transacaoAtiva);
//                    transacaoAtiva.reiniciar(this.gerarTimeStamp());
//                    escalonador.escalonar(transacaoAtiva);
//                    Operacao abort = new Operacao();
//                    abort.setTipo(TipoOperacao.A);
//                    log.addOperacao(transacaoAtiva, abort);
//                    this.atualizar();
                    tabela.read(dado);
                    if (ts > tabela.getTSread(dado)) {
                        tabela.setTSread(dado, ts);
                    }
                    sgbd.addOperacaoLog(transacaoAtiva, operacao);
//                    log.addOperacao(transacaoAtiva, operacao);
                } else {
//                    tabela.read(dado);
//                    if (ts > tabela.getTSread(dado)) {
//                        tabela.setTSread(dado, ts);
//                    }
//                    log.addOperacao(transacaoAtiva, operacao);
                    escalonador.remover(transacaoAtiva);
                    tabela.addTransacaoWait(dado, transacaoAtiva);
                    this.atualizar();
                }
                break;
            case W:
                int valor = operacao.getValor();
                if (ts <= tabela.getTSwrite(dado) || ts <= tabela.getTSread(dado) || tabela.getTSwrite(dado) == 0) {
//                    escalonador.remover(transacaoAtiva);
//                    transacaoAtiva.reiniciar(this.gerarTimeStamp());
//                    escalonador.escalonar(transacaoAtiva);
//                    Operacao abort = new Operacao();
//                    abort.setTipo(TipoOperacao.A);
//                    log.addOperacao(transacaoAtiva, abort);
//                    this.atualizar();
                    tabela.write(dado, valor);
                    tabela.setTSwrite(dado, ts);
                    sgbd.addOperacaoLog(transacaoAtiva, operacao);
//                    log.addOperacao(transacaoAtiva, operacao);
                    this.atualizar();
                } else {
//                    tabela.write(dado, valor);
//                    tabela.setTSwrite(dado, ts);
//                    log.addOperacao(transacaoAtiva, operacao);
//                    this.atualizar();
                    escalonador.remover(transacaoAtiva);
                    tabela.addTransacaoWait(dado, transacaoAtiva);
                    this.atualizar();
                }
                break;
            case C:
                escalonador.remover(transacaoAtiva);
                sgbd.addOperacaoLog(transacaoAtiva, operacao);
//                log.addOperacao(transacaoAtiva, operacao);
                Transacao transacaoWait = tabela.removeTransacaoWait(ts);
                if (transacaoWait != null) {
                    escalonador.escalonar(transacaoWait);
                }
                if (!escalonador.isVazio()) {
                    this.atualizar();
                } else {
                    System.out.println("fim!");
                    tela.inativar();
                }
                break;
        }
        
        tela.limparDados();
        tela.atualizarTela();
        
    }
    
    public long gerarTimeStamp() {
        return Instant.now().toEpochMilli() + 10;
    }
    
}
