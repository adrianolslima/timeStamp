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
public class Log {

    private ArrayList<Operacao> operacoes;

    public Log() {
        this.operacoes = new ArrayList<>();
    }

    public void addOperacao(Transacao transacao, Operacao operacao) {
                
        long ts = transacao.getTimeStamp();
        
        operacao.setIdTransacao(transacao.getId());
        operacao.setTimeStamp(ts);
                
        this.operacoes.add(operacao);        
    }

    public ArrayList<Operacao> getLog() {
        return operacoes;
    }

}
