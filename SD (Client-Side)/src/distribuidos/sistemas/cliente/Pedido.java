package distribuidos.sistemas.cliente;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 7 de mai de 2017
 * Time: 20:30:44
 *
 */
public class Pedido {

	private String nome;
	private List<String> args;

	public Pedido(String nome) {
		this.args = new ArrayList<String>();
		this.nome = nome.toLowerCase();
	}

	public void add(String arg) {
		this.args.add(arg);
	}

	public String getNome() {
		return this.nome;
	}

	public List<String> getArgs() {
		return this.args;
	}

	@Override
	public String toString() {
		StringBuilder packet = new StringBuilder();
		packet.append("%");
		packet.append(this.getNome());
		packet.append("%");

		for (String arg : this.getArgs()) {
			packet.append(arg);
			packet.append("%");
		}

		packet.append('\0'); // Fim da mensagem
		return packet.toString();
	}

}
