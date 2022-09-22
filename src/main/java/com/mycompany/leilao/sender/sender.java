package com.mycompany.leilao.sender;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class sender {
    public static void main(String[] args) {
        try {
            MulticastSocket multicastSocket = new MulticastSocket();
            InetAddress group = InetAddress.getByName("230.0.0.0");
            multicastSocket.joinGroup(group);
            byte[] rcvData = new byte[65507];
            byte[] sendData = new byte[65507];
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id", 1);
            jsonObject.put("text", "Hello world!");
            sendData = jsonObject.toString().getBytes("UTF-8");
            DatagramPacket sendDatagramPacket = new DatagramPacket(sendData, sendData.length, group, 50000);
            multicastSocket.send(sendDatagramPacket);
            DatagramPacket rcvDatagramPacket = new DatagramPacket(rcvData, rcvData.length);
            multicastSocket.receive(rcvDatagramPacket);
            
            String rcvMsg = new String(rcvData, "UTF-8");
            JOptionPane.showMessageDialog(null, rcvMsg, "Mensagem recebida pelo servidor!", JOptionPane.INFORMATION_MESSAGE);
    
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
