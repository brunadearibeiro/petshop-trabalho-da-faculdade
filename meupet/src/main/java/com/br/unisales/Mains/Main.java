package com.br.unisales.Mains;

import java.util.List;
import java.util.Scanner;

import com.br.unisales.service.PetService;
import com.br.unisales.service.ProprietarioService;
import com.br.unisales.table.Pet;
import com.br.unisales.table.Proprietario;

public class Main {

    public static void main(String[] args) {
        PetService petService = new PetService();
        ProprietarioService proprietarioService = new ProprietarioService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Gerenciar Pets");
            System.out.println("2. Gerenciar Proprietários");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int escolha = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            switch (escolha) {
                case 1:
                    gerenciarPets(petService, scanner);
                    break;
                case 2:
                    gerenciarProprietarios(proprietarioService, scanner);
                    break;
                case 3:
                    scanner.close();
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void gerenciarPets(PetService servico, Scanner scanner) {
        while (true) {
            System.out.println("\nMenu Pets:");
            System.out.println("1. Cadastrar novo pet");
            System.out.println("2. Listar todos os pets");
            System.out.println("3. Excluir um pet");
            System.out.println("4. Alterar um pet");
            System.out.println("5. Voltar ao menu principal");
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

                    System.out.print("Digite o id do proprietário: ");
                    int idProprietario = scanner.nextInt();

                    // Salvar o novo pet no banco de dados
                    servico.salvar(null, nome, peso, raca, idProprietario);

                    System.out.println("Pet cadastrado com sucesso!");
                    break;
                case 2:
                    // Listar todos os pets cadastrados
                    List<Pet> lista = servico.listar();
                    for (Pet item : lista) {
                        System.out.println("Id: " + item.getId() + " - Nome: " + item.getNome() + " - Peso: " + item.getPeso() + " - Raça: " + item.getRaca() + " - IdProprietario: " + item.getIdProprietario());
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
                    servico.salvar(idAlterar, novoNome, novoPeso, novaRaca, pet.getIdProprietario());

                    System.out.println("Pet alterado com sucesso!");
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void gerenciarProprietarios(ProprietarioService servico, Scanner scanner) {
        while (true) {
            System.out.println("\nMenu Proprietários:");
            System.out.println("1. Cadastrar novo proprietário");
            System.out.println("2. Listar todos os proprietários");
            System.out.println("3. Excluir um proprietário");
            System.out.println("4. Alterar um proprietário");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            switch (opcao) {
                case 1:
                    cadastrarProprietario(servico, scanner);
                    break;
                case 2:
                    listarProprietarios(servico);
                    break;
                case 3:
                    excluirProprietario(servico, scanner);
                    break;
                case 4:
                    alterarProprietario(servico, scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarProprietario(ProprietarioService servico, Scanner scanner) {
        System.out.print("Digite o nome do proprietário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o sexo do proprietário (M/F): ");
        String sexo = scanner.nextLine();

        System.out.print("Digite o CPF do proprietário: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o e-mail do proprietário: ");
        String email = scanner.nextLine();

        System.out.print("Digite o celular do proprietário: ");
        String celular = scanner.nextLine();

        // Salvar o novo proprietário no banco de dados
        Proprietario novoProprietario = servico.salvar(null, nome, sexo, cpf, email, celular);
        if (novoProprietario != null) {
            System.out.println("Proprietário cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar o proprietário. Verifique os dados informados.");
        }
    }

    private static void listarProprietarios(ProprietarioService servico) {
        // Listar todos os proprietários cadastrados
        List<Proprietario> lista = servico.listar();
        if (lista.isEmpty()) {
            System.out.println("Não há proprietários cadastrados.");
        } else {
            System.out.println("Lista de Proprietários:");
            for (Proprietario proprietario : lista) {
                System.out.println("Id: " + proprietario.getId() + " - Nome: " + proprietario.getNome() +
                        " - CPF: " + proprietario.getCpf() + " - E-mail: " + proprietario.getEmail() +
                        " - Celular: " + proprietario.getCelular());
            }
        }
    }

    private static void excluirProprietario(ProprietarioService servico, Scanner scanner) {
        System.out.print("Digite o ID do proprietário a ser excluído: ");
        int idExcluir = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer

        // Excluir o proprietário do banco de dados
        String resultadoExcluir = servico.excluir(idExcluir);
        if (resultadoExcluir.equals("ok")) {
            System.out.println("Proprietário excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir o proprietário. Verifique se o ID está correto.");
        }
    }

    private static void alterarProprietario(ProprietarioService servico, Scanner scanner) {
        System.out.print("Digite o ID do proprietário a ser alterado: ");
        int idAlterar = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer

        Proprietario proprietario = servico.buscarPorId(idAlterar);
        if (proprietario == null) {
            System.out.println("Proprietário não encontrado. Verifique se o ID está correto.");
            return;
        }

        System.out.println("Dados atuais do proprietário:");
        System.out.println("Nome: " + proprietario.getNome());
        System.out.println("Sexo: " + proprietario.getSexo());
        System.out.println("CPF: " + proprietario.getCpf());
        System.out.println("E-mail: " + proprietario.getEmail());
        System.out.println("Celular: " + proprietario.getCelular());

        System.out.print("Digite o novo nome do proprietário (atual: " + proprietario.getNome() + "): ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite o novo sexo do proprietário (atual: " + proprietario.getSexo() + "): ");
        String novoSexo = scanner.nextLine();

        System.out.print("Digite o novo CPF do proprietário (atual: " + proprietario.getCpf() + "): ");
        String novoCpf = scanner.nextLine();

        System.out.print("Digite o novo e-mail do proprietário (atual: " + proprietario.getEmail() + "): ");
        String novoEmail = scanner.nextLine();

        System.out.print("Digite o novo celular do proprietário (atual: " + proprietario.getCelular() + "): ");
        String novoCelular = scanner.nextLine();

        // Alterar o proprietário no banco de dados
        Proprietario proprietarioAtualizado = servico.salvar(idAlterar, novoNome, novoSexo, novoCpf, novoEmail, novoCelular);
        if (proprietarioAtualizado != null) {
            System.out.println("Proprietário alterado com sucesso!");
        } else {
            System.out.println("Erro ao alterar o proprietário. Verifique os dados informados.");
        }
    }
}
