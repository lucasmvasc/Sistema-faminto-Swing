package view.frames;

import base.Cliente;
import base.Entregador;
import base.Funcionario;
import view.frames.cliente.DialogClienteInitial;
import view.frames.entregador.DialogEntregador;
import view.frames.funcionario.DialogFuncionarioInitial;
import view.util.IClosable;
import view.util.Misc;

import javax.swing.*;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
    private final IClosable closable;
    private final JMenu menu = new JMenu("Sistema Faminto 2.0");
    private final JMenuItem fecharMenuItem = new JMenuItem("Fechar");
    private final JMenuItem creditos = new JMenuItem("- Créditos ");
    private final JMenuItem clienteMenuItem = new JMenuItem("- Cliente ");
    private final JMenuItem entregadorMenuItem = new JMenuItem("- Entregador");
    private final JMenuItem funcionarioMenuItem = new JMenuItem("- Funcionário");
    private JFrame parentFrame = null;

    public MenuBar(IClosable closable) {
        this.closable = closable;
        this.add(menu);
        this.configureFonts();

        menu.add(clienteMenuItem);
        menu.add(funcionarioMenuItem);
        menu.add(entregadorMenuItem);
        menu.add(creditos);
        menu.add(fecharMenuItem);
        this.setupActionListeners();
    }

    private void configureFonts() {
        this.setFont(Misc.FONT);
        menu.setFont(Misc.FONT);
        funcionarioMenuItem.setFont(Misc.FONT);
        entregadorMenuItem.setFont(Misc.FONT);
        fecharMenuItem.setFont(Misc.FONT);
        clienteMenuItem.setFont(Misc.FONT);
        creditos.setFont(Misc.FONT);
    }

    private void setupActionListeners() {
        this.fecharActionListener();
        this.clienteActionListener();
        this.funcionarioActionListener();
        this.entregadorActionListener();
        this.creditosActionListener();
    }

    private void fecharActionListener() {
        fecharMenuItem.addActionListener(e -> closable.close());
    }
    private void creditosActionListener() {
        creditos.addActionListener(e -> JOptionPane.showMessageDialog(null, "Developed by: \n-Lucas de Moura Vasconcelos\n-Emanuele Versoça\n-Danilo Vilhena" 
        , "Créditos", JOptionPane.INFORMATION_MESSAGE));
    }

    private void clienteActionListener() {
        parentFrame = (JFrame) this.getParent();
        clienteMenuItem.addActionListener(e -> new DialogClienteInitial(new Cliente(), parentFrame, "Menu do Cliente",
                Misc.getScreenDimension().width - 800, Misc.getScreenDimension().height - 800));
    }

    private void funcionarioActionListener() {
        parentFrame = (JFrame) this.getParent();
        funcionarioMenuItem.addActionListener(e -> {
            new DialogFuncionarioInitial(new Funcionario(), parentFrame, "Menu do Funcionário",
                    Misc.getScreenDimension().width - 800, Misc.getScreenDimension().height - 800);
        });
    }

    private void entregadorActionListener() {
        parentFrame = (JFrame) this.getParent();
        entregadorMenuItem.addActionListener(e -> {
            new DialogEntregador(new Entregador(), parentFrame, "Menu do Entregador",
                    Misc.getScreenDimension().width - 800, Misc.getScreenDimension().height - 800);
        });
    }
}
