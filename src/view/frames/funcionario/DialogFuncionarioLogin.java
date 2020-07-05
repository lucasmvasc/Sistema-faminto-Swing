package view.frames.funcionario;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import base.Funcionario;
import repository.FuncionarioRepository;
import view.components.IDialogTLogin;
import view.util.Misc;

@SuppressWarnings("serial")
public class DialogFuncionarioLogin extends IDialogTLogin<Funcionario> {
    private Funcionario autenticado;

    public DialogFuncionarioLogin(Funcionario _item, Frame parentFrame, String title, String campoLogin, int width,
            int height) {
        super(_item, parentFrame, title, campoLogin, width, height);
    }

    public DialogFuncionarioLogin(Funcionario _item, JDialog parentFrame, String title, String campoLogin, int width,
            int height) {
        super(_item, parentFrame, title, campoLogin, width, height);
    }

    public void entrar(JDialog j) {
        FuncionarioRepository funcionarios = FuncionarioRepository.getInstancia();
        this.autenticado = funcionarios.buscarFuncionarioPeloCpf(loginPanel.txtFiels.get("Digite o seu CPF").getText());
        if (this.autenticado == null) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Atenção", JOptionPane.ERROR_MESSAGE);
        } else {
            new DialogFuncionarioMenuPrincipal(j, "Menu principal do Funcionário - " + this.autenticado.getNome(),
                    Misc.getScreenDimension().width - 600, Misc.getScreenDimension().height - 600, this.autenticado);
        }
    }
}