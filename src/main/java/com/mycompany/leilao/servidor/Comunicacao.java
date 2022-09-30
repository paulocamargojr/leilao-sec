package com.mycompany.leilao.servidor;

import com.mycompany.leilao.compartilhado.ControladorItem;
import com.mycompany.leilao.compartilhado.Item;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class Comunicacao extends Thread{
    ControladorItem c;
    ArrayList<Item> itens = new ArrayList<>();
    InetAddress inetAddressIP;
    int inetAddressPort;
    MulticastSocket multicastSocket;
    InetAddress group;
    
    public Comunicacao(ControladorItem c) {
        this.c = c;
    }
    
    @Override
    public void run() {
        try {
            multicastSocket = new MulticastSocket(50000);
            group = InetAddress.getByName("230.0.0.0");
            multicastSocket.joinGroup(group);
            JOptionPane.showMessageDialog(null, "Iniciando servidor...", "Iniciando", JOptionPane.INFORMATION_MESSAGE);
            byte[] rcvData = new byte[65507];
            byte[] sendData = new byte[65507];
            while(true){
                DatagramPacket datagramPacket = new DatagramPacket(rcvData, rcvData.length);
                System.out.println("Aguardando mensagens...");
                multicastSocket.receive(datagramPacket);
                inetAddressIP = datagramPacket.getAddress();
                inetAddressPort = datagramPacket.getPort();
                rcvData = datagramPacket.getData();

//                itens = c.SelecionarTodos();
//                
//                JSONObject responseMsg = new JSONObject();
//                responseMsg.put("Itens", itens.toArray());
//                        
//                sendData = responseMsg.toString().getBytes();
//                DatagramPacket datagramPacketS = new DatagramPacket(sendData, sendData.length, inetAddressIP, inetAddressPort);
//                multicastSocket.send(datagramPacketS);  
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    public void Enviar(Item item) throws IOException {
        multicastSocket = new MulticastSocket(50000);
        group = InetAddress.getByName("230.0.0.0");
        multicastSocket.joinGroup(group);
        byte[] sendData = new byte[65507];
        JSONObject sendItem = new JSONObject();

        sendItem.put("Nome", item.getNome());
        sendItem.put("Valor", item.getValor());
        sendItem.put("LanceMin", item.getLanceMin());
        sendItem.put("Leilao", item.getLeilaoAtivo());

        sendData = sendItem.toString().getBytes();
        DatagramPacket sendDatagramPacket = new DatagramPacket(sendData, sendData.length, inetAddressIP, inetAddressPort);
        multicastSocket.send(sendDatagramPacket);
    }
    
    public void EnviarAtualizacoes(Item item) throws IOException {
        if (item != null) {
            multicastSocket = new MulticastSocket(50000);
            group = InetAddress.getByName("230.0.0.0");
            multicastSocket.joinGroup(group);
            byte[] sendData = new byte[65507];
            JSONObject sendItem = new JSONObject();

            sendItem.put("AtualizacaoItem", true);
            sendItem.put("Nome", item.getNome());
            sendItem.put("Valor", item.getValor());
            sendItem.put("LanceMin", item.getLanceMin());
            sendItem.put("UltimoLance", "Ninguém");
            sendItem.put("ValorUltimoLance", 0);
            sendItem.put("Tempo", "00:23:21s");
            sendItem.put("Leilao", item.getLeilaoAtivo());

            sendData = sendItem.toString().getBytes();
            DatagramPacket sendDatagramPacket = new DatagramPacket(sendData, sendData.length, inetAddressIP, inetAddressPort);
            multicastSocket.send(sendDatagramPacket);
        }
    }   
}
