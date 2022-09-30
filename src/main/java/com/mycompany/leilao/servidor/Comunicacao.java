package com.mycompany.leilao.servidor;

import com.mycompany.leilao.compartilhado.ControladorItem;
import com.mycompany.leilao.compartilhado.Item;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
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
        //t.start();
    }
    
    @Override
    public void run() {
        try {
            multicastSocket = new MulticastSocket(50000);
            group = InetAddress.getByName("230.0.0.0");
            multicastSocket.joinGroup(group);
            JOptionPane.showMessageDialog(null, "Iniciando servidor...", "Iniciando", JOptionPane.INFORMATION_MESSAGE);
            byte[] rcvData = new byte[65507];
            while(true){
                DatagramPacket datagramPacket = new DatagramPacket(rcvData, rcvData.length);
                System.out.println("Aguardando mensagens...");
                multicastSocket.receive(datagramPacket);
                inetAddressIP = datagramPacket.getAddress();
                inetAddressPort = datagramPacket.getPort();
                rcvData = datagramPacket.getData(); 
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    Thread t = new Thread(){
         public static void main(String[] args) throws SocketException, IOException {
        int srvPort = 50001;
        
        DatagramSocket srvSock = new DatagramSocket(srvPort);
        
        byte[] rcvdData = new byte[65507];
        byte[] sendData = new byte[65507];
        
        while (true) {
            DatagramPacket rcvdPkt = new DatagramPacket(rcvdData, rcvdData.length);
            System.out.print("\nWaiting message...");
            srvSock.receive(rcvdPkt);
            
            InetAddress srcIPAddr = rcvdPkt.getAddress();
            int srcPort = rcvdPkt.getPort();
            rcvdData = rcvdPkt.getData();
            
            String rcvMsg = new String(rcvdData, "UTF-8");
            JSONObject jsonRcvMsg = new JSONObject(rcvMsg);
            
            String userName = jsonRcvMsg.getString("userName");
            
            System.out.print("\nMessage received...");
            System.out.print("\n\tSource IP address: " + srcIPAddr);
            System.out.print("\n\tSource port: " + srcPort);
            System.out.print("\n\tSource payload lenhth: " + rcvdPkt.getLength());
            System.out.print("\n\tPayload: " + userName);
            
            String srvMsg = "Message successfully received by server...";
            
            sendData = srvMsg.getBytes();
            
            DatagramPacket sendPkt = new DatagramPacket(sendData, sendData.length, srcIPAddr, srcPort);
            
            System.out.println("\tSending a reply message...");
            srvSock.send(sendPkt);
        }
    }
    };
    
    
    
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
