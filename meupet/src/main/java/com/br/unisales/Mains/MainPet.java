package com.br.unisales.Mains;

import java.util.List;
import java.util.Scanner;

import com.br.unisales.service.PetService;
import com.br.unisales.table.Pet;

public class MainPet {

    public static void main(String[] args) {
        PetService servico = new PetService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar novo pet");
            System.out.println("2. Listar todos os pets");
            System.out.println("3. Excluir um pet");
            System.out.println("4. Alterar um pet");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do pet: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o peso do pet: ");
                    double peso = scanner.nextDouble();
                    scanner.nextLine();  // Limpar o buffer

                    System.out.print("Digite a raça do pet: ");
                    String raca = scanner.nextLine();

                    System.out.println("Digite o id do proprietário: ");
                    int idProprietario = scanner.nextInt();

                    // Salvar o novo pet no banco de dados
                    servico.salvar(null, nome, peso, raca, idProprietario);

                    System.out.println("Pet cadastrado com sucesso!");
                    break;
                case 2:
                    // Listar todos os pets cadastrados
                    List<Pet> lista = servico.listar();
                    for (Pet item : lista) {
                        System.out.println("Id: " + item.getId() + " - Nome: " + item.getNome() + " - Peso: " + item.getPeso() + " - Raça: " + item.getRaca() + item.getIdProprietario());
                    }
                    break;
                case 3:
                    System.out.print("Digite o ID do pet a ser excluído: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer

                    // Excluir o pet do banco de dados
                    String resultadoExcluir = servico.excluir(idExcluir);
                    if (resultadoExcluir.equals("ok")) {
                        System.out.println("Pet excluído com sucesso!");
                    } else {
                        System.out.println("Erro ao excluir o pet. Verifique se o ID está correto.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do pet a ser alterado: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine();  // Limpar o buffer

                    Pet pet = servico.buscarPorId(idAlterar);
                    if (pet == null) {
                        System.out.println("Pet não encontrado. Verifique se o ID está correto.");
                        break;
                    }

                    System.out.print("Digite o novo nome do pet (atual: " + pet.getNome() + "): ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Digite o novo peso do pet (atual: " + pet.getPeso() + "): ");
                    double novoPeso = scanner.nextDouble();
                    scanner.nextLine();  // Limpar o buffer

                    System.out.print("Digite a nova raça do pet (atual: " + pet.getRaca() + "): ");
                    String novaRaca = scanner.nextLine();

                    // Alterar o pet no banco de dados
                    servico.salvar(idAlterar, novoNome, novoPeso, novaRaca, null);

                    System.out.println("Pet alterado com sucesso!");
                    break;
                case 5:
                    scanner.close();
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
