package view.frames.cliente;

import base.Cliente;
import base.Pedido;
import base.SituacaoPedidoEnum;
import repository.PedidoRepository;
import view.components.IDialogTMenuPrincipal;
import view.util.Misc;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class DialogClienteMenuPrincipal extends IDialogTMenuPrincipal<Cliente> {
    private final String ATENCAO = "Atenção";
    public DialogClienteMenuPrincipal(Cliente _item,Frame parentFrame, String title, int width, int height) {
        super(_item, parentFrame, title, width, height);
    }

    public DialogClienteMenuPrincipal(Cliente _item, JDialog parentFrame, String title, int width, int height) {
        super(_item, parentFrame, title, width, height);
    }

    @Override
    protected void opcao1(JDialog parent) {
        // Fazer pedido
        if(item.getAtivo()){
            try{
                if(this.item.getPedidoUnico()[0] != null){
                    JOptionPane.showMessageDialog(null, "Há um pedido em andamento ! ", ATENCAO, JOptionPane.ERROR_MESSAGE);
                }
            }catch(NullPointerException e){
                new DialogFazerPedido(this.item, parent);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Prezado cliente sua conta foi desativada devido ao excesso \n"
            + "de devoluções no seu endereço. Por favor, entre em\n"
            + "contato com o administrador para desbloqueio.", "BLOQUEADO", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void opcao2(JDialog parent) {
        // Dados cadastrais
        new DialogClienteDadosCadastrais(this.item, parent, "Dados Cadastrais", Misc.getScreenDimension().width - 400,
				Misc.getScreenDimension().height - 700);

    }

    @Override
    protected void opcao3(JDialog parent) {
        // Cancelar pedido
        try{
            if(this.item.getPedidoUnico()[0].getSituacao() == SituacaoPedidoEnum.PENDENTE){
                this.item.getPedidoUnico()[0].cancelarPedido();
                this.item.setPedidoUnico(null);
                JOptionPane.showMessageDialog(null, " Remoção do pedido concluída", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                PedidoRepository.getInstancia().printPedidos();
            } else{ 
                JOptionPane.showMessageDialog(null, "Não há pedido pendente ! ", ATENCAO, JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não há pedido em andamento ! ", ATENCAO, JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    protected void opcao4(JDialog parent) {
        // Alterar pedido
        try{
            if(this.item.getPedidoUnico()[0].getSituacao() == SituacaoPedidoEnum.PENDENTE){
                new DialogAlterarPedido(this.item, this.item.getPedidoUnico(), parent);
            } else{ 
                JOptionPane.showMessageDialog(null, "Não há pedido pendente ! ", ATENCAO, JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não há pedido em andamento ! ", ATENCAO, JOptionPane.ERROR_MESSAGE);
        }
    }
}