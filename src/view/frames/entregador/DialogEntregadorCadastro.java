package view.frames.entregador;

import base.Entregador;
import repository.EntregadorRepository;
import view.components.IDialogTCadastro;
import view.util.Misc;

import javax.swing.*;

@SuppressWarnings("serial")
public class DialogEntregadorCadastro extends IDialogTCadastro<Entregador> {
    public DialogEntregadorCadastro(Entregador _item, JDialog parentFrame, String title, int numCol, int width, int height) {
        super(_item, parentFrame, title, numCol, width, height);
    }

    public void confirmaCadastro(JDialog j) {
        final EntregadorRepository repo = EntregadorRepository.getInstancia();
        Entregador c = new Entregador(
                cadastroPanel.txtFiels.get("nome").getText(),
                cadastroPanel.txtFiels.get("placaDoVeiculo").getText()
        );
        repo.adicionarEntregador(c);
        repo.printEntregadores(); // Verificar o console
        System.out.println(c.getPlacaDoVeiculo());
        this.setVisible(false);
        new DialogEntregadorMenuPrincipal(
                j,
                "Menu principal Entregador - " + c.getNome(),
                Misc.getScreenDimension().width - 800,
                Misc.getScreenDimension().height - 800,
                repo.buscarEntregadorPelaPlaca(c.getPlacaDoVeiculo())
        );
    }
}