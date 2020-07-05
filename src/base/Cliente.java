package base;

import java.util.UUID;

public class Cliente {

    private String ID;
    private String nome;
    private String endereco;
    private String telefone;
    private String referencia;
    private boolean ativo;
    private Pedido[] pedidoUnico;
    private Cheque cheque;
    private Cartao cartao;
    private int contadorBloqueio;

    public Cliente() {
    }

    public Cliente(String nome, String endereco, String telefone, String referencia) {
        super();
        this.ID = UUID.randomUUID().toString();
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.referencia = referencia;
        this.contadorBloqueio = 0;
        this.ativo = true;
    }

    public void checkClienteStatus() {
        if (contadorBloqueio == 3) {
            this.setContadorBloqueio(0);
            this.setAtivo(false);
        }
    }

    public void setId(String id) {
        this.ID = id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getReferencia() {
        return referencia;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getID() {
        return ID;
    }

    public Pedido[] getPedidoUnico() {
        return pedidoUnico;
    }

    public void setPedidoUnico(Pedido[] pedidoUnico) {
        this.pedidoUnico = pedidoUnico;
    }

    public Cheque getCheque() {
        return cheque;
    }

    public void setCheque(Cheque cheque) {
        this.cheque = cheque;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public int getContadorBloqueio() {
        return contadorBloqueio;
    }

    public void setContadorBloqueio(int contadorBloqueio) {
        this.contadorBloqueio = contadorBloqueio;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Telefone: " + telefone;
    }
}
