package view;

import base.*;
import repository.CardapioRepository;
import repository.ClienteRepository;
import repository.PedidoRepository;
import view.console.EntregadorView;
import view.console.FuncionarioView;
import view.console.ViewBase;

import java.util.Scanner;

public class Console extends ViewBase {
    private final CardapioRepository cardapio;
    private final EntregadorView entregadorView;
    private final FuncionarioView funcionarioView;

    private Cliente autenticado;

    public Console() {
        super(new Scanner(System.in));
        cardapio = CardapioRepository.getInstancia();
        entregadorView = new EntregadorView(this.sc);
        funcionarioView = new FuncionarioView(this.sc, entregadorView);
    }

    public void run() {
        preLogin();
    }

    void preLogin() {
        System.out.println("\n==============================");
        System.out.println("       	  Pré-login !         ");
        System.out.println("==============================");
        System.out.println("1. Cliente");
        System.out.println("2. Funcionário");
        System.out.println("3. Entregador");
        System.out.println("0. Fechar aplicação");
        System.out.println("------------------------------");
        System.out.print("\nEscolha uma opção: ");
        try {
            int value = this.readInt();
            this.CLS();
            switch (value) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.print("Tem cadastro no sistema? (s/n):  ");
                    String confirmacao = sc.nextLine();
                    if (confirmacao.equals("n")) {
                        this.cadastroCliente();
                    } else if (confirmacao.equals("s")) {
                        this.loginCliente();
                    } else {
                        this.preLogin();
                    }
                    break;
                case 2:
                    this.funcionarioView.menuInicial();
                    this.preLogin();
                    break;
                case 3:
                    this.entregadorView.menuInicial();
                    this.preLogin();
                    break;
                default:
                    System.out.println("** Opção inválida ! **");
                    this.preLogin();
            }
        } catch (Exception e) {
            sc.nextLine();
            this.CLS();
            this.preLogin();
        }
    }

    void loginCliente() {
        System.out.println("\n==============================");
        System.out.println("        Login - cliente       ");
        System.out.println("==============================");
        System.out.print("Id de login: ");
        String id = sc.nextLine();
        this.autenticado = ClienteRepository.getInstancia().buscarClientePeloId(id);
        if (this.autenticado == null) {
            System.out.println("Não foi encontrado cliente com esse ID.");
            this.preLogin();
        } else {
            CLS();
            System.out.println("Seja bem-vindo, " + this.autenticado.getNome() + "!");
            this.menuPrincipalCliente(this.autenticado, this.autenticado.getPedidoUnico());
        }
    }

    void cadastroCliente() {
        System.out.println("\n==============================");
        System.out.println("       	  Bem-vindo !         ");
        System.out.println("==============================");
        System.out.println("Faça o seu cadastro agora mesmo e começe a pedir!");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Endereço: ");
        String end = sc.nextLine();
        System.out.print("Telefone: ");
        String tel = sc.nextLine();
        System.out.print("Referência: ");
        String ref = sc.nextLine();

        System.out.println("--------------------");
        System.out.println(" Forma de pagamento ");
        System.out.println("--------------------");
        System.out.println("Digite 'ch' para cheque ou 'ca' para cartão: ");
        String pgto = sc.nextLine();

        switch (pgto) {
            case "ch":
                System.out.println("\nInsira os dados do seu cheque!");
                System.out.print("Número do cheque: ");
                String numCheque = sc.nextLine();
                System.out.print("Conta: ");
                String numConta = sc.nextLine();
                System.out.print("Agência: ");
                String numAgencia = sc.nextLine();
                System.out.print("Banco: ");
                String numBanco = sc.nextLine();
                Cheque ch = new Cheque(numCheque, numConta, numAgencia, numBanco);
                Cliente c1 = new Cliente(nome, end, tel, ref);
                ClienteRepository.getInstancia().adicionarCliente(c1);
                c1.setCheque(ch);
                this.CLS();
                System.out.println("<< Cadastro Concluído >>");
                this.menuPrincipalCliente(c1, null);
                break;
            case "ca":
                System.out.println("\nInsira os dados do seu cartão!");
                System.out.print("Número do cartão: ");
                String numCartao = sc.nextLine();
                System.out.print("CVV: ");
                String numCvv = sc.nextLine();
                Cartao ca = new Cartao(numCartao, numCvv);
                Cliente c2 = new Cliente(nome, end, tel, ref);
                ClienteRepository.getInstancia().adicionarCliente(c2);
                c2.setCartao(ca);
                this.CLS();
                System.out.println("<< Cadastro Concluído >>");
                this.menuPrincipalCliente(c2, null);
                break;
            default:
                System.out.println("\nDígito não reconhecido, operação cancelada");
                this.cadastroCliente();
        }
    }

    void menuPrincipalCliente(Cliente c, Pedido... p) {
        if (p != null) {
            if (p[0].getSituacao() == SituacaoPedidoEnum.CONCLUIDO || p[0].getSituacao() == SituacaoPedidoEnum.DEVOLVIDO) {
                this.CLS();
                this.menuPrincipalCliente(c, null);
            }
        }
        c.checkClienteStatus();
        c.setPedidoUnico(p);
        // APAGAR DEPOIS
        ClienteRepository.getInstancia().printCliente();
        PedidoRepository.getInstancia().printPedidos();
        System.out.println(c.getPedidoUnico());

        System.out.println("\n==============================");
        System.out.println("        Sistema Faminto        ");
        System.out.println("==============================");
        System.out.println("1. Fazer pedido");
        System.out.println("2. Dados cadastrais");
        System.out.println("3. Cancelar pedido");
        System.out.println("4. Alterar pedido");
        System.out.println("0. Logout");
        System.out.println("------------------------------");
        System.out.print("\nEscolha uma opção: ");
        try {
            int value = this.readInt();
            switch (value) {
                case 0:
                    this.CLS();
                    this.preLogin();
                case 1:
                    this.CLS();
                    if (c.getAtivo()) {
                        if (p == null) {
                            this.fazerPedido(c);
                        } else {
                            System.out.println("** Há um pedido pendentes em andamento ! **");
                            this.menuPrincipalCliente(c, p);
                        }
                    } else {
                        System.out.println("Prezado cliente sua conta foi desativada devido ao\n"
                                + "excesso de devoluções no seu endereço. \nPor favor, entre"
                                + " em contato com o administrador para desbloqueio");
                        this.menuPrincipalCliente(c, p);
                    }
                case 2:
                    this.CLS();
                    this.dadosCadastro(c);
                case 3:
                    if (p != null) {
                        if (p[0].getSituacao() == SituacaoPedidoEnum.PENDENTE) {
                            p[0].cancelarPedido();
                            this.CLS();
                            System.out.println("<< Pedido cancelado com sucesso ! >>");
                            this.menuPrincipalCliente(c, null);
                        }
                    } else {
                        this.CLS();
                        System.out.println("** Não há pedidos pendentes em aberto ! **");
                        this.menuPrincipalCliente(c, p);
                    }
                case 4:
                    if (p != null) {
                        if (p[0].getSituacao() == SituacaoPedidoEnum.PENDENTE) {
                            this.CLS();
                            this.alterarPedido(c, p);
                        }
                    } else {
                        this.CLS();
                        System.out.println("** Não há pedidos em aberto ! **");
                        this.menuPrincipalCliente(c, p);
                    }
                default:
                    this.CLS();
                    System.out.println("** Opção inválida ! **");
                    this.menuPrincipalCliente(c, c.getPedidoUnico());
            }
        } catch (Exception e) {
            sc.next();
            this.CLS();
            System.out.println("** Opção inválida ! **");
            this.menuPrincipalCliente(c, p);
        }
    }

    void fazerPedido(Cliente c) {
        System.out.println("\n========================================");
        System.out.println("           Monte o seu pedido           ");
        System.out.println("========================================");
        System.out.println("ID | Tipo | Nome | Valor | Qntd. estoque\n");
        CardapioRepository.getInstancia().printCardapio();
        System.out.println("-------------------------------------------------------------");
        System.out.println("Faça o pedido logo abaixo (pressione 0 para retornar ao menu)\n");
        Pedido pedido = new Pedido(c);
        while (true) {
            if (!pedido.getItensPedidos().isEmpty()) {
                System.out.print("\nDeseja finalizar o pedido? (s/n): ");
                String confirmacao = sc.nextLine();
                switch (confirmacao) {
                    case "s":
                        pedido.setSituacao(SituacaoPedidoEnum.PENDENTE);
                        this.CLS();
                        System.out.printf("<< Pedido finalizado. Total = R$ %.2f >>\n", pedido.getValorTotal());
                        PedidoRepository.getInstancia().adicionarPedido(pedido);
                        this.menuPrincipalCliente(c, pedido);
                    case "n":
                        break;
                    default:
                        System.out.println(
                                "\n** Caractere inválido, pedido em continuidade (pressione 0 para cancelar tudo) **\n");
                }
            }
            try {
                System.out.print("Código do produto: ");
                String produto = sc.nextLine();
                if (produto.contentEquals("0")) {
                    this.CLS();
                    this.menuPrincipalCliente(c, null);
                }
                Integer produtoNum = Integer.parseInt(produto);
                System.out.print("Quantidade: ");
                String qntd = sc.nextLine();
                Integer qntdNum = Integer.parseInt(qntd);
                pedido.adicionarItem(qntdNum, CardapioRepository.getInstancia().buscarItem(produtoNum - 1));

            } catch (Exception e) {
                System.out
                        .println("\n** Dígito inserido inválido ou produto em falta. Por favor, tente novamente. **\n");
            } finally {
            }
        }
    }

    void dadosCadastro(Cliente c) {
        System.out.println("\n==============================");
        System.out.println("    Informações cadastrais    ");
        System.out.println("==============================");
        System.out.println("Nome: " + c.getNome());
        System.out.println("Endereço: " + c.getEndereco());
        System.out.println("Referência: " + c.getReferencia());
        System.out.println("Telefone: " + c.getTelefone());
        System.out.println("Seu ID de login: " + c.getID() + "\n");
        System.out.println(
                "Info de pagamento: " + (c.getCartao() == null ? c.getCheque().toString() : c.getCartao().toString()));
        System.out.println("-----------------------------------");
        System.out.print("Pressione 0 para retornar ao menu: ");
        try {
            int value = this.readInt();
            this.CLS();
            switch (value) {
                case 0:
                    this.menuPrincipalCliente(c, c.getPedidoUnico());
                default:
                    this.CLS();
                    System.out.println("** Opção inválida ! **");
                    this.dadosCadastro(c);
            }
        } catch (Exception e) {
            sc.nextLine();
            this.CLS();
            System.out.println("** Opção inválida ! **");
            this.dadosCadastro(c);
        }
    }

    void alterarPedido(Cliente c, Pedido... p) {
        System.out.println("\n==============================");
        System.out.println("         Alterar Pedido         ");
        System.out.println("==============================");
        System.out.println("Dia: " + p[0].getData().substring(0, 10));
        System.out.println("Horário do pedido: " + p[0].getData().substring(11, 19));
        System.out.println("Status: " + Pedido.getSituacaoString(p[0].getSituacao()));
        System.out.println("\n------------------------------------------------------------------");
        System.out.println("ID | Tipo | Nome | Valor | Qntd. estoque | Qntd. pedida | Subtotal\n");
        p[0].printItens();
        System.out.printf("\nTotal: R$ %.2f\n", p[0].getValorTotal());
        System.out.println("-----------------------------");
        System.out.println("            Opções           ");
        System.out.println("-----------------------------");
        System.out.println("1. Alterar quantidade");
        System.out.println("2. Remover item");
        System.out.println("3. Adicionar item");
        System.out.println("-----------------------------------------------------");
        System.out.print("\nEscolha uma opção (pressione 0 para retornar ao menu): ");
        try {
            int value = this.readInt();
            switch (value) {
                case 0:
                    this.CLS();
                    this.menuPrincipalCliente(c, p);
                case 1:
                    System.out.print("\nCódigo do produto: ");
                    int cod = this.readInt();
                    System.out.print("Nova quantidade: ");
                    int qntd = this.readInt();
                    this.CLS();
                    if (cod < 1 || cod > p[0].getItensPedidos().size()) {
                        System.out.println("** Código de produto inexistente no pedido atual ! **");
                        this.alterarPedido(c, p);
                    }
                    p[0].atualizarQuantidade(qntd, cod - 1, p[0].getCardapioIndex(cod - 1));
                    this.alterarPedido(c, p);
                case 2:
                    System.out.print("Código do produto: ");
                    int cod2 = this.readInt();
                    this.CLS();
                    if (cod2 < 1 || cod2 > p[0].getItensPedidos().size()) {
                        System.out.println("** Código de produto inexistente no pedido atual ! **");
                        this.alterarPedido(c, p);
                    }
                    System.out.println(
                            "O item " + p[0].getCardapioIndex(cod2 - 1).getNome() + " foi removido com sucesso!");
                    p[0].delItem(cod2 - 1, p[0].getCardapioIndex(cod2 - 1));
                    if (p[0].getItensPedidos().isEmpty()) {
                        System.out.println("<< O último item do pedido foi excluido do pedido. Pedido excluido >>");
                        p[0].cancelarPedido();
                        this.menuPrincipalCliente(c, null);
                    }
                    this.alterarPedido(c, p);
                case 3:
                    System.out.println("\n========================================");
                    System.out.println("           Monte o seu pedido           ");
                    System.out.println("========================================");
                    System.out.println("ID | Tipo | Nome | Valor | Qntd. estoque\n");
                    CardapioRepository.getInstancia().printCardapio();
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Faça o pedido logo abaixo (pressione 0 para retornar ao menu anterior)\n");
                    try {
                        System.out.print("Código do produto: ");
                        String cod3 = sc.nextLine();
                        if (cod3.contentEquals("0")) {
                            this.CLS();
                            this.alterarPedido(c, p);
                        }
                        Integer codNum = Integer.parseInt(cod3);
                        System.out.print("Quantidade: ");
                        String qtd = sc.nextLine();
                        Integer qtdNum = Integer.parseInt(qtd);
                        this.CLS();
                        p[0].adicionarItem(qtdNum, CardapioRepository.getInstancia().buscarItem(codNum - 1));
                    } catch (Exception e) {
                        this.CLS();
                        System.out.println(
                                "** Dígito inserido inválido ou produto em falta. Por favor, tente novamente. **");
                    } finally {
                    }
                    this.alterarPedido(c, p);
                default:
                    this.CLS();
                    System.out.println("** Opção inválida ! **");
                    this.alterarPedido(c, p);
            }
        } catch (Exception e) {
            sc.nextLine();
            System.out.println(e);
            this.CLS();
            System.out.println("** Opção inválida ! **");
            this.alterarPedido(c, p);
        }
    }
}