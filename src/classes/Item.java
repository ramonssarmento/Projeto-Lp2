package classes;

import java.util.HashMap;

public class Item {
	/**
	 * Mapa para o preco do item no supermercado
	 */
	protected HashMap<String, Double> precos;
	
	protected String nome;	
	protected String categoria;
	
	public Item(String nome, String categoria, String localDeCompra, double preco) {
		this.nome = nome;
		this.categoria = categoria;
		precos = new HashMap<>();
		precos.put(localDeCompra, preco);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	

}
