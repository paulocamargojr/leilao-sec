package com.mycompany.leilao.compartilhado;

import java.security.PrivateKey;
import java.security.PublicKey;

public class Usuario {
    public String userName;
    public PrivateKey privateKey;
    public PublicKey publicKey;
    
    public Usuario(String userName){
        this.userName = userName;
    }
    
    public String getNome(){
        return this.userName;
    }
}
