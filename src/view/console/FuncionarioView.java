package view.console;

import base.*;
import repository.*;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FuncionarioView extends ViewBase {
    private final Scanner sc;
    private final FuncionarioRepository funcRepo;
    private final EntregadorView entregadores;

    public FuncionarioView(Scanner sc, EntregadorView entregadores) {
        super(sc);
        this.sc = sc;
        this.entregadores = entregadores;
        this.funcRepo = FuncionarioRepository.getInstancia();
    }

    public void menuInicial() {
        System.out.print("Tem cadastro no sistema? (s/n):  ");
        String confirmacao = sc.nextLine();
        if (confirmacao.equals("n")) {
            this.cadastrarFuncionario();
            this.loginFuncionario();
        } else if (confirmacao.equals("s")) {
            this.loginFuncionario();
        }
    }

    private void cadastrarFuncionario() {
        System.out.println("\n==============================");
        System.out.println("          Bem-vindo !         ");
        System.out.println("==============================");
        System.out.println("Faça o seu cadastro agora mesmo para acompanhar o seu restaurante!");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        this.funcRepo.adicionarFuncionario(new Funcionario(nome, cpf));
        CLS();
        System.out.println("\n\nCadastro efetuado com sucesso!\nEfetue seu login com o seu CPF.");

    }

    private void loginFuncionario() {
        System.out.println("\n==============================");
        System.out.println("     Login - funcionário      ");
        System.out.println("==============================");
        System.out.print("Seu CPF: ");
        String cpf = sc.nextLine();
        Funcionario funcionario = this.funcRepo.buscarFuncionarioPeloCpf(cpf);
        if (funcionario == null) {
            CLS();
            System.out.println("Não foi encontrado um funcionário com este CPF.\n");
            menuInicial();
        } else {
            CLS();
            System.out.println("Seja bem-vindo, " + funcionario.getNome() + "!");
            menuPrincipal();
        }
    }

    private void menuPrincipal() {
        boolean autenticado = true;
        while (autenticado) {
            System.out.println("\n==============================");
            System.out.println("        Sistema Faminto        ");
            System.out.println("==============================");
            System.out.println("1. Ver Pedidos do Dia");
            System.out.println("2. Listar Entregadores");
            System.out.println("3. Itens Vendidos");
            System.out.println("4. Reativar Clientes");
            System.out.println("0. Logout");
            System.out.println("------------------------------");
            System.out.print("\nEscolha uma opção: ");
            try {
                int value = this.readInt();
                switch (value) {
                    case 0:
                        autenticado = false;
                        CLS();
                        break;
                    case 1:
                        this.verPedidosDoDia();
                        CLS();
                        break;
                    case 2:
                        this.listarEntregadores();
                        CLS();
                        break;
                    case 3:
                        this.verItensVendidos();
                        CLS();
                        break;
                    case 4:
                        this.reativarClientes();
                        CLS();
                        break;
                    default:
                        CLS();
                        System.out.println("** Opção inválida ! **");
                }
            } catch (Exception e) {
                System.out.println("Aconteceu um erro!");
                System.err.println(e);
                CLS();
                System.out.println("** Opção inválida ! **");
                sc.nextLine();
            }
        }
    }

    private void verPedidosDoDia() {
        CLS();
        System.out.println("\n==============================");
        System.out.println("       Lista de pedidos       ");
        System.out.println("==============================\n");
        if (PedidoRepository.getInstancia().getPedidos().size() == 0) {
            System.out.println("Nenhum pedido efetuado hoje.");
        } else {
            PedidoRepository.getInstancia().printPedidosFuncionario();
        }
        System.out.println("\n------------------------------");
        System.out.println("Pressione enter para voltar ao menu principal.");
        sc.nextLine();
    }

    private void listarEntregadores() {
        CLS();
        System.out.println("\n==============================");
        System.out.println("     Lista de entregadores     ");
        System.out.println("==============================\n");
        if (EntregadorRepository.getInstancia().getEntregadores().size() == 0) {
            System.out.println("Nenhum entregador cadastrado no momento.");
        } else {
            EntregadorRepository.getInstancia().printEntregadores();
        }
        System.out.println("\n------------------------------");
        System.out.println("Pressione enter para voltar ao menu principal.");
        sc.nextLine();
    }

    private void verItensVendidos() {
        CLS();
        System.out.println("\n==============================");
        System.out.println("        Itens vendidos        ");
        System.out.println("==============================\n");
        System.out.println("Código do Produto | Tipo do Produto | Produto, Valor, Estoque | Vendidos no dia");
        int count = 0;

        List<Cardapio> cardapio = CardapioRepository.getInstancia().getCardapioRepositorio();
        List<Pedido> pedidos = PedidoRepository.getInstancia().getPedidos().stream()
                .filter(p -> p.getSituacao() == SituacaoPedidoEnum.CONCLUIDO).collect(Collectors.toList());
        for (int i = 0; i < cardapio.size(); i += 1) {
            Cardapio item = CardapioRepository.getInstancia().buscarItem(i);
            for (int j = 0; j < pedidos.size(); j += 1) {
                Pedido pedido = PedidoRepository.getInstancia().buscarPedidoConcluido(j);
                for (int k = 0; k < pedido.getItensPedidos().size(); k += 1) {
                    ItemPedido itemPedido = pedido.getItensPedidos().get(k);
                    if (item.getNome().equals(itemPedido.getCardapio().getNome())) {
                        if (itemPedido.getQuantidade() > 5) {
                            itemPedido.getCardapio().setPicoDeProcura(true);
                        }
                    }
                }
            }
        }
        if (pedidos.size() == 0) {
            System.out.println("Nenhum item foi pedido até o momento");
        } else {
            for (int i = 0; i < cardapio.size(); i += 1) {
                count++;
                Cardapio item = CardapioRepository.getInstancia().buscarItem(i);
                if (item.isPicoDeProcura()) {
                    System.out.print(count + ". ");
                    System.out.println(item + " - " + (item.getEstoqueInicial() - item.getQtdEstoque())
                            + " vendidos hoje. [PICO DE PROCURA]");
                } else {
                    if ((item.getEstoqueInicial() - item.getQtdEstoque()) != 0) {
                        System.out.print(count + ". ");
                        System.out.println(
                                item + " - " + (item.getEstoqueInicial() - item.getQtdEstoque()) + " vendidos hoje.");
                    }

                }
            }
        }

        System.out.println("\n------------------------------");
        System.out.println("Pressione enter para voltar ao menu principal.");
        sc.nextLine();
    }

    private void reativarClientes() {
        CLS();
        System.out.println("===============================");
        System.out.println("       Lista de clientes       ");
        System.out.println("===============================\n");
        ClienteRepository.getInstancia().printClientesDesativados();
        System.out.println("\n------------------------------");
        System.out.print("Digite o número do cliente para reativá-lo ou digite 0 para retornar. ");
        int escolha = this.readInt();
        switch (escolha) {
            case 0:
                CLS();
                break;
            default:
                ClienteRepository.getInstancia().buscarCliente(escolha - 1).setAtivo(true);
                CLS();
                System.out.print("O(a) cliente " + ClienteRepository.getInstancia().buscarCliente(escolha - 1).getNome()
                        + " foi reativado(a).\n");
                Cliente clienteTemp = ClienteRepository.getInstancia().buscarCliente(escolha - 1);
                ClienteRepository.getInstancia().removerCliente(escolha - 1);
                ClienteRepository.getInstancia().adicionarCliente(clienteTemp);
                break;
        }
    }
}
