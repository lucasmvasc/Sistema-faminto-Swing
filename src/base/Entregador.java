package base;

import java.util.ArrayList;
import java.util.List;

public class Entregador {
    private String nome;
    private String placaDoVeiculo;
    private List<Pedido> pedidos = new ArrayList<Pedido>();

    public Entregador() {
    }

    public Entregador(String nome, String placaDoVeiculo) {
        this();
        this.nome = nome;
        this.placaDoVeiculo = placaDoVeiculo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlacaDoVeiculo() {
        return placaDoVeiculo;
    }

    public void setPlacaDoVeiculo(String placaDoVeiculo) {
        this.placaDoVeiculo = placaDoVeiculo;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " | Placa do veículo: " + placaDoVeiculo;
    }

}
