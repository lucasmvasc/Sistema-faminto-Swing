package view.frames.cliente;

import base.Cliente;
import base.SituacaoPedidoEnum;
import repository.ClienteRepository;
import view.components.IDialogTLogin;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class DialogClienteLogin extends IDialogTLogin<Cliente> {
    private Cliente autenticado;

    public DialogClienteLogin(Cliente _item, Frame parentFrame, String title, String campoLogin, int width,
                              int height) {
        super(_item, parentFrame, title, campoLogin, width, height);
    }

    public DialogClienteLogin(Cliente _item, JDialog parentFrame, String title, String campoLogin, int width,
                              int height) {
        super(_item, parentFrame, title, campoLogin, width, height);
    }

    public void entrar(JDialog j) {
        ClienteRepository clientes = ClienteRepository.getInstancia();
        this.autenticado = clientes.buscarClientePeloId(loginPanel.txtFiels.get("Digite o seu ID").getText());
        if (this.autenticado == null) {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado", "Atenção", JOptionPane.ERROR_MESSAGE);
        } else {
            try{
                if (this.autenticado.getPedidoUnico()[0] != null) {
                    if (this.autenticado.getPedidoUnico()[0].getSituacao() == SituacaoPedidoEnum.CONCLUIDO ||
                        this.autenticado.getPedidoUnico()[0].getSituacao() == SituacaoPedidoEnum.DEVOLVIDO) {
                        this.autenticado.setPedidoUnico(null);
                    }
                }
                this.autenticado.checkClienteStatus();
                new DialogClienteMenuPrincipal(this.autenticado, j, "Menu principal do " + this.autenticado.getNome(),
                        Misc.getScreenDimension().width - 800, Misc.getScreenDimension().height - 800);
                dispose();
            }catch(Exception e){
                new DialogClienteMenuPrincipal(this.autenticado, j, "Menu principal do " + this.autenticado.getNome(),
                Misc.getScreenDimension().width - 800, Misc.getScreenDimension().height - 800);
                dispose();
            }
        }

    }
}