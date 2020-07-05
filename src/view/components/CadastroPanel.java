package view.components;

import base.Entregador;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class CadastroPanel<T> extends JPanel {
    public final JPanel botoesPanelSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    public final JButton btnCadastroConfirma = new JButton("Cadastrar");
    public final Map<String, JTextField> txtFiels = new HashMap<>();
    public final T item;
    private final int numColPanel;
    public JPanel center = new JPanel(new GridLayout(4, 1));
    public JPanel inputPanelNorth = null;
    public final String[] opcoesComboBox = { "Bebida", "Lanche", "Refeição", "Sobremesa" };
    public final JComboBox<String> escolhaCardapio = new JComboBox<>(opcoesComboBox);

    public CadastroPanel(T _item, int numCol) {
        super(new BorderLayout());
        this.item = _item;
        this.numColPanel = numCol;
        this.inicio();
    }

    public void inicio() {
        this.firsts();
        this.setInputFields();
        this.setBotoes();
    }

    public void firsts() {
        this.removeAll();
        center.setFont(Misc.FONT);
        this.add(center, BorderLayout.CENTER);
    }

    public void setInputFields() {
        String[] sts = Misc.getChildAndSuperStrings(this.item);
        inputPanelNorth = new JPanel(new GridLayout(numColPanel, 2));
        for (String s : sts) {
            if ("pedidos".equals(s) && this.item instanceof Entregador) {
                continue;
            }

            if (!(s.contentEquals("ID") || s.contentEquals("pedidoUnico") || s.contentEquals("contadorBloqueio")
                    || s.contentEquals("ativo") || s.contentEquals("estoqueInicial")
                    || s.contentEquals("picoDeProcura"))) {
                JTextField tf = new JTextField(30);
                Label l = new Label(s);
                txtFiels.put(s, tf);
                Misc.setFont(tf, l);
                inputPanelNorth.add(l);
                inputPanelNorth.add(tf);
            }
        }
        this.add(inputPanelNorth, BorderLayout.NORTH);
    }

    public void setComboBox() {
        this.add(escolhaCardapio, BorderLayout.CENTER);
    }

    public void setBotoes() {
        this.add(botoesPanelSouth, BorderLayout.SOUTH);
        botoesPanelSouth.add(btnCadastroConfirma);
    }

    public void insertComponets(Component... components) {
        for (Component c : components) {
            this.center.add(c);
        }
    }
}
