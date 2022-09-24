package com.mycompany.leilao.servidor;

public class Servidor {
    public static void main(String[] args) throws InterruptedException {
        Comunicacao c = new Comunicacao();
        c.start();
        
        Thread.sleep(1500);
        
        TelaGerenciamentoItens tela = new TelaGerenciamentoItens();
        tela.show(); 
    }
}
