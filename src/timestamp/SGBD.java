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
        this.escalonador = Escalonador.getInstance();
        this.gerador = new Gerador();
        this.operador = new Operador();        
    }

    public void run() {
        
        this.gerarTransacoesIniciais();
        
        operador.atualizar();
        
        while(operador.isAtivo()) {
            operador.executar();
        }
        
        System.out.println("fim!");
    }

    public void gerarTransacoesIniciais() {
        for (int i = 0; i < 3; i++) {
            Transacao transacao = this.gerador.gerarTransacao();
            System.out.println("TS:" + transacao.getTimeStamp());
            this.escalonador.escalonar(transacao);
        }
    }
    
    
    
}
