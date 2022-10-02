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
    SecretKey chave = controleEntrada.SelecionarChave();
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

        sendItem.put("Nome", item.getNome());
        sendItem.put("Valor", item.getValor());
        sendItem.put("LanceMin", item.getLanceMin());
        sendItem.put("Leilao", item.getLeilaoAtivo());

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
