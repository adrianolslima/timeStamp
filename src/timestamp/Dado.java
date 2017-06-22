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
    
    private long TSread;
    private long TSwrite;
    
    private int valor;

    public Dado() {
        this.TSread = 0;
        this.TSwrite = 0;
        this.valor = 0;
    }

    public int read(long TSread) {
        this.TSread = TSread;
        return valor;
    }

    public void write(int valor, long TSwrite) {
        this.TSwrite = TSwrite;
        this.valor = valor;
    }
    
}
