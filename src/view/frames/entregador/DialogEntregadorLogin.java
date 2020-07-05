package view.frames.entregador;

import base.Entregador;
import repository.EntregadorRepository;
import view.components.IDialogTLogin;
import view.util.Misc;

import javax.swing.*;

@SuppressWarnings("serial")
public class DialogEntregadorLogin extends IDialogTLogin<Entregador> {
    public DialogEntregadorLogin(Entregador _item, JDialog parentFrame, String title, String campoLogin, int width,
                                 int height) {
        super(_item, parentFrame, title, campoLogin, width, height);
    }

    public void entrar(JDialog j) {
        EntregadorRepository repo = EntregadorRepository.getInstancia();
        Entregador autenticado = repo.buscarEntregadorPelaPlaca(loginPanel.txtFiels.get("Digite a placa do seu veículo").getText());

        if (autenticado == null) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Atenção", JOptionPane.ERROR_MESSAGE);
        } else {
            this.setVisible(false);
            new DialogEntregadorMenuPrincipal(
                    j,
                    "Menu principal Entregador - " + autenticado.getNome(),
                    Misc.getScreenDimension().width - 800,
                    Misc.getScreenDimension().height - 800,
                    autenticado
            );
        }
    }
}