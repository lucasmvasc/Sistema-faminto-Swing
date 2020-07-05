package view.frames.entregador;

import base.Pedido;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DialogPedidos extends JDialog {
    protected JPanel painelFundo;
    protected JTable tabela;
    protected JScrollPane barraRolagem;
    protected String[] colunas = {"Código", "Cliente", "Endereço", "Valor", "Situação"};
    protected List<Pedido> pedidos;

    public DialogPedidos(JDialog parentFrame, String title, java.util.List<Pedido> pedidos) {
        super(parentFrame, title);
        this.pedidos = pedidos;
        this.arrangeLayout();
    }

    private void carregarTabela() {
        int contador = 0;
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));

        List<String[]> pedidosList = new ArrayList<>();
        for (Pedido p : this.pedidos) {
            contador++;
            String[] strings = new String[]{
                    String.format("%d", contador), p.getCliente().getNome(), p.getCliente().getEndereco(),
                    String.format("%.2f", p.getValorTotal()), String.valueOf(p.getSituacao())
            };
            pedidosList.add(strings);
        }
        Object[][] tableData = new Object[pedidosList.size()][5];
        for (int i = 0; i < pedidosList.size(); i++) {
            tableData[i] = pedidosList.get(i);
        }
        tabela = new JTable(tableData, colunas);
        tabela.setDefaultEditor(Object.class, null);
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem);
        getContentPane().add(painelFundo);
    }

    private void arrangeLayout() {
        carregarTabela();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Misc.getScreenDimension();
        setSize(dim.width - 800, dim.height - 800);
        setVisible(true);
    }
}
