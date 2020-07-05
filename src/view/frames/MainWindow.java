package view.frames;

import view.util.IClosable;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements IClosable {
    private static final String TITLE = "Sistema Faminto - Versão 2.0";
    private final JMenuBar menuBarMain;
    private final JPanel center = new JPanel();

    public MainWindow() {
        menuBarMain = new MenuBar(this);
        this.firsts();
        this.mids();
        this.lasts();
    }

    private void firsts() {
        this.setTitle(TITLE);
        this.fonts();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    private void mids() {
        this.add(menuBarMain, BorderLayout.NORTH);
        this.add(center, BorderLayout.CENTER);
        center.setBackground(Color.BLACK);
    }

    private void lasts() {
        this.setVisible(true);
    }

    private void fonts() {
        this.setFont(Misc.FONT);
        center.setFont(Misc.FONT);
    }

    public void close() {
        System.exit(0);
    }
}
