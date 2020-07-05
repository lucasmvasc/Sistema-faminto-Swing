package view.components;

import view.util.Misc;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class InitialPanel<T> extends JPanel {
	public JPanel center = new JPanel(new GridLayout(4, 1));
	public final JPanel botoesPanelSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
	public JPanel inputPanelNorth = null;

	public final JButton btnNewAccount = new JButton("Criar uma nova conta");
	public final JButton btnLogin = new JButton("Entrar na sua conta");
	public final T item;

	public InitialPanel(T _item) {
		super(new BorderLayout());
		this.item = _item;
		this.inicio();
	}

	public void inicio() {
		this.firsts();
		this.setBotoes();
	}

	public void firsts() {
		this.removeAll();
		center.setFont(Misc.FONT);
		this.add(center, BorderLayout.CENTER);
	}

	public void setBotoes() {
		this.add(botoesPanelSouth, BorderLayout.SOUTH);
		botoesPanelSouth.add(btnNewAccount);
		botoesPanelSouth.add(btnLogin);
		botoesPanelSouth.setBackground(Color.BLACK);
	}

	public void insertComponets(Component... components) {
		for (Component c : components) {
			this.center.add(c);
		}
	}
}
