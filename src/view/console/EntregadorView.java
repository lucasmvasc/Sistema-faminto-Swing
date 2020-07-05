package view.console;

import base.Entregador;
import base.Pedido;
import base.SituacaoPedidoEnum;
import repository.EntregadorRepository;
import repository.PedidoRepository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EntregadorView extends ViewBase {
    private final Scanner sc;
    private final PedidoRepository pedidos;
    private final EntregadorRepository entregadores;

    private Entregador autenticado;

    public EntregadorView(Scanner sc) {
        super(sc);
        this.sc = sc;
        this.pedidos = PedidoRepository.getInstancia();
        this.entregadores = EntregadorRepository.getInstancia();
    }

    public void menuInicial() {
        System.out.print("Tem cadastro no sistema? (s/n):  ");
        String confirmacao = sc.nextLine();
        if (confirmacao.equals("n")) {
            this.cadastrarEntregador();
        } else if (confirmacao.equals("s")) {
            this.loginEntregador();
        }
    }

    public void menuPrincipal() {
        boolean autenticado = true;
        while (autenticado) {
            System.out.println("\n==============================");
            System.out.println("        Sistema Faminto        ");
            System.out.println("==============================");
            System.out.println("1. Ver Pedidos do Dia");
            System.out.println("2. Aceitar Pedido");
            System.out.println("3. Devolver Pedido");
            System.out.println("4. Concluir Pedido");
            System.out.println("5. Ver Comissão");
            System.out.println("0. Logout");
            System.out.println("------------------------------");
            System.out.print("\nEscolha uma opção: ");
            try {
                int value = this.readInt();
                switch (value) {
                    case 0:
                        autenticado = false;
                        break;
                    case 1:
                        this.verPedidosDoDia();
                        CLS();
                        break;
                    case 2:
                        this.aceitarPedido();
                        CLS();
                        break;
                    case 3:
                        this.devolverPedido();
                        CLS();
                        break;
                    case 4:
                        this.concluirPedido();
                        CLS();
                        break;
                    case 5:
                        this.verComissao();
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

    public void loginEntregador() {
        System.out.println("\n==============================");
        System.out.println("       Login - entregador      ");
        System.out.println("==============================");
        System.out.print("Placa do seu veículo: ");
        String placa = sc.nextLine();
        this.autenticado = this.entregadores.buscarEntregadorPelaPlaca(placa);
        if (this.autenticado == null) {
            System.out.println("Não foi encontrado um entregador com esta placa.");
        } else {
            CLS();
            System.out.println("Seja bem-vindo, " + this.autenticado.getNome() + "!");
            menuPrincipal();
        }
    }

    public void cadastrarEntregador() {
        System.out.println("\n==============================");
        System.out.println("          Bem-vindo !         ");
        System.out.println("==============================");
        System.out.println("Faça o seu cadastro agora mesmo e começe a receber pedidos!");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Placa do seu veículo: ");
        String placa = sc.nextLine();

        this.entregadores.adicionarEntregador(new Entregador(nome, placa));
        CLS();
        System.out.println("Cadastro efetuado com sucesso!\nEfetue seu login com a placa do seu veículo.");
    }

    public void exibirPedidos(List<Pedido> pedidos) {
        System.out.println("Código | Cliente | Endereço | Valor | Situação");

        for (int i = 0; i < pedidos.size(); i += 1) {
            Pedido pedido = pedidos.get(i);
            System.out.printf("%d | %s | %s | %s | %s\n", i + 1, pedido.getCliente().getNome(),
                    pedido.getCliente().getEndereco(), String.format("R$ %.2f", pedido.getValorTotal()),
                    Pedido.getSituacaoString(pedido.getSituacao()));
        }
    }

    public void verPedidosDoDia() {
        System.out.println("\n==============================");
        System.out.println("    Lista de pedidos do dia    ");
        System.out.println("==============================");
        System.out.println("Veja abaixo os pedidos do dia:\n");
        List<Pedido> pedidos = this.pedidos.buscarPedidosPeloEntregador(autenticado.getPlacaDoVeiculo());
        if (pedidos.size() <= 0) {
            System.out.println("Não há pedidos para hoje, que tal aceitar alguns? :)");
        } else {
            this.exibirPedidos(pedidos);
        }
        System.out.println("Pressione enter para voltar ao menu principal.");
        sc.nextLine();
    }

    public void verComissao() {
        System.out.println("\n==============================");
        System.out.println("          Sua comissão");
        System.out.println("==============================");
        System.out.println("Veja abaixo sua comissão:\n");
        List<Pedido> pedidos = this.pedidos.buscarPedidosPeloEntregador(autenticado.getPlacaDoVeiculo()).stream()
                .filter(p -> p.getSituacao() == SituacaoPedidoEnum.CONCLUIDO).collect(Collectors.toList());

        if (pedidos.size() <= 0) {
            System.out.println("Não há comissões, que tal entregar alguns pedidos? :)");
        } else {
            System.out.println("Qtde de entregas | Valor total | Comissão (5% do valor total)");
            double total = 0;
            for (int i = 0; i < pedidos.size(); i += 1) {
                Pedido pedido = pedidos.get(i);
                total += pedido.getValorTotal();
            }
            System.out.printf("%d | R$ %.2f | R$ %.2f\n", pedidos.size(), total, total * (5.0 / 100.0));
        }

        System.out.println("Pressione enter para voltar ao menu principal.");
        sc.nextLine();
    }

    public void aceitarPedido() {
        System.out.println("\n==============================");
        System.out.println("   Lista de pedidos pendentes   ");
        System.out.println("==============================");
        System.out.println("Veja abaixo os pedidos que estão pendentes:\n");
        List<Pedido> pedidos = this.pedidos.buscarPedidosPendentes();

        if (pedidos.size() <= 0) {
            CLS();
            System.out.println("Não há pedidos pendentes.");
            return;
        }

        this.exibirPedidos(pedidos);

        System.out.print("\nDigite o índice do pedido que deseja aceitar ou 0 para sair: ");
        try {
            int value = this.readInt();
            if (value < 0 || value > pedidos.size()) {
                CLS();
                System.out.println("** Opção inválida ! **");
            } else if (value == 0) {
                CLS();
                return;
            } else {
                Pedido pedido = pedidos.get(value - 1);
                pedido.serAceito(autenticado);
                this.pedidos.atualizarPedido(pedido);
                CLS();
                System.out.println("Pedido aceito com sucesso!");
                return;
            }
        } catch (Exception e) {
            System.out.println("Aconteceu um erro!");
            System.err.println(e);
            CLS();
            System.out.println("** Opção inválida ! **");
            sc.nextLine();
        }
    }

    public void concluirPedido() {
        System.out.println("\n==============================");
        System.out.println("   Lista de pedidos em andamento   ");
        System.out.println("==============================");
        System.out.println("Veja abaixo os pedidos que estão em andamento:\n");
        List<Pedido> pedidos = this.pedidos.buscarPedidosPeloEntregador(autenticado.getPlacaDoVeiculo()).stream()
                .filter(p -> p.getSituacao() == SituacaoPedidoEnum.EM_ANDAMENTO).collect(Collectors.toList());

        if (pedidos.size() <= 0) {
            CLS();
            System.out.println("Não há pedidos em andamento.");
            return;
        }

        this.exibirPedidos(pedidos);

        System.out.print("\nDigite o índice do pedido que deseja concluir ou 0 para sair: ");
        try {
            int value = this.readInt();
            if (value < 0 || value > pedidos.size()) {
                CLS();
                System.out.println("** Opção inválida ! **");
            } else if (value == 0) {
                CLS();
                return;
            } else {
                Pedido pedido = pedidos.get(value - 1);
                pedido.setSituacao(SituacaoPedidoEnum.CONCLUIDO);
                this.pedidos.atualizarPedido(pedido);
                CLS();
                System.out.println("Pedido concluído com sucesso!");
                return;
            }
        } catch (Exception e) {
            System.out.println("Aconteceu um erro!");
            System.err.println(e);
            CLS();
            System.out.println("** Opção inválida ! **");
            sc.nextLine();
        }
    }

    public void devolverPedido() {
        System.out.println("\n==============================");
        System.out.println("   Lista de pedidos em andamento   ");
        System.out.println("==============================");
        System.out.println("Veja abaixo os pedidos que estão em andamento:\n");
        List<Pedido> pedidos = this.pedidos.buscarPedidosPeloEntregador(autenticado.getPlacaDoVeiculo()).stream()
                .filter(p -> p.getSituacao() == SituacaoPedidoEnum.EM_ANDAMENTO).collect(Collectors.toList());

        if (pedidos.size() <= 0) {
            CLS();
            System.out.println("Não há pedidos em andamento.");
            return;
        }

        this.exibirPedidos(pedidos);

        System.out.print("\nDigite o índice do pedido que deseja devolver ou 0 para sair: ");
        try {
            int value = this.readInt();
            if (value < 0 || value > pedidos.size()) {
                CLS();
                System.out.println("** Opção inválida ! **");
            } else if (value == 0) {
                CLS();
                return;
            } else {
                Pedido pedido = pedidos.get(value - 1);
                pedido.serDevolvido();
                this.pedidos.atualizarPedido(pedido);
                CLS();
                System.out.println("Pedido devolvido com sucesso!");
                return;
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