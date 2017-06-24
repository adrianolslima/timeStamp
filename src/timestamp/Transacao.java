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

    public Transacao(long id, long timeStamp) {
        
        this.id = id;
        
        //TimeStamp recebido na construcao da transacao
        this.timeStamp = timeStamp;
        
        this.ponteiro = 0;
        
        //Inicia a fila de operacoes com START 
        this.operacoes = new ArrayList<>();
        Operacao operacao = new Operacao();
        operacao.setTipo(TipoOperacao.S);
        operacoes.add(operacao);
    }

    public long getId() {
        return id;
    }
    
    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void reiniciar(long timeStamp) {
        this.timeStamp = timeStamp;
        this.ponteiro = 0;
    }
    
    public void addOperacao(Operacao operacao) {
        this.operacoes.add(operacao);
    }

    public Operacao getOperacao() {
        Operacao proximaOperacao = this.operacoes.get(ponteiro);
        if (ponteiro <= operacoes.size()) {
            ponteiro++;
        }
        return proximaOperacao;
    }
    
    public int getPonteiro() {
        return ponteiro;
    }
    
    public int getTamanho() {
        return operacoes.size();
    }
    
}
