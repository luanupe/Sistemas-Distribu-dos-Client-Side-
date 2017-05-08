package distribuidos.sistemas.cliente;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import distribuidos.sistemas.cliente.servicos.DispatcherInterface;
import distribuidos.sistemas.cliente.servicos.DispatcherRede;
import distribuidos.sistemas.cliente.servicos.local.Somar;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 7 de mai de 2017
 * Time: 20:22:42
 *
 */
public class Dispatcher {

	public static final String SERVIDOR = "192.168.29.100";
	public static final int PORTA = 5588;

	/**/

	private Map<String, DispatcherInterface> local;

	protected Dispatcher() {
		this.local = new HashMap<String, DispatcherInterface>();
	}

	protected void iniciar() {
		this.local.put("somar", new Somar());
	}

	protected void executar(String[] args) {
		if ((args.length > 0)) {
			Pedido pedido = new Pedido(args[0]);
			for (int i = 1; i < args.length; ++i) {
				pedido.add(args[i]);
			}

			DispatcherInterface dispatcher = this.getDispatcher(pedido.getNome());
			if ((dispatcher == null)) {
				System.out.println(" [" + pedido.getNome() + "] > Não foi possível processar, verifique sua conexão e tente novamente.");
			} else {
				String resultado = dispatcher.executar(pedido);
				System.out.println(" [" + pedido.getNome() + "] > " + resultado);
			}
		}
	}

	private DispatcherInterface getDispatcher(String nome) {
		try {
			Socket socket = new Socket();
			socket.connect(new InetSocketAddress(Dispatcher.SERVIDOR, Dispatcher.PORTA), 500);
			return new DispatcherRede(socket);
		} catch (Exception e) {
			return this.local.get(nome);
		}
	}

}
