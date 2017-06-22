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
public class Tabela {

    private Dado dadoA;
    private Dado dadoB;
    private Dado dadoC;
    private Dado dadoD;
    private Dado dadoE;

    public Tabela() {
        this.dadoA = new Dado();
        this.dadoB = new Dado();
        this.dadoC = new Dado();
        this.dadoD = new Dado();
        this.dadoE = new Dado();
    }

    public int read(char nomeDado, long TS) {
        Dado dado;
        switch (nomeDado) {
            case 'A':
                dado = dadoA;
                break;
            case 'B':
                dado = dadoB;
                break;
            case 'C':
                dado = dadoC;
                break;
            case 'D':
                dado = dadoD;
                break;
            default:
                dado = dadoE;
        }
        return dado.read(TS);
    }

    public void write(char nomeDado, int valor, long TS) {
        Dado dado;
        switch (nomeDado) {
            case 'A':
                dado = dadoA;
                break;
            case 'B':
                dado = dadoB;
                break;
            case 'C':
                dado = dadoC;
                break;
            case 'D':
                dado = dadoD;
                break;
            default:
                dado = dadoE;
        }
        dado.write(valor, TS);
    }

}
