package view.frames.entregador;

import base.Entregador;
import base.Pedido;
import base.SituacaoPedidoEnum;
import repository.PedidoRepository;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class DialogComissao extends JDialog {
    protected JPanel painelFundo;
    protected JTable tabela;
    protected JScrollPane barraRolagem;
    protected String[] colunas = {"# Entregas", "V. Total", "Comissão (5% v. total)"};
    Entregador autenticado;

    public DialogComissao(Entregador autenticado, JDialog parentFrame) {
        super(parentFrame, "Comissão");
        this.autenticado = autenticado;
        this.arrangeLayout();
    }

    private void carregarTabela() {
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));

        java.util.List<Pedido> pedidos = PedidoRepository.getInstancia()
                .buscarPedidosPeloEntregador(this.autenticado.getPlacaDoVeiculo())
                .stream()
                .filter(p -> p.getSituacao() == SituacaoPedidoEnum.CONCLUIDO).collect(Collectors.toList());

        Object[][] tableData;
        if (pedidos.size() <= 0) {
            tableData = new Object[][]{{"0", "0", "0"}};
        } else {
            System.out.println("Qtde de entregas | Valor total | Comissão (5% do valor total)");
            double total = 0;
            for (int i = 0; i < pedidos.size(); i += 1) {
                Pedido pedido = pedidos.get(i);
                total += pedido.getValorTotal();
            }
            tableData = new Object[][]{
                    {
                            String.valueOf(pedidos.size()),
                            String.format("R$ %.2f", total),
                            String.format("R$ %.2f", total * (5.0 / 100.0))
                    }
            };
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
