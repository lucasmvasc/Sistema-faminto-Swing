package view.frames.entregador;

import base.Entregador;
import base.Pedido;
import base.SituacaoPedidoEnum;
import repository.PedidoRepository;

import java.util.stream.Collectors;

import javax.swing.*;

public class DialogDevolverPedido extends DialogPedidos {
    public DialogDevolverPedido(Entregador autenticado, JDialog parentFrame) {
        super(parentFrame, "Devolver Pedidos",
                PedidoRepository.getInstancia().buscarPedidosPeloEntregador(autenticado.getPlacaDoVeiculo()).stream().filter(
                    p -> p.getSituacao() == SituacaoPedidoEnum.EM_ANDAMENTO).collect(Collectors.toList()));
        tabela.getSelectionModel().addListSelectionListener(e -> {
            PedidoRepository repo = PedidoRepository.getInstancia();
            int cod = Integer.parseInt(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0)));
            Pedido p = repo.buscarPedidoEmAndamentoEntrega(autenticado, cod - 1);
            p.serDevolvido();
            repo.atualizarPedido(p);
            dispose();
        });
    }
}
