package org.controleFinanceiro;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransacaoService service = new TransacaoService();

        boolean executando = true;
        while (executando) {
            exibirMenu();
            System.out.print("Digite sua opção: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    cadastrarTransacao(scanner, service);
                    break;
                case "2":
                    listarTransacoes(service);
                    break;
                case "3":
                    exibirSaldo(service);
                    break;
                case "0":
                    executando = false;
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n===== Controle Financeiro =====");
        System.out.println("1. Adicionar Transação");
        System.out.println("2. Listar Transações");
        System.out.println("3. Ver Saldo");
        System.out.println("0. Sair");
    }

    private static void cadastrarTransacao(Scanner scanner, TransacaoService service) {
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Valor: ");
        double valor = 0.0;
        try {
            valor = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido! Usando 0 como padrão.");
        }

        System.out.print("Data (yyyy-MM-dd): ");
        LocalDate data;
        try {
            data = LocalDate.parse(scanner.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida! Usando data atual como padrão.");
            data = LocalDate.now();
        }

        System.out.println("Tipo de Transação: ");
        System.out.println("1 - ENTRADA");
        System.out.println("2 - SAÍDA");
        String tipoStr = scanner.nextLine();

        TipoTransacao tipo;
        if (tipoStr.equals("1")) {
            tipo = TipoTransacao.ENTRADA;
        } else {
            tipo = TipoTransacao.SAIDA;
        }

        Transacao transacao = new Transacao(descricao, valor, data, tipo);
        service.adicionarTransacao(transacao);
        System.out.println("Transação adicionada com sucesso!");
    }

    private static void listarTransacoes(TransacaoService service) {
        System.out.println("\n==== Lista de Transações ====");
        if (service.listarTransacoes().isEmpty()) {
            System.out.println("Nenhuma transação cadastrada.");
        } else {
            for (Transacao t : service.listarTransacoes()) {
                System.out.println(t);
            }
        }
    }

    private static void exibirSaldo(TransacaoService service) {
        double saldo = service.calcularSaldo();
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
    }
}