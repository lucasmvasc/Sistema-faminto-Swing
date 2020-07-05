package view.frames.entregador;

import base.Entregador;
import view.frames.entregador.components.MenuPrincipalPanel;

import javax.swing.*;
import java.awt.*;


@SuppressWarnings("serial")
public class DialogEntregadorMenuPrincipal extends JDialog {
    protected MenuPrincipalPanel menuPrincipalPanel;
    protected JPanel center = new JPanel();
    protected Entregador autenticado;

    public DialogEntregadorMenuPrincipal(JDialog parentFrame, String title, int width, int height, Entregador autenticado) {
        super(parentFrame, title, true);
        this.autenticado = autenticado;
        this.inicio(width, height);
    }

    protected void inicio(int width, int height) {
        this.menuPrincipalPanel = new MenuPrincipalPanel();
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
        center.add(menuPrincipalPanel, BorderLayout.WEST);
    }

    protected void lasts() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unused")
    protected void events() {
        for (JButton btn : menuPrincipalPanel.buttons) {
            switch (btn.getText()) {
                case "Ver pedidos do dia":
                    btn.addActionListener(e -> new DialogVerPedidos(this.autenticado, this));
                    break;
                case "Aceitar pedido":
                    btn.addActionListener(e -> new DialogAceitarPedidos(this.autenticado, this));
                    break;
                case "Devolver pedido":
                    btn.addActionListener(e -> new DialogDevolverPedido(this.autenticado, this));
                    break;
                case "Concluir pedido":
                    btn.addActionListener(e -> new DialogConcluirPedido(this.autenticado, this));
                    break;
                case "Ver comissão":
                    btn.addActionListener(e -> new DialogComissao(this.autenticado, this));
                    break;
                default:
                    break;
            }
        }
    }
}
