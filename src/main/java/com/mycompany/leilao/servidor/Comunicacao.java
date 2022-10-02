package com.mycompany.leilao.servidor;

import com.mycompany.leilao.compartilhado.CriptografiaSimetrica;
import com.mycompany.leilao.compartilhado.ControladorItem;
import com.mycompany.leilao.compartilhado.Item;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class Comunicacao extends Thread{
    ControladorItem controladorItem;
    ArrayList<Item> itens = new ArrayList<>();
    ControleEntrada controleEntrada  = new ControleEntrada();
    SecretKey chave;
    byte[] IV;
    InetAddress inetAddressIP;
    int inetAddressPort;
    MulticastSocket multicastSocket;
    InetAddress group;
    String nomeUltimoLance = "";
    double valorUltimoLance = 0;
    
    public Comunicacao(ControladorItem controladorItem) {
        this.controladorItem = controladorItem;
        controleEntrada.start();
    }
    
    @Override
    public void run() {
        try {
            multicastSocket = new MulticastSocket(50002);
            group = InetAddress.getByName("230.0.0.0");
            multicastSocket.joinGroup(group);
            byte[] rcvData = new byte[65507];
            while(true){
                DatagramPacket rcvDatagramPacket = new DatagramPacket(rcvData, rcvData.length);
                multicastSocket.receive(rcvDatagramPacket);
                inetAddressPort = rcvDatagramPacket.getPort();
                rcvData = rcvDatagramPacket.getData();
                
                String rcvMsg = new String(rcvData, "UTF-8");
                JSONObject jsonRcvMsg = new JSONObject(rcvMsg);
                
                if(jsonRcvMsg.has("Usuario")){
                    nomeUltimoLance = jsonRcvMsg.getString("Usuario");
                    valorUltimoLance = jsonRcvMsg.getDouble("ValorLance");
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    public void Enviar(Item item) throws IOException, Exception {
        multicastSocket = new MulticastSocket(50002);
        group = InetAddress.getByName("230.0.0.0");
        multicastSocket.joinGroup(group);
        byte[] sendData = new byte[65507];
        JSONObject sendItem = new JSONObject();
        chave = controleEntrada.SelecionarChave();
        IV = controleEntrada.SelecionarIV();
        
        byte[] nome = CriptografiaSimetrica.do_AESEncryption(item.getNome(), chave, IV);
        String encodedNome = java.util.Base64.getEncoder().encodeToString(nome);
        sendItem.put("Nome", encodedNome);
        
        byte[] valor = CriptografiaSimetrica.do_AESEncryption(String.valueOf(item.getValor()), chave, IV);
        String encodedValor = java.util.Base64.getEncoder().encodeToString(valor);
        sendItem.put("Valor", encodedValor);
        
        byte[] lanceMin = CriptografiaSimetrica.do_AESEncryption(String.valueOf(item.getLanceMin()), chave, IV);
        String encodedLanceMin = java.util.Base64.getEncoder().encodeToString(lanceMin);
        sendItem.put("LanceMin", encodedLanceMin);
        
        byte[] leilao = CriptografiaSimetrica.do_AESEncryption(item.getLeilaoAtivo(), chave, IV);
        String encodedLeilao = java.util.Base64.getEncoder().encodeToString(leilao);
        sendItem.put("Leilao", encodedLeilao);

        sendData = sendItem.toString().getBytes();
        DatagramPacket sendDatagramPacket = new DatagramPacket(sendData, sendData.length, group, 50002);
        multicastSocket.send(sendDatagramPacket);
    }
    
    public void EnviarAtualizacoes(Item item) throws IOException {
        if (item != null) {
            multicastSocket = new MulticastSocket(50002);
            group = InetAddress.getByName("230.0.0.0");
            multicastSocket.joinGroup(group);
            byte[] sendData = new byte[65507];
            JSONObject sendItem = new JSONObject();
            
            sendItem.put("AtualizacaoItem", true);
            sendItem.put("Nome", item.getNome());
            sendItem.put("Valor", item.getValor());
            sendItem.put("LanceMin", item.getLanceMin());
            sendItem.put("UltimoLance", nomeUltimoLance);
            sendItem.put("ValorUltimoLance", valorUltimoLance);
            sendItem.put("Tempo", "00:23:21s");
            sendItem.put("Leilao", item.getLeilaoAtivo());

            sendData = sendItem.toString().getBytes();
            DatagramPacket sendDatagramPacket = new DatagramPacket(sendData, sendData.length, group, 50002);
            multicastSocket.send(sendDatagramPacket);
        }
    }   
}
