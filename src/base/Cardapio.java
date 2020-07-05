package base;

public class Cardapio {
    protected String nome;
    protected double preco;
    protected int qtdEstoque;
    protected int estoqueInicial;
    private boolean picoDeProcura;

    public Cardapio() {
    }

    public Cardapio(String nome, double preco, int qtdEstoque) {
        this();
        this.nome = nome;
        this.preco = preco;
        this.qtdEstoque = qtdEstoque;
        this.estoqueInicial = qtdEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isPicoDeProcura() {
        return picoDeProcura;
    }

    public void setPicoDeProcura(boolean picoDeProcura) {
        this.picoDeProcura = picoDeProcura;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getEstoqueInicial() {
        return estoqueInicial;
    }

    public void setEstoqueInicial(int estoqueInicial) {
        this.estoqueInicial = estoqueInicial;
    }

    @Override
    public String toString() {
        return "Cardapio [" + nome + "R$ " + preco + "]";
    }

}
