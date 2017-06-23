/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timestamp;

/**
 *
 * @author Adriano
 */
public class Operador {

    private boolean ativo;

    private Tabela tabela;
    private Escalonador escalonador;
    private Log log;

    private Transacao transacaoAtiva;

    public Operador() {
        this.tabela = new Tabela();
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
        Operacao operacao;
        operacao = this.transacaoAtiva.getOperacao();
//        escalonador.getTransacaoAtiva();
        long ts = transacaoAtiva.getTimeStamp();
        char dado;

        switch (operacao.getTipo()) {
            case S:
                log.addOperacao(transacaoAtiva, operacao);
                break;
            case R:
                dado = operacao.getDado();
                tabela.read(dado, ts);
                log.addOperacao(transacaoAtiva, operacao);
                break;
            case W:
                dado = operacao.getDado();
                int valor = operacao.getValor();
                tabela.write(dado, valor, ts);
                log.addOperacao(transacaoAtiva, operacao);
                this.atualizar();
                break;
            case C:
                escalonador.commit(transacaoAtiva);
                log.addOperacao(transacaoAtiva, operacao);
                if (escalonador.isVazio()) {
                    this.ativo = false;
                } else {
                    this.atualizar();
                }
                break;
        }

    }

}
