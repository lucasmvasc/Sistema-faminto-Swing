package repository;

import base.*;

import java.util.ArrayList;
import java.util.List;

public class CardapioRepository {
    private static CardapioRepository mInstancia;
    private final ArrayList<Cardapio> cardapio;

    private CardapioRepository() {
        cardapio = new ArrayList<>();
        new ArrayList<>();

        // Bebidas
        this.adicionarItem(new Bebida("Coca Cola", 3.0, 1000));
        this.adicionarItem(new Bebida("Guarana", 3.0, 5000));

        // Lanches
        this.adicionarItem(new Lanche("Sanduiche", 5.0, 200));
        this.adicionarItem(new Lanche("Empada", 6.5, 150));
        this.adicionarItem(new Lanche("Folheado", 5.5, 175));

        // Refeições
        this.adicionarItem(new Refeicao("Macarronada", 12.70, 100));
        this.adicionarItem(new Refeicao("Lasanha", 15.60, 110));
        this.adicionarItem(new Refeicao("Estrogonofe", 15.60, 120));

        // Sobremesas
        this.adicionarItem(new Sobremesa("Mousse", 8.00, 450));
        this.adicionarItem(new Sobremesa("Pudim", 7.50, 250));

    }

    public static CardapioRepository getInstancia() {
        if (mInstancia == null) {
            mInstancia = new CardapioRepository();
        }
        return mInstancia;
    }

    public void adicionarItem(Cardapio item) {
        this.cardapio.add(item);
    }

    public Cardapio buscarItem(int index) throws IndexOutOfBoundsException {
        return this.cardapio.get(index);
    }

    public Cardapio buscarItem(String nome) {
        for (int i = 0; i < cardapio.size(); i += 1) {
            Cardapio item = this.buscarItem(i);
            if (item.getNome().equals(nome)) {
                return item;
            }
        }
        return null; // Não existe este item
    }

    public void printCardapio() {
        int count = 0;
        for (int i = 0; i < cardapio.size(); i += 1) {
            count++;
            Cardapio item = this.buscarItem(i);
            System.out.print(count + ". ");
            System.out.println(item);
        }
    }

    public List<Cardapio> getCardapioRepositorio() {
        return cardapio;
    }

}
