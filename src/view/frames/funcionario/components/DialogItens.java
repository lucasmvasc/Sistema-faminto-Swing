package view.frames.funcionario.components;

import base.Cardapio;
import base.ItemPedido;
import base.Pedido;
import base.SituacaoPedidoEnum;
import repository.CardapioRepository;
import repository.PedidoRepository;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DialogItens extends JDialog {
    protected JPanel painelFundo;
    protected JTable tabela;
    protected JScrollPane barraRolagem;
    protected String[] colunas = { "Tipo", "Produto", "Valor", "Estoque", "Vendidos no dia", "Pico de procura" };
    protected List<Cardapio> itens;

    public DialogItens(JDialog parentFrame, String title, java.util.List<Cardapio> itens) {
        super(parentFrame, title);
        this.itens = itens;
        this.arrangeLayout();
    }

    public void setPicosDeProcura() {
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
    }

    private void carregarTabela() {
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
        List<String[]> itensList = new ArrayList<>();
        for (Cardapio p : this.itens) {
            if (p.getEstoqueInicial() != p.getQtdEstoque()) {
                String[] strings = new String[] { p.getClass().getSimpleName(), p.getNome(),
                        String.format("%.2f", p.getPreco()), String.format("%d", p.getQtdEstoque()),
                        String.format("%d", p.getEstoqueInicial() - p.getQtdEstoque()),
                        p.isPicoDeProcura() == true ? "SIM" : "-" };
                itensList.add(strings);
            }
        }
        Object[][] tableData = new Object[itensList.size()][6];
        for (int i = 0; i < itensList.size(); i++) {
            tableData[i] = itensList.get(i);
        }
        tabela = new JTable(tableData, colunas);
        tabela.setDefaultEditor(Object.class, null);
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem);
        getContentPane().add(painelFundo);
    }

    private void arrangeLayout() {
        setPicosDeProcura();
        carregarTabela();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Misc.getScreenDimension();
        setSize(dim.width - 800, dim.height - 800);
        setVisible(true);
    }
}
