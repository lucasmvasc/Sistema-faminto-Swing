package view.frames.funcionario.components;

import base.Pedido;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DialogPedidosFuncionario extends JDialog {
    protected JPanel painelFundo;
    protected JTable tabela;
    protected JScrollPane barraRolagem;
    protected String[] colunas = { "Código", "Cliente", "Entregador", "Forma de Pagamento", "Valor", "Itens pedidos",
            "Situação" };
    protected List<Pedido> pedidos;

    public DialogPedidosFuncionario(JDialog parentFrame, String title, java.util.List<Pedido> pedidos) {
        super(parentFrame, title);
        this.pedidos = pedidos;
        this.arrangeLayout();
    }

    private void carregarTabela() {
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));

        List<String[]> pedidosList = new ArrayList<>();
        for (Pedido p : this.pedidos) {
            String itens = "";
            String formaDePagamento = p.getCliente().getCartao() == null
                    ? "Cheque - " + p.getCliente().getCheque().getNumero()
                    : "Cartão - " + p.getCliente().getCartao().getNumero();
            for (int i = 0; i < p.getItensPedidos().size(); i++) {
                itens = itens + p.getItensPedidos().get(i).getCardapio().getNome() + " ";
            }
            String[] strings = new String[] { p.getId(), p.getCliente().getNome(), p.getEntregador() == null ? "Não alocado" : p.getEntregador().getNome(),
                    formaDePagamento, String.format("%.2f", p.getValorTotal()), itens,
                    String.valueOf(p.getSituacao()) };
            pedidosList.add(strings);
        }
        Object[][] tableData = new Object[pedidosList.size()][7];
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
        setSize(dim.width - 300, dim.height - 800);
        setVisible(true);
    }
}
