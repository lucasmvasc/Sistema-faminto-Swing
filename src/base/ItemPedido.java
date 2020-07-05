package base;

public class ItemPedido {
    private int qtde;
    private Cardapio cardapio;

    public ItemPedido(int quantidade, Cardapio item) {
        this.qtde = quantidade;
        this.cardapio = item;
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public void setCardapio(Cardapio item) {
        this.cardapio = item;
    }

    public int getQuantidade() {
        return qtde;
    }

    public void setQuantidade(int quantidade) {
        this.qtde = quantidade;
    }

    @Override
    public String toString() {
        return cardapio + " x " + qtde + " Uni.";
    }
}
