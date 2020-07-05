package view.frames.cliente;

import base.Cartao;
import base.Cheque;
import base.Cliente;
import repository.ClienteRepository;
import view.components.IDialogTCadastro;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class DialogClienteCadastro extends IDialogTCadastro<Cliente> {
	public DialogClienteCadastro(Cliente _item, JDialog parentFrame, String title, int numCol, int width, int height) {
		super(_item, parentFrame, title, numCol, width, height);
	}

	public void confirmaCadastro(JDialog j) {
		final ClienteRepository clientes = ClienteRepository.getInstancia();
		try {
			Cliente c = new Cliente(cadastroPanel.txtFiels.get("nome").getText(),
					cadastroPanel.txtFiels.get("endereco").getText(), cadastroPanel.txtFiels.get("telefone").getText(),
					cadastroPanel.txtFiels.get("referencia").getText());
			if (cadastroPanel.txtFiels.get("cheque").getText().length() > 14) {
				String listaTemp[];
				listaTemp = cadastroPanel.txtFiels.get("cheque").getText().split(" ");
				Cheque ch = new Cheque(listaTemp[0], listaTemp[1], listaTemp[2], listaTemp[3]);
				c.setCheque(ch);
			}
			if (cadastroPanel.txtFiels.get("cartao").getText().length() > 14) {
				String listaTemp[];
				listaTemp = cadastroPanel.txtFiels.get("cartao").getText().split(" ");
				Cartao ca = new Cartao(listaTemp[0], listaTemp[1]);
				c.setCartao(ca);
			}
			clientes.adicionarCliente(c);
			clientes.printClientes(); // Verificar o console
			System.out.println(c.getID());
			dispose();
			new DialogClienteMenuPrincipal(c, j, "Menu principal do " + c.getNome(),
					Misc.getScreenDimension().width - 800, Misc.getScreenDimension().height - 800);

		} catch (Exception e) {
		}
	}
}