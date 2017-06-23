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
public class Dado {
    
    private char nome;
    
    private long TSread;
    private long TSwrite;
    
    private int valor;

    public Dado(char nome) {
        this.nome = nome;
        this.TSread = 0;
        this.TSwrite = 0;
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
