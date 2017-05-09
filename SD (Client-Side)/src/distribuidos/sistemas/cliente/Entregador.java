package distribuidos.sistemas.cliente;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import distribuidos.sistemas.cliente.servicos.EntregadorInterface;
import distribuidos.sistemas.cliente.servicos.EntregadorRede;
import distribuidos.sistemas.cliente.servicos.local.Somar;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 7 de mai de 2017
 * Time: 20:22:42
 *
 */
public class Entregador {

	public static final String SERVIDOR = "192.168.29.100";
	public static final int PORTA = 5588;

	/**/

	private Map<String, EntregadorInterface> local;

	protected Entregador() {
		this.local = new HashMap<String, EntregadorInterface>();
	}

	protected void iniciar() {
		this.local.put("somar", new Somar());
	}

	protected void executar(String[] args) {
		if ((args.length > 0)) { // Verifica se � v�lido
			Pedido pedido = new Pedido(args[0]); // Cria um novo pedido
			for (int i = 1; i < args.length; ++i) {
				pedido.add(args[i]); // Adiciona os argumentos
			}

			EntregadorInterface dispatcher = this.getEntregador(pedido.getNome());
			if ((dispatcher == null)) { // Usu�rio sem internet ou servi�o nao existe
				System.out.println(" [" + pedido.getNome() + "] > N�o foi poss�vel processar, verifique sua conex�o e tente novamente.");
			} else { // Executa o servi�o (transpar�ncia de localiza��o)
				String resultado = dispatcher.executar(pedido);
				System.out.println(" [" + pedido.getNome() + "] > " + resultado);
			}
		}
	}

	private EntregadorInterface getEntregador(String nome) {
		try {
			Socket socket = new Socket(); // Tenta conectar com os sevidor
			socket.connect(new InetSocketAddress(Entregador.SERVIDOR, Entregador.PORTA), 500);
			return new EntregadorRede(socket);
		} catch (Exception e) {
			return this.local.get(nome); // Tenta processar localmente
		}
	}

}
