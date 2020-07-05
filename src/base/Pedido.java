package base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
    static int count;
    private final List<ItemPedido> itens = new ArrayList<>();
    private String id;
    private final String data;
    private Cliente cliente;
    private Entregador entregador;
    private SituacaoPedidoEnum situacao;

    public Pedido() {
        this.id = UUID.randomUUID().toString();
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.data = ldt.format(formatador);
        this.cliente = null;
        this.entregador = null;
        this.situacao = SituacaoPedidoEnum.PENDENTE;
    }

    public Pedido(Cliente cliente) {
        this();
        this.cliente = cliente;
    }

    public static String getSituacaoString(SituacaoPedidoEnum situacao) {
        switch (situacao) {
            case PENDENTE:
                return "Pendente";
            case EM_ANDAMENTO:
                return "Em andamento";
            case DEVOLVIDO:
                return "Devolvido";
            case CONCLUIDO:
                return "Concluído";
            case CANCELADO:
                return "Cancelado";
            default:
                return "N/A";
        }
    }

    private void adicionarItem(ItemPedido item) {
        itens.add(item);
    }

    public void adicionarItem(int qtde, Cardapio cardapio) throws Exception {
        ItemPedido item = new ItemPedido(qtde, cardapio);
        if (cardapio.getQtdEstoque() >= qtde) {
            cardapio.setQtdEstoque(cardapio.getQtdEstoque() - qtde);
            System.out.println("\n" + qtde + " unidade(s) de " + cardapio.getNome() + " adicionadas ao pedido");
            this.adicionarItem(item);
        } else {
            System.out.println("Não há essa quantidade de " + cardapio.getNome() + " disponível. Apenas "
                    + cardapio.getQtdEstoque() + " restante(s)");
            throw new Exception("Quantidade acima do normal");
        }


    }

    public void atualizarQuantidade(int qtde, int index, Cardapio item) throws Exception {
        if (item.getQtdEstoque() >= qtde) {
            int diferenca = itens.get(index).getQuantidade() - qtde;
            item.setQtdEstoque(item.getQtdEstoque() + diferenca);
            itens.get(index).setQuantidade(qtde);
            System.out.println("O item " + item.getNome() + " teve a quantidade atualizada!");
        } else {
            System.out.println("Não há essa quantidade de " + item.getNome() + " disponível. Apenas "
                    + item.getQtdEstoque() + " restante(s)");
            throw new Exception();
        }
        if (itens.get(index).getQuantidade() == 0) {
            this.delItem(index, item);
        }
    }

    public void delItem(int idx, Cardapio item) {
        item.setQtdEstoque(item.getQtdEstoque() + itens.get(idx).getQuantidade());
        itens.remove(itens.get(idx));
    }

    public void serAceito(Entregador entregador) {
        this.situacao = SituacaoPedidoEnum.EM_ANDAMENTO;
        this.entregador = entregador;
    }

    public void serDevolvido() {
        this.situacao = SituacaoPedidoEnum.DEVOLVIDO;
        for (int i = 0; i < this.itens.size(); i += 1) {
            Cardapio item = this.itens.get(i).getCardapio();
            item.setQtdEstoque(item.getQtdEstoque() + itens.get(i).getQuantidade());
        }
        this.cliente.setContadorBloqueio(this.cliente.getContadorBloqueio() + 1);
    }

    public void cancelarPedido() {
        this.situacao = SituacaoPedidoEnum.CANCELADO;
        for (int i = 0; i < this.itens.size(); i += 1) {
            Cardapio item = this.itens.get(i).getCardapio();
            item.setQtdEstoque(item.getQtdEstoque() + itens.get(i).getQuantidade());
        }
    }

    public void printItens() {
        for (int i = 0; i < this.itens.size(); i += 1) {
            ItemPedido item = this.itens.get(i);

            System.out.printf("%d. %s  R$ %.2f\n", i + 1, item.toString(),
                    item.getCardapio().getPreco() * item.getQuantidade());
        }
    }

    public void printItensResumo() {
        count = 0;
        System.out.println(" | Itens: ");
        for (int i = 0; i < this.itens.size(); i += 1) {
            ItemPedido item = this.itens.get(i);
            System.out.print("    | ");
            System.out.print(item);
            System.out.println(String.format(" - R$ %.2f", item.getCardapio().getPreco() * item.getQuantidade()));
        }
    }

    public double getValorTotal() {
        double total = 0;
        for (ItemPedido item : this.itens) {
            total += item.getQuantidade() * item.getCardapio().getPreco();
        }
        return total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SituacaoPedidoEnum getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoPedidoEnum situacao) {
        this.situacao = situacao;
    }

    public Cardapio getCardapioIndex(int idx) {
        return itens.get(idx).getCardapio();
    }

    public String getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public List<ItemPedido> getItensPedidos() {
        return itens;
    }

    @Override
    public String toString() {
        return "Situação: " + situacao + "\n | Data: " + data + "\n | Cliente: " + "[" + cliente + "]"
                + "\n | Entregador: [" + this.getEntregador() + "]" + "\n | Forma de pagamento: "
                + (cliente.getCartao() == null ? cliente.getCheque() : cliente.getCartao()) + "\n | Valor: R$ "
                + String.format("%.2f", this.getValorTotal());
    }
}
