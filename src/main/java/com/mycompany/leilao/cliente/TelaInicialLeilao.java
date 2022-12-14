package com.mycompany.leilao.cliente;

import com.mycompany.leilao.compartilhado.CriptografiaAssimetrica;
import com.mycompany.leilao.compartilhado.Item;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class TelaInicialLeilao extends javax.swing.JFrame implements Runnable {

    Comunicacao comunicacao;
    Item itemSelecionado;
    Usuario usuario;
    SecretKey secretKey;

    public TelaInicialLeilao() throws Exception {
        initComponents();
        EntrarNoGrupo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jListItens = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelNomeItem = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelPrecoItem = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelLanceMinItem = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelUltimoLanceItem = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabelValorUltimoLanceItem = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabelTempoRestanteItem = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Leil??o");

        jScrollPane1.setViewportView(jListItens);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel1.setText("Item com leil??o em andamento:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nome:");

        jLabelNomeItem.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelNomeItem.setText("[Nome do item]");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Pre??o:");

        jLabelPrecoItem.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelPrecoItem.setText("[Pre??o do item]");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Lance m??nimo:");

        jLabelLanceMinItem.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelLanceMinItem.setText("[Lance m??nimo do item]");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("??ltimo lance:");

        jLabelUltimoLanceItem.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelUltimoLanceItem.setText("[Nome do cliente que fez o ??ltimo lance]");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Valor do ??ltimo lance (Valor atual):");

        jLabelValorUltimoLanceItem.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelValorUltimoLanceItem.setText("[Valor do ??ltimo lance]");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Tempo restante:");

        jLabelTempoRestanteItem.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabelTempoRestanteItem.setText("[Tempo restante do item]");

        jButton1.setText("Dar lance");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 2, 24)); // NOI18N
        jLabel14.setText("Lote:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabelNomeItem)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabelPrecoItem)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabelLanceMinItem))
                                .addGap(101, 101, 101)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTempoRestanteItem)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabelValorUltimoLanceItem)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabelUltimoLanceItem)
                                    .addComponent(jLabel8))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 721, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNomeItem)
                    .addComponent(jLabelUltimoLanceItem))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPrecoItem)
                    .addComponent(jLabelValorUltimoLanceItem))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLanceMinItem)
                    .addComponent(jLabelTempoRestanteItem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        double valorLance = 0;
        
        try {
            valorLance = Double.parseDouble(JOptionPane.showInputDialog(null, "Informe o valor do lance:", "Dar lance", JOptionPane.QUESTION_MESSAGE));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Somente n??meros no campo lance!", "Erro!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(valorLance <= itemSelecionado.getLanceMin()){
            JOptionPane.showMessageDialog(null, "O lance precisa ser maior do que o lance m??nimo!", "Erro!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(valorLance <= itemSelecionado.getValor()){
            JOptionPane.showMessageDialog(null, "O lance precisa ser maior do que o  valor inicial!", "Erro!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(valorLance < itemSelecionado.getValorUltimoLance()+ itemSelecionado.getLanceMin()){
            JOptionPane.showMessageDialog(null, "O lance precisa ser maior do que o  valor atual!", "Erro!", JOptionPane.ERROR_MESSAGE);
            return;
        }     
        
        if(valorLance < itemSelecionado.getLanceMin() + itemSelecionado.getValor()){
            JOptionPane.showMessageDialog(null, "O lance precisa ser maior do que o  valor atual com o lance m??nimo!", "Erro!", JOptionPane.ERROR_MESSAGE);
            return;
        }
 
        try {
            comunicacao.EnviarLance(usuario, valorLance);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicialLeilao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TelaInicialLeilao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelLanceMinItem;
    private javax.swing.JLabel jLabelNomeItem;
    private javax.swing.JLabel jLabelPrecoItem;
    private javax.swing.JLabel jLabelTempoRestanteItem;
    private javax.swing.JLabel jLabelUltimoLanceItem;
    private javax.swing.JLabel jLabelValorUltimoLanceItem;
    private javax.swing.JList<String> jListItens;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void AtualizarLista() {
        ArrayList<Item> itens = comunicacao.SelecionarTodos();

        jListItens.setFont(new java.awt.Font("Arial", 0, 16));
        DefaultListModel demoList = new DefaultListModel();
        for (Item i : itens) {
            demoList.addElement(i.toString());
        }
        jListItens.setModel(demoList);
    }

    @Override
    public void run() {
        while (true) {
            if (comunicacao != null) {
                AtualizarLista();
                AtualizarDadosLeilao();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TelaInicialLeilao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void AtualizarDadosLeilao() {
        ArrayList<Item> itens = comunicacao.SelecionarTodos();
        for (Item item : itens) {
            if ("sim".equals(item.getLeilaoAtivo())) {
                itemSelecionado = item;
                break;
            }
        }
        if (itemSelecionado != null) {
            jLabelNomeItem.setText(itemSelecionado.getNome());
            jLabelPrecoItem.setText(String.valueOf(itemSelecionado.getValor()));
            jLabelLanceMinItem.setText(String.valueOf(itemSelecionado.getLanceMin()));
            if (itemSelecionado.getNomeUltimoLance() != null) {
                jLabelUltimoLanceItem.setText(itemSelecionado.getNomeUltimoLance());
                jLabelValorUltimoLanceItem.setText(String.valueOf(itemSelecionado.getValorUltimoLance()));
                if(itemSelecionado.getTempo() > 0)
                    jLabelTempoRestanteItem.setText(String.valueOf(itemSelecionado.getTempo()));
                else
                    jLabelTempoRestanteItem.setText("0");
            }
        }
    }

    private void EntrarNoGrupo() throws Exception {
        String userName = JOptionPane.showInputDialog(null, "Insira seu Nome: ");

        usuario = new Usuario(userName);

        try {
            DatagramSocket clientSock = new DatagramSocket();
            InetAddress srvIP = InetAddress.getByName("127.0.0.1");
            int srvPort = 50001;

            byte[] sendData = new byte[65507];
            byte[] rcvdData = new byte[65507];

            JSONObject SendMsg = new JSONObject();
            SendMsg.put("userName", usuario.getNome());
            byte[] bytes = usuario.getChavePublica().getEncoded();
            String encodedString = java.util.Base64.getEncoder().encodeToString(bytes);
            SendMsg.put("Chave",encodedString);

            sendData = SendMsg.toString().getBytes("UTF-8");

            DatagramPacket sendDatagramPacket = new DatagramPacket(sendData, sendData.length,srvIP, srvPort);
            clientSock.send(sendDatagramPacket);

            DatagramPacket rcvDatagramPacket = new DatagramPacket(rcvdData, rcvdData.length);
            clientSock.receive(rcvDatagramPacket);

            String rcvMsg = new String(rcvDatagramPacket.getData());

            JSONObject JsonRcvMsg = new JSONObject(rcvMsg);
            
            String port = JsonRcvMsg.getString("Port");
            byte[] bytePort = java.util.Base64.getDecoder().decode(port);
            String DecodedPort = CriptografiaAssimetrica.do_RSADecryption(bytePort, usuario.chaves.getPrivate());
            MulticastSocket multicastSocket = new MulticastSocket(Integer.parseInt(DecodedPort));
            
            String Encodedgroup = JsonRcvMsg.getString("Group");
            byte[] byteGroup = java.util.Base64.getDecoder().decode(Encodedgroup);
            String DecodedGroup = CriptografiaAssimetrica.do_RSADecryption(byteGroup, usuario.chaves.getPrivate());
            InetAddress group = InetAddress.getByName(DecodedGroup);
            
            String encodedIV = JsonRcvMsg.getString("IV");
            byte[] decodedIV = java.util.Base64.getDecoder().decode(encodedIV);
            
            String encodedKey = JsonRcvMsg.getString("Chave"); 
            byte[] byteEncodedKey = java.util.Base64.getDecoder().decode(encodedKey);
            String decodedKey = CriptografiaAssimetrica.do_RSADecryption(byteEncodedKey, usuario.chaves.getPrivate());
            byte [] byteDecodedKey = java.util.Base64.getDecoder().decode(decodedKey);
            secretKey = new SecretKeySpec(byteDecodedKey, 0, byteDecodedKey.length, "AES");

            comunicacao = new Comunicacao(multicastSocket, group, secretKey, decodedIV);
            comunicacao.start();

        } catch (Exception e) {

        }
    }
}
