
import java.util.Locale;

import base.Cartao;
import base.Cheque;
import base.Cliente;
import base.Entregador;
import base.Funcionario;
import base.Pedido;
import base.SituacaoPedidoEnum;
import repository.CardapioRepository;
import repository.ClienteRepository;
import repository.EntregadorRepository;
import repository.FuncionarioRepository;
import repository.PedidoRepository;
import view.Console;
import view.frames.MainWindow;

public class AppMain {
	public static void testSeed() throws Exception {
		EntregadorRepository entregadorRepo = EntregadorRepository.getInstancia();
		Entregador manu = new Entregador("Manu", "AAA1111");
		entregadorRepo.adicionarEntregador(manu);

		FuncionarioRepository funcionarioRepo = FuncionarioRepository.getInstancia();
		Funcionario danilo = new Funcionario("Danilo", "123456789");
		funcionarioRepo.adicionarFuncionario(danilo);

		ClienteRepository clientRepo = ClienteRepository.getInstancia();
		Cliente fulano = new Cliente("Fulano Pedidor", "Rua da Fome", "(85) 99999-9999", "Perto do Azilados");
		Cliente ciclano = new Cliente("Ciclano Pedidor", "Rua da Fome 2", "(85) 99999-9998", "Perto do Bobs");

		System.out.println("fulano ID: " + fulano.getID());
		System.out.println("ciclano ID: " + ciclano.getID());
		clientRepo.adicionarCliente(fulano);
		clientRepo.adicionarCliente(ciclano);

		fulano.setAtivo(true);
		ciclano.setAtivo(false);

		fulano.setCartao(new Cartao("901239012930", "123"));
		ciclano.setCheque(new Cheque("12123123123", "123123123", "1231231", "123123123"));

		CardapioRepository cardRepo = CardapioRepository.getInstancia();
		PedidoRepository pedidoRepo = PedidoRepository.getInstancia();
		Pedido pedido = new Pedido(fulano);

		pedido.adicionarItem(10, cardRepo.buscarItem(0));
		pedido.adicionarItem(1, cardRepo.buscarItem(2));


		pedido.setSituacao(SituacaoPedidoEnum.PENDENTE);

		pedidoRepo.adicionarPedido(pedido);
	}

	public static void main(String[] args) throws Exception {
		Locale.setDefault(new Locale("pt", "BR"));
		testSeed(); // TODO: Comentar esta linha para começar com a base limpa
		new MainWindow();
	}
}
