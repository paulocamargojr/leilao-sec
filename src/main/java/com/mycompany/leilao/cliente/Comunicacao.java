package com.mycompany.leilao.cliente;

import com.mycompany.leilao.compartilhado.Item;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class Comunicacao extends Thread {
    ArrayList<Item> itens = new ArrayList<>();
     
    @Override
    public void run() {
        try {
            MulticastSocket multicastSocket = new MulticastSocket();
            InetAddress group = InetAddress.getByName("230.0.0.0");
            multicastSocket.joinGroup(group);
            byte[] rcvData = new byte[65507];
            byte[] sendData = new byte[65507];
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id", 1);
            sendData = jsonObject.toString().getBytes("UTF-8");
            DatagramPacket sendDatagramPacket = new DatagramPacket(sendData, sendData.length, group, 50000);
            multicastSocket.send(sendDatagramPacket);
            
            while(true){
                DatagramPacket rcvDatagramPacket = new DatagramPacket(rcvData, rcvData.length);
                multicastSocket.receive(rcvDatagramPacket);
                rcvData = rcvDatagramPacket.getData();
                String rcvMsg = new String(rcvData, "UTF-8");
                JSONObject jsonRcvMsg = new JSONObject(rcvMsg);

                String nome = jsonRcvMsg.getString("Nome");
                double preco = jsonRcvMsg.getDouble("Valor");
                double lanceMin = jsonRcvMsg.getDouble("LanceMin");

                Item item = new Item(nome, preco, lanceMin);

                itens.add(item);

                JOptionPane.showMessageDialog(null, rcvMsg, "Mensagem recebida pelo servidor!", JOptionPane.INFORMATION_MESSAGE);
            } 
    
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public ArrayList<Item> SelecionarTodos(){
        return itens;
    }
}
