package base;

public class Lanche extends Cardapio {

    public Lanche() {
    }

    public Lanche(String nome, double preco, int qtdEstoque) {
        super(nome, preco, qtdEstoque);
        this.qtdEstoque = qtdEstoque;
        this.estoqueInicial = qtdEstoque;
    }

    @Override
    public String toString() {
        return String.format("Lanche: [%s, R$ %.2f, %d Uni.]", nome, preco, qtdEstoque);
    }

}