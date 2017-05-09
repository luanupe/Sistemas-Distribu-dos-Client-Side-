package distribuidos.sistemas.cliente.servicos.local;

import distribuidos.sistemas.cliente.Pedido;
import distribuidos.sistemas.cliente.servicos.EntregadorInterface;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 7 de mai de 2017
 * Time: 20:50:04
 *
 */
public class Somar implements EntregadorInterface {

	@Override
	public String executar(Pedido pedido) {
		double resultado = 0;
		for (String valor : pedido.getArgs()) {
			try {
				resultado += Double.parseDouble(valor);
			} catch (NumberFormatException e) {
				// Argumento n�o � um n�mero...
			}
		}
		return Double.toString(resultado);
	}

}
