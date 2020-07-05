package view.frames.entregador;

import base.Entregador;
import base.Pedido;
import repository.PedidoRepository;

import javax.swing.*;

public class DialogAceitarPedidos extends DialogPedidos {
    public DialogAceitarPedidos(Entregador autenticado, JDialog parentFrame) {
        super(parentFrame, "Aceitar Pedidos",
                PedidoRepository.getInstancia().buscarPedidosPendentes());
        tabela.getSelectionModel().addListSelectionListener(e -> {
            PedidoRepository repo = PedidoRepository.getInstancia();
            int cod = Integer.parseInt(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0)));
            Pedido p = repo.buscarPedidoPendente(cod - 1);
            p.serAceito(autenticado);
            repo.atualizarPedido(p);
            dispose();
        });
    }
}
