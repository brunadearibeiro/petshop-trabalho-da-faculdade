package com.br.unisales;

import java.util.List;

import com.br.unisales.service.PetService;
import com.br.unisales.table.Pet;

public class Main {

    public static void main(String[] args) {
        PetService servico = new PetService();
        servico.salvar(null, "Totó", 8.7, "Vira-Lata");
        servico.salvar(null, "Mel", 14.5, "Fila");
        List<Pet> lista = servico.listar();
        for (Pet item : lista) {
            System.out.println("Id: " + item.getId() + " - Nome: " + item.getNome());
        }
    }

}