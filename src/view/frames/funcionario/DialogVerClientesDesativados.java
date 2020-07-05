package view.frames.funcionario;

import base.Cliente;
import base.Funcionario;
import repository.ClienteRepository;
import view.frames.funcionario.components.DialogClientes;

import javax.swing.*;

public class DialogVerClientesDesativados extends DialogClientes {
    public DialogVerClientesDesativados(Funcionario autenticado, JDialog parentFrame) {
        super(parentFrame, "Clientes desativados", ClienteRepository.getInstancia().getClienteRepositorio());
        tabela.getSelectionModel().addListSelectionListener(e -> {
            ClienteRepository repo = ClienteRepository.getInstancia();
            String nome = String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0));
            Cliente cliente = repo.buscarCliente(nome);
            cliente.setAtivo(true);
            dispose();
        });
    }
}
