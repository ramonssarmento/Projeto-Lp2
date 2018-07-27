package classes;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Esta classe permite adicionar itens a um determinado supermercado e calcula o
 * preco das compras no mesmo
 *
 */
public class SuperMercado {

	private HashMap<Integer, Double> produtos;
	private String nomeSupermercado;
	private boolean comprasParciais;

	/**
	 * Construtor que inicializa os atributos de um supermercado
	 * 
	 * @param nomeSupermercado
	 */
	public SuperMercado(String nomeSupermercado) {
		this.nomeSupermercado = nomeSupermercado;
		this.produtos = new HashMap<Integer, Double>();
		this.comprasParciais = false;
	}

	/**
	 * Adiciona um item a lista de produtos do supermercado
	 * 
	 * @param id
	 * @param preco
	 */
	public void adicionarItem(int id, double preco) {
		this.produtos.put(id, preco);
	}

	/**
	 * Calcula o preco das compras se feitas em determinado supermercado. A variavel
	 * compras parciais esta servindo para dizer se o supermercado possui todos os
	 * produtos desejados Ou se esta retornando apenas um valor parcial.
	 * 
	 * @param ids
	 * @return
	 */
	public double calculaPrecoCompras(ArrayList<Integer> ids) {
		double precoCompras = 0;

		for (int id : ids) {
			if (this.produtos.containsKey(id)) {
				precoCompras += this.produtos.get(id);
			} else {
				this.comprasParciais = true;
			}
		}

		return precoCompras;
	}
	/**
	 * Verifica se o supermercado possui determinado item
	 * @return um booleando relatando se ha ou nao presenca de tal produto no supermercado
	 */
	public boolean getComprasParciais() {
		boolean retorno = this.comprasParciais;
		this.comprasParciais = false;

		return retorno;

	}
	
	/**
	 * Metodo responsavel para verificar se existe produto no supermercado
	 * 
	 * @param id, recebe o id(inteiro) representativo de um produto.
	 * @return
	 * 		boolean, true caso tenha, caso contrário, false.
	 */
	public boolean verificaExistProduto(int id) {
		for(Integer ids: produtos.keySet()) {
			if(id == ids) {
				return true;
			}
		}
		return false;
	}
}
