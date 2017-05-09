package distribuidos.sistemas.cliente;

import java.util.Scanner;

/**
 *
 * Author: Luan Augusto, LAF
 * Date: 7 de mai de 2017
 * Time: 20:22:30
 *
 */
public class Controlador {

	public final static double VERSAO = 1.0;

	/* */

	private Entregador dispatcher;

	public Controlador() {
		this.dispatcher = new Entregador();
	}

	public void iniciar() {
		this.dispatcher.iniciar();
		System.out.println(" Escreva ? para ajuda.");
		System.out.print(" > ");

		Scanner scanner = new Scanner(System.in);
		String comando;

		while ((comando = scanner.nextLine()).equals("sair") == false) {
			if ((comando.equals("?"))) {
				// TODO Mostrar ajuda
			} else {
				comando = comando.trim();
				if ((comando.isEmpty())) {
					System.out.print(" > Entrada inválida");
				} else {
					String[] entrada = comando.split(" ");
					this.dispatcher.executar(entrada);
				}
			}

			System.out.print(" > ");
		}

		scanner.close();
	}

}
