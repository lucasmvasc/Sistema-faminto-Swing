package view.frames.cliente.components;

import base.Cliente;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class DadosCadastraisPanel extends JPanel {
    public final Map<String, Label> txtFiels = new HashMap<>();
    public final Cliente cliente;
    public JPanel center = new JPanel(new GridLayout(4, 1));
    public JPanel inputPanelNorth = null; 

    public DadosCadastraisPanel(Cliente c) {
        super(new BorderLayout());
        this.cliente = c;
        final String[] infoCliente = {cliente.getID(), cliente.getNome(),cliente.getEndereco(), cliente.getTelefone(), 
                cliente.getReferencia(), (cliente.getCheque() == null ? "" : cliente.getCheque().toString()), 
                (cliente.getCartao() == null ? "" : cliente.getCartao().toString())};
        this.inicio(infoCliente);
    }

    public void inicio(String[] infoLista) {
        this.firsts();
        this.setInputFields(infoLista);
    }

    public void firsts() {
        this.removeAll();
        center.setFont(Misc.FONT);
        this.add(center, BorderLayout.CENTER);
    }

    public void setInputFields(String[] infoInd) {
        int i = 0;
        String[] sts = Misc.getChildAndSuperStrings(cliente);
        inputPanelNorth = new JPanel(new GridLayout(7, 2));
        for (String s : sts) {
            if (!(s.contentEquals("pedidoUnico") || s.contentEquals("contadorBloqueio")
                    || s.contentEquals("ativo"))) {
                Label info = new Label(infoInd[i]);
                Label l = new Label(s+":");
                txtFiels.put(s, info);
                Misc.setFont(info, l);
                inputPanelNorth.add(l);
                inputPanelNorth.add(info);
                i++;
            }
        }
        this.add(inputPanelNorth, BorderLayout.NORTH);
    }

    public void insertComponets(Component... components) {
        for (Component c : components) {
            this.center.add(c);
        }
    }
}
