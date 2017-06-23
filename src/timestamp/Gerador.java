/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timestamp;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Random;

/**
 *
 * @author Adriano
 */
public class Gerador {

    private long ponteiro;  //Define o id da transacao

    public Gerador() {
        this.ponteiro = 1;
    }

    public Transacao gerarTransacao() {

        //Gera a transacao com seu TimeStamp
        long timeStamp = (Instant.now().toEpochMilli() + ponteiro) % 1000;
        Transacao transacao = new Transacao(ponteiro, timeStamp);
        ponteiro++;

        //Popula a transacao com operacoes aleatórias
        Operacao operacao;
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int aleatorio = random.nextInt();
            operacao = new Operacao();
            if (aleatorio % 2 == 0) { //Sorteia o tipo da operacao
                operacao.setTipo(TipoOperacao.R);
            } else {
                operacao.setTipo(TipoOperacao.W);
                operacao.setValor(aleatorio % 100); //Sorteia o valor a ser escrito
            }
            switch (aleatorio % 5) { //Sorteia o dado a ser alterado
                case 0:
                    operacao.setDado('A');
                    break;
                case 1:
                    operacao.setDado('B');
                    break;
                case 2:
                    operacao.setDado('C');
                    break;
                case 3:
                    operacao.setDado('D');
                    break;
                default:
                    operacao.setDado('E');
            }
            transacao.addOperacao(operacao);
        }
        operacao = new Operacao();
        operacao.setTipo(TipoOperacao.C);
        transacao.addOperacao(operacao);

        return transacao;
    }

}
