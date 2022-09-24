package com.mycompany.leilao.compartilhado;

import java.util.ArrayList;

public class ControladorItem {
    ArrayList<Item> itens = new ArrayList<>();
    
    public void AdicionarItem(Item item) {
        itens.add(item);
    }
    
//    public Item SelecionarPorId(String Id) {
//        itens.forEach(item -> {
//            if(item.getId() == Id)
//                return item;
//        });
//    }
    
    public ArrayList<Item> SelecionarTodos() {
        return itens;
    }
}
