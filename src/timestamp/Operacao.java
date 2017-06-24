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
    private long idTransacao;
    private long timeStamp;

    public Operacao() {
    }

    public void setTipo(TipoOperacao tipo) {
        this.tipo = tipo;
    }
    
    public TipoOperacao getTipo() {
        return tipo;
    }

    public char getDado() {
        return dado;
    }

    public void setDado(char dado) {
        this.dado = dado;
    }

    public int getValor() {
        return valor;
    }
    
    public void setValor(int valor) {
        this.valor = valor;
    }

    public long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
