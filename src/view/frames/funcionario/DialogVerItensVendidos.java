package view.frames.funcionario;

import base.Funcionario;
import repository.CardapioRepository;
import view.frames.funcionario.components.DialogItens;

import javax.swing.*;

public class DialogVerItensVendidos extends DialogItens {
    public DialogVerItensVendidos(Funcionario autenticado, JDialog parentFrame) {
        super(parentFrame, "Itens vendidos", CardapioRepository.getInstancia().getCardapioRepositorio());
    }
}
