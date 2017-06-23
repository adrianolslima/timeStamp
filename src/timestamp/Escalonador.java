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
class Escalonador {
    
    private ArrayList<Transacao> transacoes;
    private int ponteiro;   //Indica qual transacao sera escalonada;

    public Escalonador() {
        this.transacoes = new ArrayList<>();
        this.ponteiro = 0;
    }

    public Transacao getTransacaoAtiva() {
        Transacao transacaoAtiva = this.transacoes.get(ponteiro);
        this.ponteiro = ponteiro++ % transacoes.size(); //Evitar que o ponteiro ultrapasse o tamanho da lista
        return transacaoAtiva;
    }

    public void escalonar(Transacao transacao) {
        this.transacoes.add(transacao);
    }
    
    public boolean isVazio() {
        return transacoes.size() == 0;
    }
    
}
