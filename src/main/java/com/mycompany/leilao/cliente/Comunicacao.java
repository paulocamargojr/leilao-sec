package com.mycompany.leilao.cliente;

import com.mycompany.leilao.compartilhado.Item;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import javax.crypto.SecretKey;
import javax.swing.JOptionPane;
import org.json.JSONObject;

public class Comunicacao extends Thread {

    ArrayList<Item> itens = new ArrayList<>();
    
    MulticastSocket multicastSocket;
    InetAddress group;
    SecretKey chave;
    
    public Comunicacao(MulticastSocket multicastSocket, InetAddress group, SecretKey chave){
        this.multicastSocket = multicastSocket;
        this.group = group;
        this.chave = chave;
    }

    @Override
    public void run() {
        try {
            
            multicastSocket.joinGroup(group);
            byte[] rcvData = new byte[65507];

            while (true) {
                DatagramPacket rcvDatagramPacket = new DatagramPacket(rcvData, rcvData.length);
                multicastSocket.receive(rcvDatagramPacket);
                rcvData = rcvDatagramPacket.getData();
                String rcvMsg = new String(rcvData, "UTF-8");
                JSONObject jsonRcvMsg = new JSONObject(rcvMsg);

                if (jsonRcvMsg.has("AtualizacaoItem")) {
                    String nome = jsonRcvMsg.getString("Nome");
                    String ultimoLance = jsonRcvMsg.getString("UltimoLance");
                    double valorUltimoLance = jsonRcvMsg.getDouble("ValorUltimoLance");
                    String tempoRestante = jsonRcvMsg.getString("Tempo");
                    String estaAtivo = jsonRcvMsg.getString("Leilao");

                    for (Item item : itens) {
                        if (item.getNome() == null ? nome == null : item.getNome().equals(nome)) {
                            item.setNomeUltimoLance(ultimoLance);
                            item.setValorUltimoLance(valorUltimoLance);
                            item.setTempo(tempoRestante);
                            item.setLeilaoAtivo(estaAtivo);
                        }
                    }
                } else if (!jsonRcvMsg.has("Usuario")){
                    String nome = jsonRcvMsg.getString("Nome");
                    double preco = jsonRcvMsg.getDouble("Valor");
                    double lanceMin = jsonRcvMsg.getDouble("LanceMin");
                    String estaAtivo = jsonRcvMsg.getString("Leilao");

                    Item item = new Item(nome, preco, lanceMin);
                    item.setLeilaoAtivo(estaAtivo);

                    itens.add(item);
                }

            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Item> SelecionarTodos() {
        return itens;
    }
    
    public void EnviarLance(Usuario usuario, double valor) throws IOException{
        byte[] sendData = new byte[65507];
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Usuario", usuario.getNome());
        jsonObject.put("ValorLance", valor);
        sendData = jsonObject.toString().getBytes("UTF-8");
        DatagramPacket sendDatagramPacket = new DatagramPacket(sendData, sendData.length, group, 50002);
        multicastSocket.send(sendDatagramPacket);
    }
}
