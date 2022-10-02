package com.mycompany.leilao.servidor;

import com.mycompany.leilao.compartilhado.CriptografiaAssimetrica;
import static com.mycompany.leilao.compartilhado.CriptografiaSimetrica.createAESKey;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import javax.crypto.SecretKey;
import org.json.JSONObject;

public class ControleEntrada extends Thread {
    public HashMap<String, PublicKey> usuarios = new HashMap<String, PublicKey>();
    public SecretKey chaveSimetrica;

    @Override
    public void run() {
        try {
            int srvPort = 50001;
            DatagramSocket datagramSocket = new DatagramSocket(srvPort);
            byte[] rcvData = new byte[65507];
            byte[] sendData = new byte[65507];
            
            chaveSimetrica = createAESKey();

            while (true) {
                DatagramPacket rcvdDatagramPacket = new DatagramPacket(rcvData, rcvData.length);
                System.out.print("\nWaiting message...");
                datagramSocket.receive(rcvdDatagramPacket);

                InetAddress srcIPAddr = rcvdDatagramPacket.getAddress();
                int srcPort = rcvdDatagramPacket.getPort();
                rcvData = rcvdDatagramPacket.getData();

                String rcvMsg = new String(rcvData, "UTF-8");
                JSONObject jsonRcvMsg = new JSONObject(rcvMsg);
                String userName = jsonRcvMsg.getString("userName");
                String encodedString = jsonRcvMsg.getString("Chave");
                
                byte[] byteArray = java.util.Base64.getDecoder().decode(encodedString);

                PublicKey publicKey = 
                    KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(byteArray));
                
                System.out.print("\nMessage received...");
                System.out.print("\n\tSource IP address: " + srcIPAddr);
                System.out.print("\n\tSource port: " + srcPort);
                System.out.print("\n\tPayload: " + userName);

                usuarios.put(userName, publicKey);
                
                JSONObject sendMsg = new JSONObject();
                
                byte[] port = CriptografiaAssimetrica.do_RSAEncryption("50002", publicKey);
                String encodedPort = java.util.Base64.getEncoder().encodeToString(port);
                sendMsg.put("Port", encodedPort);
                
                byte[] group = CriptografiaAssimetrica.do_RSAEncryption("230.0.0.0", publicKey);
                String encodedGroup = java.util.Base64.getEncoder().encodeToString(group);
                sendMsg.put("Group", encodedGroup);
                
                byte[] Symmetrickey = CriptografiaAssimetrica.do_RSAEncryption(chaveSimetrica.toString(), publicKey);
                String encodedKey = java.util.Base64.getEncoder().encodeToString(Symmetrickey);
                sendMsg.put("Chave", encodedKey);

                sendData = sendMsg.toString().getBytes();

                DatagramPacket sendDatagramPacket = new DatagramPacket(sendData, sendData.length, srcIPAddr, srcPort);
                datagramSocket.send(sendDatagramPacket);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public PublicKey SelecionarPorNome(String nome) {
        return usuarios.get(nome);
    }
    
    public SecretKey SelecionarChave(){
        return chaveSimetrica;
    }
}
