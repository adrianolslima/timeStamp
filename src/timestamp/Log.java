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
        this.operacoes.add(operacao);        
                
        String tid = String.valueOf(transacao.getId());
        TipoOperacao tipo = operacao.getTipo();
        
        String saida = tipo + tid;
        
        char dado = operacao.getDado();
        int valor = operacao.getValor();
        
        switch(tipo) {
            case S:
                System.out.println(saida);
                break;
            case R:
                System.out.println(saida + "(" + dado + ", " + valor + "); ");
                break;
            case W:
                System.out.println(saida + "(" + dado + ", " + valor + "); ");
                break;
            default:
                System.out.println(saida);
        }
    }

}
