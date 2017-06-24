/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import timestamp.Dado;
import timestamp.Operacao;
import timestamp.Operador;
import timestamp.SGBD;
import timestamp.Tabela;
import timestamp.TipoOperacao;

/**
 *
 * @author Adriano
 */
public class Tela extends javax.swing.JFrame {

    private SGBD sgbd;
    private Operador operador;

    private Tabela tabela;
    private Dado[] dados;

    /**
     * Creates new form Tela
     */
    public Tela(SGBD sgbd, Tabela tabela, Operador operador) {
        this.sgbd = sgbd;
        this.operador = operador;
        this.tabela = tabela;
        this.dados = tabela.getDados();

        initComponents();
    }

    public void atualizarTela() {
        
        this.apresentarDados();
        this.popularLog();
    }

    public void apresentarDados() {
        DefaultTableModel dtmCardapio = (DefaultTableModel) tbDados.getModel();

        Object coluna[] = new Object[4];

        for (int i = 0; i < dados.length; i++) {
            coluna[0] = dados[i].getNome();
            coluna[1] = dados[i].getValor();
            coluna[2] = dados[i].getTSread();
            coluna[3] = dados[i].getTSwrite();
            dtmCardapio.addRow(coluna);
        }
    }
     
    public void popularLog() {
        ArrayList<Operacao> operacoes = sgbd.getLog();
        String log = new String();
        TipoOperacao tipo;
        long idTransacao;
        char dado;
        int valor;
        long ts;
        int i = 1;

        for (Operacao operacao : operacoes) {
            tipo = operacao.getTipo();
            idTransacao = operacao.getIdTransacao();
            dado = operacao.getDado();
            valor = operacao.getValor();
            ts = operacao.getTimeStamp();

            log = log + i + ". ";
            log = log + tipo + idTransacao;
            
            if (tipo == TipoOperacao.R) {
                log = log + "(" + dado + ", " + valor + "); TS: " + ts + ";" + System.getProperty("line.separator");
            } else if (tipo == TipoOperacao.W) {
                log = log + "(" + dado + ", " + valor + "); TS: " + ts + ";" + System.getProperty("line.separator");
            } else {
                log = log + ";" + System.getProperty("line.separator");
            }
            i++;
        }
        taLog.setText(log);

    }

    public void limparDados() {
        DefaultTableModel dtmPedido = (DefaultTableModel) tbDados.getModel();
        for (int i = tbDados.getRowCount() - 1; i >= 0; i--) {
            dtmPedido.removeRow(i);
        }
    }

    public void atualizarProxima(String proxima) {
        tfProxima.setText(proxima);
//        while(!continuar){}
    }

    public void inativar() {
        btProxima.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbDados = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        taLog = new javax.swing.JTextArea();
        btProxima = new javax.swing.JButton();
        tfProxima = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Dado", "Valor", "TS-Read", "TS-Write"
            }
        ));
        jScrollPane1.setViewportView(tbDados);

        taLog.setEditable(false);
        taLog.setColumns(20);
        taLog.setRows(5);
        jScrollPane2.setViewportView(taLog);

        btProxima.setText("Próxima");
        btProxima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximaActionPerformed(evt);
            }
        });

        tfProxima.setEditable(false);
        tfProxima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfProximaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(btProxima)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfProxima)))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btProxima)
                    .addComponent(tfProxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(208, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfProximaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfProximaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfProximaActionPerformed

    private void btProximaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProximaActionPerformed
        operador.executar();
    }//GEN-LAST:event_btProximaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Tela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btProxima;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea taLog;
    private javax.swing.JTable tbDados;
    private javax.swing.JTextField tfProxima;
    // End of variables declaration//GEN-END:variables

}
