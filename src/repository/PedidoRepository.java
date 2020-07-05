package repository;

import base.Entregador;
import base.Pedido;
import base.SituacaoPedidoEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoRepository {
    private static PedidoRepository pInstancia;
    private final ArrayList<Pedido> pedidos;

    private PedidoRepository() {
        pedidos = new ArrayList<>();
    }

    public static PedidoRepository getInstancia() {
        if (pInstancia == null) {
            pInstancia = new PedidoRepository();
        }
        return pInstancia;
    }

    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void atualizarPedido(Pedido pedido) {
        for (int i = 0; i < pedidos.size(); i += 1) {
            Pedido p = pedidos.get(i);
            if (p.getId() == pedido.getId()) {
                pedidos.set(i, pedido);
                return;
            }
        }
    }

    public void removerPedido(Pedido pedido) {
        Pedido pedidoAlvo = this.buscarPedido(pedido.getCliente().getID());
        this.pedidos.remove(pedidoAlvo);
    }

    public Pedido buscarPedido(int index) throws IndexOutOfBoundsException {
        return this.pedidos.get(index);
    }

    public Pedido buscarPedido(String id) {
        for (int i = 0; i < pedidos.size(); i += 1) {
            Pedido pedido = this.buscarPedido(i);
            if (pedido.getCliente().getID().equals(id)) {
                return pedido;
            }
        }
        return null; // Não existe este pedido
    }

    public Pedido buscarPedidoConcluido(int index) throws IndexOutOfBoundsException {
        return this.pedidos.stream()
                .filter(p -> p.getSituacao() == SituacaoPedidoEnum.CONCLUIDO).collect(Collectors.toList()).get(index);
    }

    public Pedido buscarPedidoPendente(int index) throws IndexOutOfBoundsException {
        return this.pedidos.stream()
                .filter(p -> p.getSituacao() == SituacaoPedidoEnum.PENDENTE).collect(Collectors.toList()).get(index);
    }

    public Pedido buscarPedidoEmAndamentoEntrega(Entregador entregador, int index) throws IndexOutOfBoundsException {
        return buscarPedidosPeloEntregador(entregador.getPlacaDoVeiculo()).stream().filter(
            p -> p.getSituacao() == SituacaoPedidoEnum.EM_ANDAMENTO).collect(Collectors.toList()).get(index);
    }

    public List<Pedido> buscarPedidosPendentes() {
        List<Pedido> pedidosEntr = new ArrayList<>();
        for (Pedido p : this.pedidos) {
            Entregador entregador = p.getEntregador();
            if (entregador == null && p.getSituacao() == SituacaoPedidoEnum.PENDENTE) {
                pedidosEntr.add(p);
            }
        }
        return pedidosEntr;
    }

    public List<Pedido> buscarPedidosPeloEntregador(String placa) {
        List<Pedido> pedidosEntr = new ArrayList<>();
        for (Pedido p : this.pedidos) {
            Entregador entregador = p.getEntregador();
            if (entregador != null) {
                if (entregador.getPlacaDoVeiculo().equals(placa)) {
                    pedidosEntr.add(p);
                }
            }
        }
        return pedidosEntr;
    }

    public void printPedidos() {
        int count = 0;
        for (int i = 0; i < pedidos.size(); i += 1) {
            count++;
            Pedido item = this.buscarPedido(i);
            System.out.print(count + ". ");
            System.out.println(item);
        }
    }

    public void printPedidosFuncionario() {
        int count = 0;
        for (int i = 0; i < pedidos.size(); i += 1) {
            count++;
            Pedido item = this.buscarPedido(i);
            System.out.print(count + ". ");
            System.out.println(item);
            item.printItensResumo();
        }
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

}
