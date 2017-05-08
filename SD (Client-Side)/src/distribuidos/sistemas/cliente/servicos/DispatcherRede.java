package distribuidos.sistemas.cliente.servicos;

import java.net.Socket;

import distribuidos.sistemas.cliente.Pedido;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 7 de mai de 2017
 * Time: 20:36:44
 *
 */
public class DispatcherRede implements DispatcherInterface {

	private Socket socket;

	public DispatcherRede(Socket socket) {
		this.socket = socket;
	}

	@Override
	public String executar(Pedido pedido) {
		StringBuilder resposta = new StringBuilder();
		try {
			this.socket.getOutputStream().write(pedido.toString().getBytes());
			int atual;

			while ((atual = this.socket.getInputStream().read()) > 0) {
				resposta.append((char) atual);
			}
		} catch (Exception e) {
			// TODO Reconectar em outro nó? Processar localmente?
		}

		return resposta.toString();
	}

}
