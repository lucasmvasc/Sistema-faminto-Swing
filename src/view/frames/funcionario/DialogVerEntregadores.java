package view.frames.funcionario;

import base.Funcionario;
import repository.EntregadorRepository;
import view.frames.funcionario.components.DialogEntregadores;

import javax.swing.*;

public class DialogVerEntregadores extends DialogEntregadores {
    public DialogVerEntregadores(Funcionario autenticado, JDialog parentFrame) {
        super(parentFrame, "Entregadores", EntregadorRepository.getInstancia().getEntregadores());
    }
}
