package com.mycompany.leilao.cliente;

import java.security.KeyPair;

public class Usuario {
    public String userName;
    public KeyPair chaves;
    
    public Usuario(String userName) throws Exception{
        this.userName = userName;
        chaves = CriptografiaAssimetrica.generateRSAKkeyPair();
    }
    
    public String getNome(){
        return this.userName;
    }
}
