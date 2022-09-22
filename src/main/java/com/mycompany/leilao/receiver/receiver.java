package com.mycompany.leilao.receiver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class receiver {
    public static void main(String[] args) {
        TelaCadastroItens tela = new TelaCadastroItens();
        tela.show();
    }
    
    public void iniciarComunicacao() {
        try {
            MulticastSocket multicastSocket = new MulticastSocket(50000);
            InetAddress group = InetAddress.getByName("230.0.0.0");
            multicastSocket.joinGroup(group);
            JOptionPane.showMessageDialog(null, "Iniciando servidor...", "Iniciando", JOptionPane.INFORMATION_MESSAGE);
            byte[] rcvData = new byte[65507];
            byte[] sendData = new byte[65507];
            while(true){
                DatagramPacket datagramPacket = new DatagramPacket(rcvData, rcvData.length);
                JOptionPane.showMessageDialog(null, "Aguardando mensagens...", "Aguardando", JOptionPane.INFORMATION_MESSAGE);
                multicastSocket.receive(datagramPacket);
                InetAddress inetAddressIP = datagramPacket.getAddress();
                int inetAddressPort = datagramPacket.getPort();
                rcvData = datagramPacket.getData();
                String msg = new String(rcvData, "UTF-8");
                JSONObject jsonObject = new JSONObject(msg);
                int Id = jsonObject.getInt("Id");
                String text = jsonObject.getString("text");
                JOptionPane.showMessageDialog(null, "Mensagem recebida: " + text + "\n" + "Id da mensagem: " + Id, "",  JOptionPane.INFORMATION_MESSAGE);
                String responseMsg = "Mesagem recebida com sucesso!";
                sendData = responseMsg.getBytes();
                DatagramPacket datagramPacketS = new DatagramPacket(sendData, sendData.length, inetAddressIP, inetAddressPort);
                multicastSocket.send(datagramPacket);  
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
}
