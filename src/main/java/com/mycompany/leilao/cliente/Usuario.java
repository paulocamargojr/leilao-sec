package com.mycompany.leilao.cliente;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Usuario {
    public String userName;
    public KeyPair chaves;
    public PublicKey chavePublica;
    public PrivateKey chavePrivada;
    
    public Usuario(String userName) throws Exception{
        this.userName = userName;
        chaves = CriptografiaAssimetrica.generateRSAKkeyPair();
        chavePublica = chaves.getPublic();
        chavePrivada = chaves.getPrivate();
    }
    
    public String getNome(){
        return this.userName;
    }

    public PublicKey getChavePublica() {
        return chavePublica;
    }
    
}
