package view.frames.cliente;

import base.Cliente;
import view.components.IDialogTPreLogin;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class DialogClienteInitial extends IDialogTPreLogin<Cliente> {

	public DialogClienteInitial(Cliente _item, Frame parentFrame, String title, int width, int height) {
		super(_item, parentFrame, title, width, height);
	}

	public DialogClienteInitial(Cliente _item, JDialog parentFrame, String title, int width, int height) {
		super(_item, parentFrame, title, width, height);
	}

	public void login(JDialog j) {
		new DialogClienteLogin(new Cliente(), j, "Login do Cliente", "Digite o seu ID",
				Misc.getScreenDimension().width - 800, Misc.getScreenDimension().height - 700);
	}

	public void cadastro(JDialog j) {
		new DialogClienteCadastro(new Cliente(), j, "Cadastro de Cliente", 12, Misc.getScreenDimension().width - 800,
				Misc.getScreenDimension().height - 450);
	}
}