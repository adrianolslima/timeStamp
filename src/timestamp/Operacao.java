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
public class Operacao {
    
    private TipoOperacao tipo;
    private char dado;
    private int valor;

    public Operacao(TipoOperacao tipo, char dado, int valor) {
        this.tipo = tipo;
        this.dado = dado;
        this.valor = valor;
    }

    public TipoOperacao getTipo() {
        return tipo;
    }

    public char getDado() {
        return dado;
    }

    public int getValor() {
        return valor;
    }
}
