import java.time.LocalDateTime;
import java.util.Scanner;

import automovel.Automovel;
import automovel.Carro;
import automovel.Moto;
import estacionamento.Estacionamento;
import exceptions.AutomovelDuplicadoException;
import exceptions.AutomovelIndefinidoException;
import pessoa.Cliente;

/**
 * main
 */
public class main {

    public static void main(String[] args) {
        Estacionamento park = new Estacionamento(1.2, 1, 10, 6);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao seu mais moderno gerenciador de estacionamento!!");
        int option = -1;
        do {
            System.out.println("Selecione uma opção abaixo para continuar:");

            System.out.println("1 - Registrar entrada de veículo");
            System.out.println("2 - Registrar saída de veículo");
            System.out.println("3 - Obter balanço de carros do dia");
            System.out.println("4 - Obter balanço de motos do dia");
            System.out.println("5 - Obter lucro total do dia até o momento");
            System.out.println("6 - Obter lucro total do período");
            System.out.println("7 - Iniciar registro de um novo dia");
            System.out.println("0 - Sair do programa");

            try {
                System.out.print("\nEscolha uma opção: ");
                option = scanner.nextInt();
            } catch (Exception e) {
                System.err.println("O valor digitado é inválido");
            }

            scanner.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Qual o tipo do veículo?");
                    System.out.println("1 - Carro");
                    System.out.println("2 - Moto");
                    try {
                        System.out.print("\nEscolha uma opção: ");
                        int opt = scanner.nextInt();

                        if (opt > 2 || opt < 1) {
                            throw new Exception("O valor digitado é invalido");
                        }

                        scanner.nextLine();
                        System.out.println("Digite a placa do veículo");
                        String placa = scanner.nextLine();
                        Automovel auto = park.verificaPLaca(placa.trim());

                        if (auto != null) {
                            throw new AutomovelDuplicadoException("Placa duplicada: " + auto.getPlaca());
                        }

                        System.out.println("\nDigite o nome do cliente");
                        String nome = scanner.nextLine();

                        System.out.println("\nDigite o telefone do cliente");
                        String telefone = scanner.nextLine();

                        System.out.println("\nDigite o email do cliente");
                        String email = scanner.nextLine();

                        Cliente cliente = new Cliente(nome, email, telefone);
                        if (opt == 1) {
                            Carro carro = new Carro(placa.trim(), LocalDateTime.now(), cliente);
                            park.adicionarCarro(carro);
                        } else {
                            Moto moto = new Moto(placa.trim(), LocalDateTime.now(), cliente);
                            park.adicionarMoto(moto);
                        }

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Digite a placa do veículo");
                        String placa = scanner.nextLine();

                        Automovel auto = park.verificaPLaca(placa.trim());
                        if (auto == null) {
                            throw new AutomovelIndefinidoException("Automóvel não foi encontrado: " + placa);
                        }

                        System.out.println("Qual o tipo do veículo?");
                        System.out.println("1 - Carro");
                        System.out.println("2 - Moto");

                        System.out.print("\nEscolha uma opção: ");
                        int opt = scanner.nextInt();

                        if (opt > 2 || opt < 1) {
                            scanner.close();
                            throw new Error("O valor digitado é invalido");
                        }

                        if (opt == 1) {
                            park.removerCarro(placa.trim());
                        } else {
                            park.removerMoto(placa.trim());
                        }

                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("A quantidade de carros que já passaram hoje no seu estacionamento foi de "
                            + park.quantidadeDeCarrosDia());
                    break;
                case 4:
                    System.out.println("A quantidade de motos que já passaram hoje no seu estacionamento foi de "
                            + park.quantidadeDeMotoDia());
                    break;
                case 5:
                    System.out.println("O lucro total do dia até o momento é de " + park.obterLucroTotalDia());
                    break;
                case 6:
                    System.out.println("O lucro total do período até o momento é de " + park.obterLucroTotalPeriodo());
                    break;
                case 7:
                    park.novoDia();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Nenhuma opção válida foi digitada");
                    break;
            }
        } while (option != 0);
        scanner.close();
    }
}