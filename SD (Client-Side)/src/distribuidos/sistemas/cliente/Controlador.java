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

	private Entregador entrador;

	public Controlador() {
		this.entrador = new Entregador();
	}

	public void iniciar() {
		this.entrador.iniciar();
		System.out.println(" Escreva ? para ajuda.");
		System.out.print(" > ");

		Scanner scanner = new Scanner(System.in);
		String comando;

		while ((comando = scanner.nextLine()).equals("sair") == false) {
			if ((comando.equals("?"))) {
				// TODO Mostrar ajuda
			} else {
				comando = comando.trim();
				if ((comando.isEmpty())) { // String vazia
					System.out.println(" > Entrada inválida");
				} else {
					String[] entrada = comando.split(" ");
					this.entrador.executar(entrada); // Pega um serviço
				}
			}

			System.out.print(" > ");
		}

		scanner.close();
	}

}
