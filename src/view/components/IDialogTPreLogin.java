package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import base.Funcionario;

@SuppressWarnings("serial")
public abstract class IDialogTPreLogin<T> extends JDialog {
    protected T item = null;
    protected InitialPanel<T> initialPanel;
    protected JPanel center = new JPanel();
    protected String campoLogin;

    public IDialogTPreLogin(T _item, Frame parentFrame, String title, int width, int height) {
        super(parentFrame, title, true);
        this.inicio(_item, width, height);
    }

    public IDialogTPreLogin(T _item, JDialog parentFrame, String title, int width, int height) {
        super(parentFrame, title, true);
        this.inicio(_item, width, height);
    }

    protected void inicio(T _item, int width, int height) {
        this.item = _item;
        this.initialPanel = new InitialPanel<T>(_item);
        this.setSize(width, height);
        this.firsts();
        this.setCenterComponents();
        this.events();
        this.lasts();
    }

	protected void firsts() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void setCenterComponents() {
		this.add(center, BorderLayout.CENTER);
		center.setBackground(Color.BLACK);
		center.add(initialPanel, BorderLayout.WEST);
	}

	protected void lasts() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@SuppressWarnings("unused")
	protected void events() {
		JDialog parent = this;
		initialPanel.btnNewAccount.addActionListener(e -> cadastro(parent));
		initialPanel.btnLogin.addActionListener(e -> login(parent));
	}

	protected abstract void login(JDialog parent);

	protected abstract void cadastro(JDialog parent);
}
