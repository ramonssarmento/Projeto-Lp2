package classes;

import java.util.ArrayList;
import java.util.HashMap;

public class SuperMercado {
	
	private HashMap<Integer, Double> produtos;
	private String nomeSupermercado;
	private boolean comprasParciais;
	
	public SuperMercado(String nomeSupermercado) {
		this.nomeSupermercado = nomeSupermercado;
		this.produtos = new HashMap<Integer, Double>();
		this.comprasParciais = false;
	}
	
	public void adicionarItem(int id, double preco) {
		this.produtos.put(id, preco);
	}
	
	public double calculaPrecoCompras(ArrayList<Integer> ids) {
		double precoCompras = 0;
		
		for (int id : ids) {
			if (this.produtos.containsKey(id)) {
				precoCompras += this.produtos.get(id);
			}
			
			// Essa variavel esta servindo para dizer se o supermercado possui todos os produtos desejados
			// Ou se está retornando apenas um valor parcial.
			
			else {
				this.comprasParciais = true;
			}
		}
		
		return precoCompras;
	}
	
	/**
	 * A variável comprasParciais é alterada para false, pois se for desejado realizar outra pesquisa de preços
	 * será analisado se todos os produtos estão presentes no supermercado.
	 */
	
	public boolean getComprasParciais() {
		boolean retorno = this.comprasParciais;
		this.comprasParciais = false;
		
		return retorno;
		
	}
}
