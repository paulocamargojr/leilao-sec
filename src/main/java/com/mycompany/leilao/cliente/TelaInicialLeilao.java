package com.mycompany.leilao.cliente;

import com.mycompany.leilao.compartilhado.Item;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

public class TelaInicialLeilao extends javax.swing.JFrame implements Runnable {
    Comunicacao c = new Comunicacao();
    
    public TelaInicialLeilao() {
        initComponents();
        c.start();
        AtualizarLista();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListItens = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jListItens);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 889, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jListItens;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void AtualizarLista() {
        ArrayList<Item> itens = c.SelecionarTodos();
       
        jListItens.setFont(new java.awt.Font("Arial", 0, 16));
        DefaultListModel demoList = new DefaultListModel();
        for (Item i : itens) {
            demoList.addElement(i.toString());
        }
        jListItens.setModel(demoList);
    }

    @Override
    public void run() {
        while(true){
            AtualizarLista();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(TelaInicialLeilao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
