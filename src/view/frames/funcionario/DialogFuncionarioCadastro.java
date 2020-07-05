package view.frames.funcionario;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import base.Funcionario;
import repository.FuncionarioRepository;
import view.components.IDialogTCadastro;
import view.util.Misc;

@SuppressWarnings("serial")
public class DialogFuncionarioCadastro extends IDialogTCadastro<Funcionario> {

    public DialogFuncionarioCadastro(Funcionario _item, Frame parentFrame, String title, int numCol, int width,
            int height) {
        super(_item, parentFrame, title, numCol, width, height);
    }

    public DialogFuncionarioCadastro(Funcionario _item, JDialog parentFrame, String title, int numCol, int width,
            int height) {
        super(_item, parentFrame, title, numCol, width, height);
    }

    public void confirmaCadastro(JDialog j) {
        final FuncionarioRepository funcionarios = FuncionarioRepository.getInstancia();
        try {
            Funcionario f = new Funcionario(cadastroPanel.txtFiels.get("nome").getText(),
                    cadastroPanel.txtFiels.get("cpf").getText());
            funcionarios.adicionarFuncionario(f);
            System.out.println(f.getCpf() + " - " + f.getNome());
            new DialogFuncionarioMenuPrincipal(j, "Menu principal do Funcionário - " + f.getNome(),
                    Misc.getScreenDimension().width - 600, Misc.getScreenDimension().height - 600, f);

        } catch (Exception e) {
        }
    }
}