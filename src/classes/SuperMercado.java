package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Esta classe permite adicionar itens a um determinado supermercado e calcula o
 * preco das compras no mesmo
 *
 */
public class SuperMercado implements Serializable{

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
	
	public void deletaItem(int id) {
		if (this.produtos.containsKey(id)) {
			this.produtos.remove(id);
		}
	}
	
	public String getNome() {
		return this.nomeSupermercado;
	}

	/**
	 * Metodo responsavel para verificar se existe produto no supermercado
	 * 
	 * @param id - recebe o id(inteiro) representativo de um produto.
	 * 
	 * @return boolean, true caso tenha, caso contrário, false.
	 */
	public boolean verificaExistProduto(int id) {
		if (this.produtos.containsKey(id)) {
			return true;
		}

		return false;
	}

	public double retornaValorDeItemParaLista(int idItem, int quantidadeItem) {
		return this.produtos.get(idItem) * quantidadeItem;
	}
	public Set<Integer> test() {
		return this.produtos.keySet();
	}
}
