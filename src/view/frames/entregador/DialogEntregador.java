package view.frames.entregador;

import base.Entregador;
import view.components.IDialogTPreLogin;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class DialogEntregador extends IDialogTPreLogin<Entregador> {
    public DialogEntregador(Entregador _item, Frame parentFrame, String title, int width, int height) {
        super(_item, parentFrame, title, width, height);
    }

    public DialogEntregador(Entregador _item, JDialog parentFrame, String title, int width, int height) {
        super(_item, parentFrame, title, width, height);
    }

    public void login(JDialog j) {
        new DialogEntregadorLogin(
                new Entregador(),
                j,
                "LOGIN ENTREGADOR",
                "Digite a placa do seu veículo",
                Misc.getScreenDimension().width - 800,
                Misc.getScreenDimension().height - 800
        );
    }

    public void cadastro(JDialog j) {
        new DialogEntregadorCadastro(new Entregador(),
                j,
                "CADASTRO ENTREGADOR",
                7,
                Misc.getScreenDimension().width - 800,
                Misc.getScreenDimension().height - 800
        );
    }
}