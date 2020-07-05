package view.frames.cliente.components;

import base.Cardapio;
import repository.CardapioRepository;
import view.util.Misc;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class FazerPedidosPanel extends JDialog {
    protected JPanel painelFundo1 = new JPanel(new GridLayout(1, 1));
    protected JTable tabela;
    protected JScrollPane barraRolagem;
    protected JPanel acoesPanelCenter = new JPanel();
    protected String[] colunasCardapio = { "Código", "Nome", "Valor", "Quantidade em estoque" };
    protected List<String[]> cardapioList = new ArrayList<>();
    protected CardapioRepository cardapio = CardapioRepository.getInstancia();
    protected JButton btnAdd = new JButton(" Adicionar item");
    protected JButton btnFinish = new JButton(" Finalizar pedido");
    protected JComboBox<String> comboboxEscolhaPedido;
    protected JTextField campoQntd;
    protected JLabel cardapioLabel = new JLabel("CARDÁPIO",SwingConstants.CENTER);
    protected JLabel qntdLabel = new JLabel("Qntd: ");

    public FazerPedidosPanel(JDialog parentFrame, String title) {
        super(parentFrame, title);
        this.events();
        this.arrangeLayout();

    }

    private void carregarCardapio() {
        int contador = 0;
        for (Cardapio itemDoCardapio : this.cardapio.getCardapioRepositorio()) {
            contador++;
            String[] strings = new String[] { String.format("%d", contador), itemDoCardapio.getNome(),
                    String.format("%.2f", itemDoCardapio.getPreco()),
                    String.format("%d", itemDoCardapio.getQtdEstoque()) };
            cardapioList.add(strings);
        }
        Object[][] tableData = new Object[cardapioList.size()][4];
        for (int i = 0; i < cardapioList.size(); i++) {
            tableData[i] = cardapioList.get(i);
        }

        tabela = new JTable(tableData, colunasCardapio);
        tabela.setDefaultEditor(Object.class, null);
        barraRolagem = new JScrollPane(tabela);
        this.add(cardapioLabel, BorderLayout.NORTH);
        painelFundo1.add(barraRolagem);
        this.add(painelFundo1, BorderLayout.CENTER);
    }

    private void carregarBotao() {
        comboboxEscolhaPedido = new JComboBox<>();
        for (Cardapio itemDoCardapio : this.cardapio.getCardapioRepositorio()) {
            comboboxEscolhaPedido.addItem(itemDoCardapio.getNome());
        }
        
        campoQntd = new JTextField(3);
        acoesPanelCenter.add(btnFinish);
        acoesPanelCenter.add(btnAdd);
        acoesPanelCenter.add(comboboxEscolhaPedido);
        acoesPanelCenter.add(qntdLabel);
        acoesPanelCenter.add(campoQntd);
        this.add(acoesPanelCenter, BorderLayout.SOUTH);
    }

    private void arrangeLayout() {
        carregarCardapio();
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
                adicionarItemPedido();

            }
        });
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarPedido();

            }
		});
	}
    protected abstract void adicionarItemPedido();
    protected abstract void finalizarPedido();
}
