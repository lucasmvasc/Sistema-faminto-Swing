package view.frames.cliente.components;

import base.Cardapio;
import base.Cliente;
import base.ItemPedido;
import repository.CardapioRepository;
import repository.PedidoRepository;
import view.util.Misc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class AlterarPedidosPanel extends JDialog {
    protected JPanel painelFundo1 = new JPanel(new GridLayout(1, 1));
    protected JTable tabela;
    protected JScrollPane barraRolagem;
    protected JPanel acoesPanelCenter = new JPanel();
    protected String[] colunasPedido = { "Código", "Nome", "Valor", "Quantidade pedida", "Subtotal"};
    protected List<String[]> itemPedidoList = new ArrayList<>();
    protected PedidoRepository pedidos = PedidoRepository.getInstancia();
    protected CardapioRepository cardapio = CardapioRepository.getInstancia();
    protected JButton btnAdd = new JButton(" Adicionar item");
    protected JComboBox<String> comboboxPedido;
    protected JTextField campoQntdAlt;
    protected JLabel cardapioLabel = new JLabel("SEU PEDIDO ATUAL", SwingConstants.CENTER);
    protected JLabel qntdLabel = new JLabel("Qntd: ");
    protected JLabel totalLabel;
    protected List<ItemPedido> pedidoDoCliente;
    protected Cliente mainClient;
    protected JPanel panel = new JPanel();
    protected JTextField textField;

    public AlterarPedidosPanel(JDialog parentFrame, String title, Cliente c) {
        super(parentFrame, title);
        this.mainClient = c;
        this.pedidoDoCliente = c.getPedidoUnico()[0].getItensPedidos();
        totalLabel = new JLabel("TOTAL : " + String.format("%.2f", c.getPedidoUnico()[0].getValorTotal()));
        this.events();
        this.configJPanel();
        this.arrangeLayout();
    }

    private void configJPanel(){
        panel.add(new JLabel("Nova quantidade: "));
        textField = new JTextField(3);
        panel.add(textField);
    }

    private void carregarPedidos() {
        int contador = 0;
        for (ItemPedido itemDoPedido : pedidoDoCliente){
            contador++;
            String[] strings = new String[] { String.format("%d", contador), itemDoPedido.getCardapio().getNome(),
                    String.format("%.2f", itemDoPedido.getCardapio().getPreco()),
                    String.format("%d", itemDoPedido.getQuantidade()),
                    String.format("%.2f", (itemDoPedido.getQuantidade() * itemDoPedido.getCardapio().getPreco()))};
            itemPedidoList.add(strings);
        }
        Object[][] tableData = new Object[itemPedidoList.size()][5];
        for (int i = 0; i < itemPedidoList.size(); i++) {
            tableData[i] = itemPedidoList.get(i);
        }
        tabela = new JTable(tableData, colunasPedido);
        tabela.setDefaultEditor(Object.class, null);
        barraRolagem = new JScrollPane(tabela);
        this.add(cardapioLabel, BorderLayout.NORTH);
        painelFundo1.add(barraRolagem);
        this.add(painelFundo1, BorderLayout.CENTER);
    }

    private void carregarBotao() {
        comboboxPedido = new JComboBox<>();
        for (Cardapio itemDoPedido : this.cardapio.getCardapioRepositorio()) {
            comboboxPedido.addItem(itemDoPedido.getNome());
        }
        campoQntdAlt = new JTextField(3);
        acoesPanelCenter.add(btnAdd);
        acoesPanelCenter.add(comboboxPedido);
        acoesPanelCenter.add(qntdLabel);
        acoesPanelCenter.add(campoQntdAlt);
        acoesPanelCenter.add(totalLabel);
        this.add(acoesPanelCenter, BorderLayout.SOUTH);
    }

    private void arrangeLayout() {
        carregarPedidos();
        carregarBotao();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Misc.getScreenDimension();
        setSize(dim.width - 800, dim.height - 700);
        setVisible(true);
    }

    @SuppressWarnings("unused")
    protected void events() {
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItemPedidoAlt();

            }
        });
	}
    protected abstract void adicionarItemPedidoAlt();
}
