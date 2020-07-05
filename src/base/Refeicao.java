package base;

public class Refeicao extends Cardapio {

    public Refeicao() {
    }

    public Refeicao(String nome, double preco, int qtdEstoque) {
        super(nome, preco, qtdEstoque);
        this.qtdEstoque = qtdEstoque;
        this.estoqueInicial = qtdEstoque;
    }

    @Override
    public String toString() {
        return String.format("Refeição: [%s, R$ %.2f, %d Uni.]", nome, preco, qtdEstoque);
    }

}
