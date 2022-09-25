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
    
    public Comunicacao(ControladorItem c) {
        this.c = c;
    }
    
    @Override
    public void run() {
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
                inetAddressIP = datagramPacket.getAddress();
                inetAddressPort = datagramPacket.getPort();
                rcvData = datagramPacket.getData();
                String msg = new String(rcvData, "UTF-8");
                JSONObject jsonObject = new JSONObject(msg);
                int Id = jsonObject.getInt("Id");
                JOptionPane.showMessageDialog(null, "Id da mensagem: " + Id, "",  JOptionPane.INFORMATION_MESSAGE);

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
        MulticastSocket multicastSocket = new MulticastSocket(50000);
        InetAddress group = InetAddress.getByName("230.0.0.0");
        multicastSocket.joinGroup(group);
        byte[] sendData = new byte[65507];
        JSONObject sendItem = new JSONObject();

        sendItem.put("Nome", item.getNome());
        sendItem.put("Valor", item.getValor());
        sendItem.put("LanceMin", item.getLanceMin());

        sendData = sendItem.toString().getBytes();
        DatagramPacket sendDatagramPacket = new DatagramPacket(sendData, sendData.length, inetAddressIP, inetAddressPort);
        multicastSocket.send(sendDatagramPacket);
    }
}
