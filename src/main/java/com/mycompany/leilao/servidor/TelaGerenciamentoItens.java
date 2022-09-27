package com.mycompany.leilao.servidor;

import com.mycompany.leilao.compartilhado.ControladorItem;
import com.mycompany.leilao.compartilhado.Item;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class TelaGerenciamentoItens extends javax.swing.JFrame implements Runnable{
    ControladorItem controlador = new ControladorItem();
    Comunicacao c = new Comunicacao(controlador);
    Item itemSelecionado;
    public TelaGerenciamentoItens() {
        this.setLocationRelativeTo(null);
        initComponents();
        jPanelCriar.setVisible(false);
        c.start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelLista = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListItens = new javax.swing.JList<>();
        jPanelCriar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldValor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldLanceMin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonNovoItem = new javax.swing.JButton();
        jButtonNovoItem1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Visualização e cadastro de itens");

        jPanel1.setLayout(new java.awt.CardLayout());

        jScrollPane1.setViewportView(jListItens);

        javax.swing.GroupLayout jPanelListaLayout = new javax.swing.GroupLayout(jPanelLista);
        jPanelLista.setLayout(jPanelListaLayout);
        jPanelListaLayout.setHorizontalGroup(
            jPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE)
        );
        jPanelListaLayout.setVerticalGroup(
            jPanelListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelListaLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanelLista, "card2");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel2.setText("Criação de novos itens para leilão");

        jLabel3.setText("Nome do item:");

        jLabel4.setText("Preço do item:");

        jLabel5.setText("Lance mínimo para o item:");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonVoltar.setText("Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCriarLayout = new javax.swing.GroupLayout(jPanelCriar);
        jPanelCriar.setLayout(jPanelCriarLayout);
        jPanelCriarLayout.setHorizontalGroup(
            jPanelCriarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCriarLayout.createSequentialGroup()
                .addContainerGap(214, Short.MAX_VALUE)
                .addGroup(jPanelCriarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCriarLayout.createSequentialGroup()
                        .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCriarLayout.createSequentialGroup()
                        .addGroup(jPanelCriarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addGroup(jPanelCriarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextFieldLanceMin, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldValor, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(195, 195, 195))))
        );
        jPanelCriarLayout.setVerticalGroup(
            jPanelCriarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCriarLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldLanceMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addGroup(jPanelCriarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanelCriar, "card3");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel1.setText("Visualização e cadastro de itens");

        jButtonNovoItem.setText("Novo item");
        jButtonNovoItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoItemActionPerformed(evt);
            }
        });

        jButtonNovoItem1.setText("Iniciar leilão de item");
        jButtonNovoItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoItem1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNovoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNovoItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButtonNovoItem1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonNovoItem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNovoItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoItemActionPerformed
        jPanelLista.setVisible(false);
        jButtonNovoItem.setVisible(false);
        jPanelCriar.setVisible(true);
    }//GEN-LAST:event_jButtonNovoItemActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        String nome = jTextFieldNome.getText();
        double preco = Double.parseDouble(jTextFieldValor.getText());
        double valorMin = Double.parseDouble(jTextFieldLanceMin.getText());
        
        Item item = new Item(nome, preco, valorMin);
        item.setLeilaoAtivo("");
        
        controlador.AdicionarItem(item);
        
        AtualizarLista();
        
        try {
            c.Enviar(item);
        } catch (IOException ex) {
            Logger.getLogger(TelaGerenciamentoItens.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        jTextFieldNome.setText("");
        jTextFieldValor.setText("");
        jTextFieldLanceMin.setText("");
        
        jPanelLista.setVisible(true);
        jButtonNovoItem.setVisible(true);
        jPanelCriar.setVisible(false);
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        jPanelLista.setVisible(true);
        jButtonNovoItem.setVisible(true);
        jPanelCriar.setVisible(false);
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jButtonNovoItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoItem1ActionPerformed
        ArrayList<Item> itens = controlador.SelecionarTodos();
        
        if (jListItens.size() == null) {
            JOptionPane.showMessageDialog(null, "Insira um item primeiro!", "Erro!", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            String itemLista = jListItens.getSelectedValue();

            if (itemLista == null) {
                JOptionPane.showMessageDialog(null, "Selecione um item primeiro!", "Erro!", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                for (Item item : itens) {
                    if (itemLista.contains(item.getId())) {
                        itemSelecionado = item;
                        break;
                    }
                }
            }
        }
        itemSelecionado.setLeilaoAtivo("sim");
        try {
            c.EnviarAtualizacoes(itemSelecionado);
        } catch (IOException ex) {
            Logger.getLogger(TelaGerenciamentoItens.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButtonNovoItem1ActionPerformed

     private void AtualizarLista() {
        ArrayList<Item> itens = new ArrayList<>();
        itens = controlador.SelecionarTodos();
        jListItens.setFont(new java.awt.Font("Arial", 0, 16));
        DefaultListModel demoList = new DefaultListModel();
        for (Item i : itens) {
            demoList.addElement(i.toString());
        }
        jListItens.setModel(demoList);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonNovoItem;
    private javax.swing.JButton jButtonNovoItem1;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList<String> jListItens;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCriar;
    private javax.swing.JPanel jPanelLista;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldLanceMin;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1500);
                c.EnviarAtualizacoes(itemSelecionado);

            } catch (IOException ex) {
                Logger.getLogger(TelaGerenciamentoItens.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(TelaGerenciamentoItens.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
