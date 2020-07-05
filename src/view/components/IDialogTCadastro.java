package view.components;

import view.util.Misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public abstract class IDialogTCadastro<T> extends JDialog {
    protected T item = null;
    protected CadastroPanel<T> cadastroPanel;
    protected JPanel center = new JPanel();
    protected int numColPanelCadastro;

    public IDialogTCadastro(T _item, Frame parentFrame, String title, int numCol, int width, int height) {
        super(parentFrame, title, true);
        this.numColPanelCadastro = numCol;
        this.inicio(_item, width, height);
    }

    public IDialogTCadastro(T _item, JDialog parentFrame, String title, int numCol, int width, int height) {
        super(parentFrame, title, true);
        this.numColPanelCadastro = numCol;
        this.inicio(_item, width, height);
    }

    protected void inicio(T _item, int width, int height) {
        this.item = _item;
        this.cadastroPanel = new CadastroPanel<T>(_item, numColPanelCadastro);
        this.setSize(width, height);
        this.firsts();
        this.setCenterComponents();
        this.events();
        this.setCrudComponents();
        this.lasts();
    }

    protected void firsts() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    protected void setCenterComponents() {
        this.add(center, BorderLayout.CENTER);
        center.setBackground(Color.BLACK);
        center.add(cadastroPanel, BorderLayout.CENTER);

        JScrollPane scroll = new JScrollPane(cadastroPanel);
        scroll.setFont(Misc.FONT);
        center.add(scroll, BorderLayout.CENTER);
    }

    protected void setCrudComponents() {
        this.cadastroPanel.setInputFields();
    }

    protected void lasts() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unused")
    protected void events() {
        JDialog parent = this;
        cadastroPanel.btnCadastroConfirma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmaCadastro(parent);
            }
        });
    }

    protected abstract void confirmaCadastro(JDialog parent);
}
