package view.frames.cliente;

import base.Cliente;
import view.frames.cliente.components.DadosCadastraisPanel;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class DialogClienteDadosCadastrais extends JDialog{

	public DialogClienteDadosCadastrais(Cliente item, Frame parentFrame, String title, int width, int height) {
		super(parentFrame, title, true);
		this.inicio(item, width, height);
	}

	public DialogClienteDadosCadastrais(Cliente item, JDialog parentFrame, String title, int width, int height) {
		super(parentFrame, title, true);
		this.inicio(item, width, height);
	}
	protected Cliente cliente = null;
    protected DadosCadastraisPanel dadosCadastraisPanel;
    protected JPanel center = new JPanel();

    protected void inicio(Cliente c, int width, int height) {
        this.cliente = c;
        this.dadosCadastraisPanel = new DadosCadastraisPanel(c);
        this.setSize(width, height);
        this.firsts();
        this.setCenterComponents();
        this.lasts();
    }

	protected void firsts() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	protected void setCenterComponents() {
		this.add(center, BorderLayout.CENTER);
		center.setBackground(Color.BLACK);
		center.add(dadosCadastraisPanel, BorderLayout.WEST);
	}

	protected void lasts() {
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}