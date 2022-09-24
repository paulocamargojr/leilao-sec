package com.mycompany.leilao.compartilhado;

import java.security.Timestamp;
import java.util.UUID;

public class Item {
    private String id;
    private String nome;
    private double valor;
    private double lanceMin;
    private Timestamp tempo;
    
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

    @Override
    public String toString() {
        return "id=" + id + ", nome=" + nome + ", valor=" + valor + ", lanceMin=" + lanceMin;
    }
}
