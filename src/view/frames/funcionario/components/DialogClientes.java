package view.frames.funcionario.components;

import base.Cliente;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DialogClientes extends JDialog {
    protected JPanel painelFundo;
    protected JTable tabela;
    protected JScrollPane barraRolagem;
    protected String[] colunas = { "Nome", "Telefone" };
    protected List<Cliente> clientes;

    public DialogClientes(JDialog parentFrame, String title, java.util.List<Cliente> clientes) {
        super(parentFrame, title);
        this.clientes = clientes;
        this.arrangeLayout();
    }

    private void carregarTabela() {
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
        List<String[]> clientesList = new ArrayList<>();
        for (Cliente cliente : this.clientes) {
            if (!cliente.getAtivo()) {
                String[] strings = new String[] { cliente.getNome(), cliente.getTelefone() };
                clientesList.add(strings);
            }
        }
        Object[][] tableData = new Object[clientesList.size()][2];
        for (int i = 0; i < clientesList.size(); i++) {
            tableData[i] = clientesList.get(i);
        }
        tabela = new JTable(tableData, colunas);
        tabela.setDefaultEditor(Object.class, null);
        barraRolagem = new JScrollPane(tabela);
        painelFundo.add(barraRolagem);
        getContentPane().add(painelFundo);
    }

    private void arrangeLayout() {
        carregarTabela();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Misc.getScreenDimension();
        setSize(dim.width - 800, dim.height - 800);
        setVisible(true);
    }
}
