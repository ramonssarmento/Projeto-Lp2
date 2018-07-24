package controllers;

import java.util.HashMap;

import classes.SuperMercado;

public class ControllerSupermercados {
	
	private HashMap<String, SuperMercado> superMercados;
	
	public ControllerSupermercados() {
		this.superMercados = new HashMap<>();
	}
	
	/**
	 * Verifica se determinado supermercado contém tal produto
	 * 
	 * @param nomeSupermercado,
	 *            nome do supermercado o qual deseja procurar @return, um boolenado
	 *            true se houver, false se não houver
	 */
	private boolean verificaPresencaDeSupermercado(String nomeSupermercado) {
		if (this.superMercados.containsKey(nomeSupermercado)) {
			return true;
		}

		return false;
	}

	/**
	 * Adiciona determinado produto ao supermercado
	 * 
	 * @param localDeCompra,
	 *            supermercado o qual o produto sera adicionado
	 * @param preco,
	 *            preco do produto
	 */
	public void adicionaItemNoSupermercado(String localDeCompra, double preco, int id) {
		if (verificaPresencaDeSupermercado(localDeCompra)) {
			this.superMercados.get(localDeCompra).adicionarItem(id, preco);
		}

		else {

			SuperMercado superMercado = new SuperMercado(localDeCompra);
			this.superMercados.put(localDeCompra, superMercado);
			this.superMercados.get(localDeCompra).adicionarItem(id, preco);
		}
	}
}
