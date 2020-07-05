package view.frames.funcionario.components;

import base.Entregador;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DialogEntregadores extends JDialog {
    protected JPanel painelFundo;
    protected JTable tabela;
    protected JScrollPane barraRolagem;
    protected String[] colunas = { "Nome", "Placa do veículo" };
    protected List<Entregador> entregadores;

    public DialogEntregadores(JDialog parentFrame, String title, java.util.List<Entregador> entregadores) {
        super(parentFrame, title);
        this.entregadores = entregadores;
        this.arrangeLayout();
    }

    private void carregarTabela() {
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(1, 1));
        List<String[]> entregadoresList = new ArrayList<>();
        for (Entregador entregador : this.entregadores) {
            String[] strings = new String[] { entregador.getNome(), entregador.getPlacaDoVeiculo() };
            entregadoresList.add(strings);
        }
        Object[][] tableData = new Object[entregadoresList.size()][2];
        for (int i = 0; i < entregadoresList.size(); i++) {
            tableData[i] = entregadoresList.get(i);
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
