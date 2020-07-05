package view.frames.entregador.components;

import view.util.Misc;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MenuPrincipalPanel extends JPanel {
    public final JPanel botoesPanelSouth = new JPanel(new FlowLayout(FlowLayout.LEFT));
    public final JButton[] buttons;
    private final String[] opcoes = {"Ver pedidos do dia", "Aceitar pedido", "Devolver pedido", "Concluir pedido", "Ver comissão"};
    public JPanel center = new JPanel(new GridLayout(4, 1));

    public MenuPrincipalPanel() {
        super(new BorderLayout());
        this.buttons = new JButton[opcoes.length];
        for (int i = 0; i < opcoes.length; i++) {
            this.buttons[i] = new JButton(opcoes[i]);
        }
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
        for (JButton btn : buttons) {
            botoesPanelSouth.add(btn);
        }
        botoesPanelSouth.setBackground(Color.BLACK);
    }
}
