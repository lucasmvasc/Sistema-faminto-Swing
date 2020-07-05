package view.components;

import view.util.Misc;
import base.Cliente;
import base.Funcionario;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MenuPrincipalPanel<T> extends JPanel {
	public JPanel center = new JPanel(new GridLayout(4, 1));
	public final JPanel botoesPanelSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
	public JPanel inputPanelNorth = null;

	public MenuPrincipalPanel(T _item) {
		super(new BorderLayout());
		this.item = _item;
		this.inicio();
	}

	public final T item;
	public JButton btnOpcao1;
	public JButton btnOpcao2;
	public JButton btnOpcao3;
	public JButton btnOpcao4;

	public void inicio() {
		this.firsts();
		this.definirBotoesPorClasse();
		this.setBotoes();
	}

	public void firsts() {
		this.removeAll();
		center.setFont(Misc.FONT);
		this.add(center, BorderLayout.CENTER);
	}

	public void definirBotoesPorClasse() {
		if (this.item instanceof Funcionario) {
			btnOpcao1 = new JButton(Misc.labelListFuncionario[0]);
			btnOpcao2 = new JButton(Misc.labelListFuncionario[1]);
			btnOpcao3 = new JButton(Misc.labelListFuncionario[2]);
			btnOpcao4 = new JButton(Misc.labelListFuncionario[3]);
		} else if (this.item instanceof Cliente) {
			btnOpcao1 = new JButton(Misc.labelListCliente[0]);
			btnOpcao2 = new JButton(Misc.labelListCliente[1]);
			btnOpcao3 = new JButton(Misc.labelListCliente[2]);
			btnOpcao4 = new JButton(Misc.labelListCliente[3]);
		}
	}

	public void setBotoes() {
		this.add(botoesPanelSouth, BorderLayout.SOUTH);
		botoesPanelSouth.add(btnOpcao1);
		botoesPanelSouth.add(btnOpcao2);
		botoesPanelSouth.add(btnOpcao3);
		botoesPanelSouth.add(btnOpcao4);
		botoesPanelSouth.setBackground(Color.BLACK);
	}

	public void insertComponets(Component... components) {
		for (Component c : components) {
			this.center.add(c);
		}
	}
}
