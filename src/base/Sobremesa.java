package base;

public class Sobremesa extends Cardapio {

    public Sobremesa() {
    }

    public Sobremesa(String nome, double preco, int qtdEstoque) {
        super(nome, preco, qtdEstoque);
        this.qtdEstoque = qtdEstoque;
        this.estoqueInicial = qtdEstoque;
    }

    @Override
    public String toString() {
        return String.format("Sobremesa: [%s, R$ %.2f, %d Uni.]", nome, preco, qtdEstoque);
    }

}
