/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timestamp;

import java.sql.Timestamp;
import java.time.Instant;

/**
 *
 * @author Adriano
 */
public class Gerador {
    
    public Gerador() {
        
    }
    
    public Transacao gerarTransacao(int quantidade) {
        long timeStamp = Instant.now().toEpochMilli();
        Transacao transacao = new Transacao(timeStamp);
        
        return transacao;
    }
    
}
