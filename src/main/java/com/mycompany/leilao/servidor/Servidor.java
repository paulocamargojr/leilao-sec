package com.mycompany.leilao.servidor;

import java.io.IOException;

public class Servidor {
    public static void main(String[] args) throws InterruptedException, IOException {        
        TelaGerenciamentoItens tela = new TelaGerenciamentoItens();
        tela.show(); 
        tela.run();
    }
}
