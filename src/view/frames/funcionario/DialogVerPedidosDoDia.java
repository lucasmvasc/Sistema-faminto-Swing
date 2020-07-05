package view.frames.funcionario;

import base.Funcionario;
import repository.PedidoRepository;
import view.frames.funcionario.components.DialogPedidosFuncionario;

import javax.swing.*;

public class DialogVerPedidosDoDia extends DialogPedidosFuncionario {
    public DialogVerPedidosDoDia(Funcionario autenticado, JDialog parentFrame) {
        super(parentFrame, "Pedidos do dia", PedidoRepository.getInstancia().getPedidos());
    }
}
