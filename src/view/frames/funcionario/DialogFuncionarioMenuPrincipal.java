package view.frames.funcionario;

import base.Cardapio;
import base.Cliente;
import base.Funcionario;
import repository.ClienteRepository;
import repository.EntregadorRepository;
import repository.PedidoRepository;
import view.frames.funcionario.components.MenuPrincipalPanel;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DialogFuncionarioMenuPrincipal extends JDialog {
    protected MenuPrincipalPanel menuPrincipalPanel;
    protected JPanel center = new JPanel();
    protected Funcionario autenticado;

    public DialogFuncionarioMenuPrincipal(JDialog parentFrame, String title, int width, int height,
            Funcionario autenticado) {
        super(parentFrame, title, true);
        this.autenticado = autenticado;
        this.inicio(width, height);
    }

    public boolean checarClientesDesativados() {
        ArrayList<Cliente> clientes = ClienteRepository.getInstancia().getClienteRepositorio();
        for (Cliente c : clientes) {
            if (c.getAtivo() == false) {
                return true;
            }
        }
        return false;
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
                    btn.addActionListener(e -> {
                        if (PedidoRepository.getInstancia().getPedidos().isEmpty()){
                            JOptionPane.showMessageDialog(null, "Nenhum pedido realizado hoje", "Atenção",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            new DialogVerPedidosDoDia(this.autenticado, this);
                        }
                    });
                    break;
                case "Listar entregadores":
                    btn.addActionListener(e -> {
                        if (EntregadorRepository.getInstancia().getEntregadores().size() == 0) {
                            JOptionPane.showMessageDialog(null, "Nenhum entregador cadastrado no momento.", "Atenção",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            new DialogVerEntregadores(this.autenticado, this);
                        }
                    });
                    break;
                case "Itens vendidos":
                    btn.addActionListener(e -> {
                        if (PedidoRepository.getInstancia().getPedidos().size() == 0) {
                            JOptionPane.showMessageDialog(null, "Nenhum item foi pedido até o momento", "Atenção",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            new DialogVerItensVendidos(this.autenticado, this);
                        }

                    });
                    break;
                case "Reativar clientes":
                    btn.addActionListener(e -> {
                        if (ClienteRepository.getInstancia().getClienteRepositorio().size() == 0) {
                            JOptionPane.showMessageDialog(null, "Nenhum cliente cadastrado no momento.", "Atenção",
                                    JOptionPane.ERROR_MESSAGE);
                        } else if (!checarClientesDesativados()) {
                            JOptionPane.showMessageDialog(null, "Nenhum cliente desativado no momento.", "Atenção",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            new DialogVerClientesDesativados(this.autenticado, this);
                        }
                    });
                    break;
                case "Adicionar cardápio":
                    btn.addActionListener(e -> {
                        new DialogAdicionarCardapio(new Cardapio(), this, "Cadastro de Cardápio", 7,
                                Misc.getScreenDimension().width - 600, Misc.getScreenDimension().height - 600);
                    });
                default:
                    break;
            }
        }
    }
}
