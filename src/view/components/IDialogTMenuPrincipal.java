package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public abstract class IDialogTMenuPrincipal<T> extends JDialog {
    protected T item = null;
    protected MenuPrincipalPanel<T> menuPrincipalPanel;
    protected JPanel center = new JPanel();
    protected String campoLogin;

    public IDialogTMenuPrincipal(T _item, Frame parentFrame, String title, int width, int height) {
        super(parentFrame, title, true);
        this.inicio(_item, width, height);
    }

    public IDialogTMenuPrincipal(T _item, JDialog parentFrame, String title, int width, int height) {
        super(parentFrame, title, true);
        this.inicio(_item, width, height);
    }

    protected void inicio(T _item, int width, int height) {
        this.item = _item;
        this.menuPrincipalPanel = new MenuPrincipalPanel<T>(_item);
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
        JDialog parent = this;
        menuPrincipalPanel.btnOpcao1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcao1(parent);
            }
        });
        menuPrincipalPanel.btnOpcao2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcao2(parent);
            }
        });
        menuPrincipalPanel.btnOpcao3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcao3(parent);
            }
        });
        menuPrincipalPanel.btnOpcao4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcao4(parent);
            }
        });
    }

    protected abstract void opcao1(JDialog parent);

    protected abstract void opcao2(JDialog parent);

    protected abstract void opcao3(JDialog parent);

    protected abstract void opcao4(JDialog parent);
}
