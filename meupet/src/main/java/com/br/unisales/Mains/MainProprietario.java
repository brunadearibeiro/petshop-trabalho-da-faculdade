package com.br.unisales.Mains;

import java.util.List;
import java.util.Scanner;

import com.br.unisales.service.ProprietarioService;
import com.br.unisales.table.Proprietario;

public class MainProprietario {

    public static void main(String[] args) {
        ProprietarioService servico = new ProprietarioService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar novo proprietário");
            System.out.println("2. Listar todos os proprietários");
            System.out.println("3. Excluir um proprietário");
            System.out.println("4. Alterar um proprietário");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();

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
                    encerrarPrograma(scanner);
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

    private static void encerrarPrograma(Scanner scanner) {
        scanner.close();
        System.out.println("Saindo...");
    }
}
