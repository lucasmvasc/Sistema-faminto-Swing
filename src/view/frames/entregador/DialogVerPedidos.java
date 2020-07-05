package view.frames.entregador;

import base.Entregador;
import repository.PedidoRepository;

import javax.swing.*;

public class DialogVerPedidos extends DialogPedidos {
    public DialogVerPedidos(Entregador autenticado, JDialog parentFrame) {
        super(parentFrame, "Ver pedidos do dia",
                PedidoRepository.getInstancia()
                        .buscarPedidosPeloEntregador(autenticado.getPlacaDoVeiculo()));
    }
}
