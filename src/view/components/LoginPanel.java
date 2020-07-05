package view.components;

import view.util.Misc;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class LoginPanel<T> extends JPanel {
    public final JPanel botoesPanelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    public final JButton btnEntrar = new JButton("Entrar");
    public final Map<String, JTextField> txtFiels = new HashMap<String, JTextField>();
    public final T item;
    public JPanel center = new JPanel(new GridLayout(4, 1));
    public JPanel inputPanelNorth = null;
    private final String campoLoginPanel;

    public LoginPanel(T _item, String campoLogin) {
        super(new BorderLayout());
        this.item = _item;
        this.campoLoginPanel = campoLogin;
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

    public void setInputFields() {
        inputPanelNorth = new JPanel(new GridLayout(2, 1));
        JTextField tf = new JTextField(30);
        Label l = new Label(campoLoginPanel);
        txtFiels.put(campoLoginPanel, tf);
        Misc.setFont(tf, l);
        inputPanelNorth.add(l);
        inputPanelNorth.add(tf);
        this.add(inputPanelNorth, BorderLayout.NORTH);
    }

    public void setBotoes() {
        this.add(botoesPanelSouth, BorderLayout.EAST);
        botoesPanelSouth.add(btnEntrar);
    }

    public void insertComponets(Component... components) {
        for (Component c : components) {
            this.center.add(c);
        }
    }
}
