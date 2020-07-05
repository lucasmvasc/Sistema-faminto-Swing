package view.frames.cliente;

import base.Cliente;
import base.ItemPedido;
import base.Pedido;
import base.SituacaoPedidoEnum;
import repository.CardapioRepository;
import repository.PedidoRepository;
import view.frames.cliente.components.*;

import javax.naming.AuthenticationException;
import javax.swing.*;

public class DialogFazerPedido extends FazerPedidosPanel {
    protected Pedido mainPedido;
    protected Pedido[] mainPedidoUnico = new Pedido[1];
    protected Cliente clienteDoPedido;
    public DialogFazerPedido(Cliente autenticado, JDialog parentFrame) {
        super(parentFrame, "Fazer Pedido");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.clienteDoPedido = autenticado;
        mainPedido = new Pedido();
    }

    @Override
    protected void adicionarItemPedido() {
        CardapioRepository cardapio = CardapioRepository.getInstancia();
        try{
            mainPedido.adicionarItem(Integer.parseInt(campoQntd.getText()), cardapio.buscarItem(String.valueOf(comboboxEscolhaPedido.getSelectedItem())));
            JOptionPane.showMessageDialog(null, cardapio.buscarItem(String.valueOf(comboboxEscolhaPedido.getSelectedItem())) + " foi adicionada ao pedido" , "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valor inválido", "Atenção", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void finalizarPedido() {
        if (!mainPedido.getItensPedidos().isEmpty()) {
            PedidoRepository pedidos = PedidoRepository.getInstancia();
            mainPedido.setSituacao(SituacaoPedidoEnum.PENDENTE);
            mainPedido.setCliente(clienteDoPedido);
            mainPedidoUnico[0] = mainPedido;
            clienteDoPedido.setPedidoUnico(mainPedidoUnico);
            pedidos.adicionarPedido(mainPedido);
            clienteDoPedido.getPedidoUnico()[0].printItens();
            dispose();
            JOptionPane.showMessageDialog(null, "Valor total do pedido: R$" + clienteDoPedido.getPedidoUnico()[0].getValorTotal(),"Pedido realizado com sucesso", JOptionPane.INFORMATION_MESSAGE);
            PedidoRepository.getInstancia().printPedidos();
        } else {
            JOptionPane.showMessageDialog(null, "Pedido não possui itens adicionados", "Atenção", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
