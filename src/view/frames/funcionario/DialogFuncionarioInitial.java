package view.frames.funcionario;

import java.awt.Frame;
import javax.swing.JDialog;
import javax.swing.JFrame;

import base.Cliente;
import base.Funcionario;
import view.components.IDialogTPreLogin;
import view.util.Misc;

@SuppressWarnings("serial")
public class DialogFuncionarioInitial extends IDialogTPreLogin<Funcionario> {

	public DialogFuncionarioInitial(Funcionario _item, Frame parentFrame, String title, int width, int height) {
		super(_item, parentFrame, title, width, height);
	}

	public DialogFuncionarioInitial(Funcionario _item, JDialog parentFrame, String title, int width, int height) {
		super(_item, parentFrame, title, width, height);
	}

	public void login(JDialog j) {
		new DialogFuncionarioLogin(new Funcionario(), j, "Login do Funcionário", "Digite o seu CPF",
				Misc.getScreenDimension().width - 800, Misc.getScreenDimension().height - 750);
	}

	public void cadastro(JDialog j) {
		new DialogFuncionarioCadastro(new Funcionario(), j, "Cadastro de Funcionário", 4,
				Misc.getScreenDimension().width - 800, Misc.getScreenDimension().height - 675);
	}
}