package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public abstract class IDialogTLogin<T> extends JDialog {
    protected T item = null;
    protected LoginPanel<T> loginPanel;
    protected JPanel center = new JPanel();
    protected String campoLoginPanel;

    public IDialogTLogin(T _item, Frame parentFrame, String title, String campoLogin, int width, int height) {
        super(parentFrame, title, true);
        this.campoLoginPanel = campoLogin;
        this.inicio(_item, width, height);
    }

    public IDialogTLogin(T _item, JDialog parentFrame, String title, String campoLogin, int width, int height) {
        super(parentFrame, title, true);
        this.campoLoginPanel = campoLogin;
        this.inicio(_item, width, height);
    }

    protected void inicio(T _item, int width, int height) {
        this.item = _item;
        this.loginPanel = new LoginPanel<T>(_item, campoLoginPanel);
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
        center.add(loginPanel, BorderLayout.WEST);
    }

    protected void setCrudComponents() {
        this.loginPanel.setInputFields();
    }

    protected void lasts() {
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @SuppressWarnings("unused")
    protected void events() {
        JDialog parent = this;
        loginPanel.btnEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entrar(parent);
            }
        });
    }

    protected abstract void entrar(JDialog parent);
}
