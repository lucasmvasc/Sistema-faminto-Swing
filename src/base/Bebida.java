package base;

public class Bebida extends Cardapio {

    public Bebida() {
    }

    public Bebida(String nome, double preco, int qtdEstoque) {
        super(nome, preco, qtdEstoque);
        this.qtdEstoque = qtdEstoque;
        this.estoqueInicial = qtdEstoque;
    }

    @Override
    public String toString() {
        return String.format("Bebida: [%s, R$ %.2f, %d Uni.]", nome, preco, qtdEstoque);
    }

}