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

    private boolean ativo;

    private Tabela tabela;
    private Escalonador escalonador;
    private Log log;
    
    private Tela tela;

    private Transacao transacaoAtiva;

    public Operador(Tabela tabela) {
        this.tabela = tabela;
        
        this.tela = new Tela(tabela, this);
        tela.setVisible(true);
        
        this.escalonador = Escalonador.getInstance();
        this.log = new Log();

        this.ativo = true;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void atualizar() {
        this.transacaoAtiva = this.escalonador.getTransacaoAtiva();
    }

    public void executar() {
        
        tela.limparDados();
        tela.apresentarDados();
        
        Operacao operacao;
        operacao = this.transacaoAtiva.getOperacao();
//        escalonador.getTransacaoAtiva();
        long ts = transacaoAtiva.getTimeStamp();
        char dado = operacao.getDado();
        TipoOperacao tipo = operacao.getTipo();
        String proxima;
        
        if (tipo == TipoOperacao.R) {
            proxima = tipo+String.valueOf(transacaoAtiva.getId())+"("+dado+"); TS: "+ts;
        } else if (tipo == TipoOperacao.W) {
            proxima = tipo+String.valueOf(transacaoAtiva.getId())+"("+dado+", "+operacao.getValor()+"); TS: "+ts;            
        } else {
            proxima = tipo+String.valueOf(transacaoAtiva.getId());
        }
        
        tela.atualizarProxima(proxima);

        switch (tipo) {
            case S:
                log.addOperacao(transacaoAtiva, operacao);
                break;
            case R:
                if (ts < tabela.getTSwrite(dado)) {
                    escalonador.remover(transacaoAtiva);
                    transacaoAtiva.reiniciar(this.gerarTimeStamp());
                    escalonador.escalonar(transacaoAtiva);
                    Operacao abort = new Operacao();
                    abort.setTipo(TipoOperacao.A);
                    log.addOperacao(transacaoAtiva, abort);
                    this.atualizar();
                } else {
                    tabela.read(dado);
                    if (ts > tabela.getTSread(dado)) {
                        tabela.setTSread(dado, ts);
                    }
                    log.addOperacao(transacaoAtiva, operacao);
                }
                break;
            case W:
                int valor = operacao.getValor();
                if (ts < tabela.getTSread(dado) || ts < tabela.getTSwrite(dado)) {
                    escalonador.remover(transacaoAtiva);
                    transacaoAtiva.reiniciar(this.gerarTimeStamp());
                    escalonador.escalonar(transacaoAtiva);
                    Operacao abort = new Operacao();
                    abort.setTipo(TipoOperacao.A);
                    log.addOperacao(transacaoAtiva, abort);
                    this.atualizar();
                } else {
                    tabela.write(dado, valor);
                    tabela.setTSwrite(dado, ts);
                    log.addOperacao(transacaoAtiva, operacao);
                    this.atualizar();                    
                }
                break;
            case C:
                escalonador.remover(transacaoAtiva);
                log.addOperacao(transacaoAtiva, operacao);
                if (escalonador.isVazio()) {
                    this.ativo = false;
                } else {
                    this.atualizar();
                }
                break;
        }

    }
    
    public long gerarTimeStamp() {
        return Instant.now().toEpochMilli() + 10;
    }

}
