package view.frames.cliente;

import base.Cliente;
import base.Pedido;
import repository.CardapioRepository;
import repository.PedidoRepository;
import view.frames.cliente.components.*;

import javax.swing.*;

public class DialogAlterarPedido extends AlterarPedidosPanel {
    protected Pedido[] mainPedidoUnico = new Pedido[1];
    protected Cliente clienteDoPedido;
    protected Object[] opts = { "Atualizar Qntd ", "Excluir Item" };
    protected final String ATENCAO = "Atenção";
    protected final String SUCESSO = "Sucesso";

    public DialogAlterarPedido(Cliente autenticado, Pedido[] pedido, JDialog parentFrame) {
        super(parentFrame, "Alterar Pedido", autenticado);
        this.clienteDoPedido = autenticado;
        this.mainPedidoUnico = pedido;
        tabela.getSelectionModel().addListSelectionListener(e -> {
            int cod = Integer.parseInt(String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 0)));
            int result = JOptionPane.showOptionDialog(null, panel, "Modificar pedido " + mainPedidoUnico[0].getCardapioIndex(cod-1).getNome() +
            " | R$ " + mainPedidoUnico[0].getCardapioIndex(cod-1).getPreco(),
            JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, opts, null);
            if(result == JOptionPane.YES_OPTION){
                try{
                    int qntd = Integer.parseInt(textField.getText());
                    mainPedidoUnico[0].atualizarQuantidade(qntd, cod - 1, mainPedidoUnico[0].getCardapioIndex(cod - 1));
                    clienteDoPedido.setPedidoUnico(mainPedidoUnico);
                    JOptionPane.showMessageDialog(null, "Quantidade do item pedido alterada\n" + mainPedidoUnico[0].getCardapioIndex(cod-1).toString() 
                    + "\nNova quantidade: " + qntd, SUCESSO, JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Valor inválido", ATENCAO, JOptionPane.ERROR_MESSAGE);
                }
            }
            else if(result == JOptionPane.NO_OPTION){
                if (mainPedidoUnico[0].getItensPedidos().size() <=1) {
                    JOptionPane.showMessageDialog(null, "Pedido com poucas unidades, não é possivel\nremover item", ATENCAO, JOptionPane.ERROR_MESSAGE);
                }else{
                    mainPedidoUnico[0].delItem(cod - 1, mainPedidoUnico[0].getCardapioIndex(cod - 1));
                    clienteDoPedido.setPedidoUnico(mainPedidoUnico);
                    JOptionPane.showMessageDialog(null, "Item removido do pedido", SUCESSO, JOptionPane.INFORMATION_MESSAGE);
                }
            }
            PedidoRepository.getInstancia().printPedidos();
            dispose();
        });
    }
    @Override
    protected void adicionarItemPedidoAlt() {
        CardapioRepository cardapio = CardapioRepository.getInstancia();
        try{
        mainPedidoUnico[0].adicionarItem(Integer.parseInt(campoQntdAlt.getText()), cardapio.buscarItem(String.valueOf(comboboxPedido.getSelectedItem())));
        JOptionPane.showMessageDialog(null, cardapio.buscarItem(String.valueOf(comboboxPedido.getSelectedItem())) + " foi adicionada ao pedido" , SUCESSO, JOptionPane.INFORMATION_MESSAGE);
        PedidoRepository.getInstancia().printPedidos();
        dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Valor inválido", ATENCAO, JOptionPane.ERROR_MESSAGE);
        }
        clienteDoPedido.setPedidoUnico(mainPedidoUnico);
    }


}
