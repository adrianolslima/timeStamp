/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timestamp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adriano
 */
public class TransacaoTest {

    private Gerador gerador;
    private Transacao transacao;
    
    public TransacaoTest() {
        this.gerador = new Gerador();
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {        
//        for (int i = 0; i < 3; i++) {
            this.transacao = this.gerador.gerarTransacao();
//            System.out.println("TS:" + transacao.getTimeStamp());
//        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Transacao.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Transacao instance = new Transacao(1L, 0);
        long expResult = 1L;
        long result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTimeStamp method, of class Transacao.
     */
    @Test
    public void testGetTimeStamp() {
        System.out.println("getTimeStamp");
        Transacao instance = new Transacao(0, 0);
        long expResult = 0L;
        long result = instance.getTimeStamp();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOperacao method, of class Transacao.
     */
    @Test
    public void testGetOperacao() {
        System.out.println("getOperacao");
//        Transacao instance = null;
        Operacao expResult = null;
        Operacao result = transacao.getOperacao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of addOperacao method, of class Transacao.
     */
    @Test
    public void testAddOperacao() {
        System.out.println("addOperacao");
        Operacao operacao = null;
        Transacao instance = null;
        instance.addOperacao(operacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testGetPonteiro() {
        System.out.println("getPonteiro");
        for (int i = 0; i < transacao.getTamanho(); i++) {
//            int expResult = i;
            int result = transacao.getPonteiro();
            assertEquals(i, result);
            System.out.println("Esperado: " + String.valueOf(i) + "; Ponteiro: "
            + String.valueOf(result));
            transacao.getOperacao();
        }
    }
    
}
