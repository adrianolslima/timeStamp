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
    
    private Tabela tabela;
    private Escalonador escalonador;
    
    private Transacao transacaoAtiva;

    public Operador(Escalonador escalonador) {
        this.tabela = new Tabela();
        this.escalonador = escalonador;
    }

    public void atualizar() {
        this.transacaoAtiva = this.escalonador.getTransacaoAtiva();
    }
    
    public void executar() {
        Operacao operacao;
        operacao = this.transacaoAtiva.getOperacao();
        long ts = transacaoAtiva.getTimeStamp();        
        
        switch (operacao.getTipo()) {
            case S :
                break;
            case R :
                char dado = operacao.getDado();
                tabela.read(dado, ts);
                break;
            case W :
                char dado = operacao.getDado();
                int valor = operacao.getValor();
                tabela.write(dado, valor, ts);
                break;
            case C :
                break;
            
        }
    }
    
}
