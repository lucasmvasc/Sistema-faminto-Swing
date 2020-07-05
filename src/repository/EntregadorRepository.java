package repository;

import base.Entregador;

import java.util.ArrayList;

public class EntregadorRepository {
    private static EntregadorRepository eInstancia;
    private final ArrayList<Entregador> entregadores;

    private EntregadorRepository() {
        entregadores = new ArrayList<>();

    }

    public static EntregadorRepository getInstancia() {
        if (eInstancia == null) {
            eInstancia = new EntregadorRepository();
        }
        return eInstancia;
    }

    private String higienizarPlaca(String placa) {
        return placa.replace("-", "").toUpperCase();
    }

    private Entregador higienizarPlaca(Entregador e) {
        return new Entregador(e.getNome(), higienizarPlaca(e.getPlacaDoVeiculo()));
    }

    public void adicionarEntregador(Entregador entregador) {
        this.entregadores.add(higienizarPlaca(entregador));
    }

    public Entregador buscarEntregadorPelaPlaca(String placa) {
        String searchPlaca = higienizarPlaca(placa);
        for (int i = 0; i < entregadores.size(); i += 1) {
            Entregador entregador = this.entregadores.get(i);
            if (entregador.getPlacaDoVeiculo().equals(searchPlaca)) {
                return entregador;
            }
        }
        return null; // Não existe entregador com esta placa
    }

    public ArrayList<Entregador> getEntregadores() {
        return entregadores;
    }

    public Entregador buscarEntregador(int index) throws IndexOutOfBoundsException {
        return this.entregadores.get(index);
    }

    public void printEntregadores() {
        int count = 0;
        for (int i = 0; i < entregadores.size(); i += 1) {
            count++;
            Entregador entregador = this.buscarEntregador(i);
            System.out.print(count + ". ");
            System.out.println(entregador);
        }
    }
}
