/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timestamp;

import java.util.ArrayList;

/**
 *
 * @author Adriano
 */
public class Transacao {
    
    private long id;
    
    private long timeStamp;
    
    private int ponteiro;   //Indica qual operacao da fila sera executada
    
    private ArrayList<Operacao> operacoes;   //Fila de operacoes a serem executadas

    public Transacao(long timeStamp) {
        
        //TimeStamp recebido na construcao da transacao
        this.timeStamp = timeStamp;
        
        this.ponteiro = 0;
        
        //Inicia a fila de operacoes com START 
        this.operacoes = new ArrayList<>();
        operacoes.add(new Operacao(TipoOperacao.S));
    }
    
    public long getTimeStamp() {
        return this.timeStamp;
    }
    
    public Operacao getOperacao() {
        Operacao proximaOperacao = this.operacoes.get(ponteiro);
        ponteiro++;
        return proximaOperacao;
    }
    
    public void addOperacao(Operacao operacao) {
        this.operacoes.add(operacao);
    }
    
}
