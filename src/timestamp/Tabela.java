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
public class Tabela {

    private Dado dadoA;
    private Dado dadoB;
    private Dado dadoC;
    private Dado dadoD;
    private Dado dadoE;

    public Tabela() {
        this.dadoA = new Dado('A');
        this.dadoB = new Dado('B');
        this.dadoC = new Dado('C');
        this.dadoD = new Dado('D');
        this.dadoE = new Dado('E');
    }

    public Dado defineDado(char nomeDado) {
        switch (nomeDado) {
            case 'A':
                return dadoA;
            case 'B':
                return dadoB;
            case 'C':
                return dadoC;
            case 'D':
                return dadoD;
            default:
                return dadoE;
        }
    }

    public int read(char nomeDado) {
        Dado dado = this.defineDado(nomeDado);
        return dado.read();
    }

    public void write(char nomeDado, int valor) {
        Dado dado = this.defineDado(nomeDado);
        dado.write(valor);
    }

    public long getTSread(char nomeDado) {
        Dado dado = this.defineDado(nomeDado);
        return dado.getTSread();
    }

    public long getTSwrite(char nomeDado) {
        Dado dado = this.defineDado(nomeDado);
        return dado.getTSwrite();
    }

    public void setTSread(char nomeDado, long TSread) {
        Dado dado = this.defineDado(nomeDado);
        dado.setTSread(TSread);
    }

    public void setTSwrite(char nomeDado, long TSwrite) {
        Dado dado = this.defineDado(nomeDado);
        dado.setTSwrite(TSwrite);
    }

    public Dado[] getDados() {
        Dado dados[] = {dadoA, dadoB, dadoC, dadoD, dadoE};
        return dados;
    }

    public ArrayList<Transacao> removeTransacaoWait(long timeStamp) {
        ArrayList<Transacao> transacoes = new ArrayList<>();
        if (dadoA.getTSwrite() == timeStamp && !dadoA.isFilaWaitVazia()) {
            transacoes.add(dadoA.removeTransacaoWait());
        }
        if (dadoB.getTSwrite() == timeStamp && !dadoB.isFilaWaitVazia()) {
            transacoes.add(dadoB.removeTransacaoWait());
        }
        if (dadoC.getTSwrite() == timeStamp && !dadoC.isFilaWaitVazia()) {
            transacoes.add(dadoC.removeTransacaoWait());
        }
        if (dadoD.getTSwrite() == timeStamp && !dadoD.isFilaWaitVazia()) {
            transacoes.add(dadoD.removeTransacaoWait());
        }
        if (dadoE.getTSwrite() == timeStamp && !dadoE.isFilaWaitVazia()) {
            transacoes.add(dadoE.removeTransacaoWait());
        }
        return transacoes;
    }

    public void addTransacaoWait(char nomeDado, Transacao transacao) {
        Dado dado = this.defineDado(nomeDado);
        dado.addTransacaoWait(transacao);
    }

}
