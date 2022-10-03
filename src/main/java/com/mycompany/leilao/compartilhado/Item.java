package com.mycompany.leilao.compartilhado;

import java.security.Timestamp;
import java.util.UUID;

public class Item {
    private String id;
    private String nome;
    private double valor;
    private double lanceMin;
    private String nomeUltimoLance;
    private double valorUltimoLance;
    private String leilaoAtivo;
    private int tempo;
    
    public Item(String nome, double valor, double lanceMin) {
        id = gerarId();
        this.nome = nome;
        this.valor = valor;
        this.lanceMin = lanceMin; 
    }
    
    public Item(){
        
    }
    
    private String gerarId() {
        return UUID.randomUUID().toString(); 
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getId(){
        return this.id;
    }
    
    public double getValor() {
        return this.valor;
    }
    
    public double getLanceMin() {
        return this.lanceMin;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setLanceMin(double lanceMin) {
        this.lanceMin = lanceMin;
    }

    public String getNomeUltimoLance() {
        return nomeUltimoLance;
    }

    public void setNomeUltimoLance(String nomeUltimoLance) {
        this.nomeUltimoLance = nomeUltimoLance;
    }

    public double getValorUltimoLance() {
        return valorUltimoLance;
    }

    public void setValorUltimoLance(double valorUltimoLance) {
        this.valorUltimoLance = valorUltimoLance;
    }

    public void setLeilaoAtivo(String leilaoAtivo) {
        this.leilaoAtivo = leilaoAtivo;
    }
    
    public String getLeilaoAtivo(){
        return this.leilaoAtivo;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
    
    @Override
    public String toString() {
        return "id=" + id + ", nome=" + nome + ", valor=" + valor + ", lanceMin=" + lanceMin;
    }
}
