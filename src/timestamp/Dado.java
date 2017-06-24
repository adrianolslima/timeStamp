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
public class Dado {
    
    private char nome;
    
    private long TSread;
    private long TSwrite;
    private ArrayList<Transacao> filaWait;
    
    private int valor;

    public Dado(char nome) {
        this.nome = nome;
        this.TSread = 0;
        this.TSwrite = 0;
        this.filaWait = new ArrayList<>();
        this.valor = 0;
    }

    public char getNome() {
        return nome;
    }

    public long getTSread() {
        return TSread;
    }

    public void setTSread(long TSread) {
        this.TSread = TSread;
    }

    public long getTSwrite() {
        return TSwrite;
    }

    public void setTSwrite(long TSwrite) {
        this.TSwrite = TSwrite;
    }

    public ArrayList<Transacao> getFilaWait() {
        return filaWait;
    }

    public Transacao removeTransacaoWait() {
        Transacao transacao = filaWait.get(0);
        this.setTSwrite(transacao.getTimeStamp());
        return filaWait.remove(0);
    }
    
    public boolean isFilaWaitVazia() {
        return filaWait.isEmpty();
    }

    public void addTransacaoWait(Transacao transacao) {
        this.filaWait.add(transacao);
    }

    public int read() {
        return valor;
    }

    public void write(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
    
}
