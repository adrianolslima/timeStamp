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
public class SGBD {
    
    private Operador operador;
    private Escalonador escalonador;
    private Gerador gerador;

    public SGBD() {
        this.escalonador = new Escalonador();
        this.gerador = new Gerador();
        this.operador = new Operador(escalonador);
    }
    
    
    
}
