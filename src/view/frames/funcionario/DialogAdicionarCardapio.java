package view.frames.funcionario;

import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import base.Bebida;
import base.Cardapio;
import base.Lanche;
import base.Refeicao;
import base.Sobremesa;
import base.Cardapio;
import repository.CardapioRepository;
import view.frames.funcionario.components.DialogCadastro;
import view.util.Misc;

@SuppressWarnings("serial")
public class DialogAdicionarCardapio extends DialogCadastro<Cardapio> {

    public DialogAdicionarCardapio(Cardapio _item, Frame parentFrame, String title, int numCol, int width, int height) {
        super(_item, parentFrame, title, numCol, width, height);
    }

    public DialogAdicionarCardapio(Cardapio _item, JDialog parentFrame, String title, int numCol, int width,
            int height) {
        super(_item, parentFrame, title, numCol, width, height);
    }

    public void confirmaCadastro(JDialog j) {
        final CardapioRepository cardapio = CardapioRepository.getInstancia();
        try {
            if (cadastroPanel.escolhaCardapio.getSelectedItem() == "Bebida") {
                cardapio.adicionarItem(new Bebida(cadastroPanel.txtFiels.get("nome").getText(),
                        Double.parseDouble(cadastroPanel.txtFiels.get("preco").getText()),
                        Integer.parseInt(cadastroPanel.txtFiels.get("qtdEstoque").getText())));
                dispose();
            }
            if (cadastroPanel.escolhaCardapio.getSelectedItem() == "Lanche") {
                cardapio.adicionarItem(new Lanche(cadastroPanel.txtFiels.get("nome").getText(),
                        Double.parseDouble(cadastroPanel.txtFiels.get("preco").getText()),
                        Integer.parseInt(cadastroPanel.txtFiels.get("qtdEstoque").getText())));
                dispose();
            }
            if (cadastroPanel.escolhaCardapio.getSelectedItem() == "Refeição") {
                cardapio.adicionarItem(new Refeicao(cadastroPanel.txtFiels.get("nome").getText(),
                        Double.parseDouble(cadastroPanel.txtFiels.get("preco").getText()),
                        Integer.parseInt(cadastroPanel.txtFiels.get("qtdEstoque").getText())));
                dispose();
            }
            if (cadastroPanel.escolhaCardapio.getSelectedItem() == "Sobremesa") {
                cardapio.adicionarItem(new Sobremesa(cadastroPanel.txtFiels.get("nome").getText(),
                        Double.parseDouble(cadastroPanel.txtFiels.get("preco").getText()),
                        Integer.parseInt(cadastroPanel.txtFiels.get("qtdEstoque").getText())));
                dispose();
            }
        } catch (Exception e) {
        }
    }
}