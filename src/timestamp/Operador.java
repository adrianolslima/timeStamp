/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timestamp;

import java.time.Instant;
import java.util.ArrayList;
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

    public Operador(SGBD sgbd) {
        this.sgbd = sgbd;

        this.tabela = new Tabela();

        this.tela = new Tela(sgbd, tabela, this);
        tela.setVisible(true);

        this.escalonador = Escalonador.getInstance();
    }

    public void atualizar() {
        this.transacaoAtiva = this.escalonador.getTransacaoAtiva();
    }

    public void executar() {

        //Operacao a ser executada
        Operacao operacao = this.transacaoAtiva.getOperacao();

        long ts = transacaoAtiva.getTimeStamp();
        char dado = operacao.getDado();
        TipoOperacao tipo = operacao.getTipo();

//        Operacao proximaOperacao = this.transacaoAtiva.getProximaOperacao();
//        char proximoDado = proximaOperacao.getDado();
//        TipoOperacao proximoTipo = proximaOperacao.getTipo();
//        String proxima;
//
//        if (proximoTipo == TipoOperacao.R) {
//            proxima = proximoTipo + String.valueOf(transacaoAtiva.getId()) + "(" + proximoDado + "); TS: " + ts;
//        } else if (tipo == TipoOperacao.W) {
//            proxima = proximoTipo + String.valueOf(transacaoAtiva.getId()) + "(" + proximoDado + ", " + proximaOperacao.getValor() + "); TS: " + ts;
//        } else {
//            proxima = proximoTipo + String.valueOf(transacaoAtiva.getId());
//        }
//
//        tela.atualizarProxima(proxima);
        switch (tipo) {
            case S:
                sgbd.addOperacaoLog(transacaoAtiva, operacao);
                transacaoAtiva.terminarOperacao();
                break;
            case R:
                if (ts <= tabela.getTSwrite(dado) || tabela.getTSwrite(dado) == 0) {
                    tabela.read(dado);
                    if (ts > tabela.getTSread(dado)) {
                        tabela.setTSread(dado, ts);
                    }
                    transacaoAtiva.terminarOperacao();
                    sgbd.addOperacaoLog(transacaoAtiva, operacao);
                } else {
                    ArrayList<Transacao> escalonamento = escalonador.getTransacoes();
                    if (!escalonamento.isEmpty()) {
                        for (Transacao transacao : escalonamento) {
                            if (ts > transacao.getTimeStamp()) {
                                escalonador.remover(transacaoAtiva);
                                tabela.addTransacaoWait(dado, transacaoAtiva);
                                this.atualizar();
                                break;
                            } else {
                                tabela.read(dado);
                                if (ts > tabela.getTSread(dado)) {
                                    tabela.setTSread(dado, ts);
                                }
                                transacaoAtiva.terminarOperacao();
                                sgbd.addOperacaoLog(transacaoAtiva, operacao);
                            }
                        }
                    }
                }
                break;
            case W:
                int valor = operacao.getValor();
                if (ts <= tabela.getTSwrite(dado) || ts <= tabela.getTSread(dado) || tabela.getTSwrite(dado) == 0) {
                    tabela.write(dado, valor);
                    tabela.setTSwrite(dado, ts);
                    transacaoAtiva.terminarOperacao();
                    sgbd.addOperacaoLog(transacaoAtiva, operacao);
                    this.atualizar();
                } else {
                    ArrayList<Transacao> escalonamento = escalonador.getTransacoes();
                    if (!escalonamento.isEmpty()) {
                        for (Transacao transacao : escalonamento) {
                            if (ts > transacao.getTimeStamp()) {
                                escalonador.remover(transacaoAtiva);
                                tabela.addTransacaoWait(dado, transacaoAtiva);
                                this.atualizar();
                                break;
                            } else {
                                tabela.write(dado, valor);
                                tabela.setTSwrite(dado, ts);
                                transacaoAtiva.terminarOperacao();
                                sgbd.addOperacaoLog(transacaoAtiva, operacao);
                                this.atualizar();
                            }
                        }
                    }
                }
                break;
            case C:
                escalonador.remover(transacaoAtiva);
                sgbd.addOperacaoLog(transacaoAtiva, operacao);
                ArrayList<Transacao> transacoesWait = tabela.removeTransacaoWait(ts);
                if (!transacoesWait.isEmpty()) {
                    for (Transacao transacao : transacoesWait) {
                        escalonador.escalonar(transacao);
                    }
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

    void iniciar() {
        tela.apresentarTransacoes();
    }
}
