package classes;

import java.util.HashMap;

import interfaces.ItemCompravel;

public abstract class Item implements ItemCompravel {
	/**
	 * Mapa para o preco do item no supermercado
	 */
	protected HashMap<String, Double> precos;

	protected final int ID;
	protected String nome;
	protected String categoria;
	protected double menorPreco;

	public Item(int ID, String nome, String categoria, String localDeCompra, double preco) {
		validaItem(nome, categoria, localDeCompra, preco);
		this.ID = ID;
		this.nome = nome;
		this.categoria = categoria;
		this.precos = new HashMap<>();
		this.precos.put(localDeCompra, preco);
		this.menorPreco = preco;
	}

	public void adicionaPrecoItem(String localDeCompra, double preco) {
		validaAdicionaPreco(localDeCompra, preco);
		this.precos.put(localDeCompra, preco);
		if (preco < this.menorPreco) {
			this.menorPreco = preco;
		}
	}

	public boolean atualizaItem(String atributo, String novoValor) {
		validaAtualizaItem(atributo, novoValor);
		switch (atributo) {
		case "nome":
			this.nome = novoValor;
			return true;
		case "categoria":
			this.categoria = novoValor;
			return true;
		default:
			return false;
		}
	}

	public double getMenorPreco() {
		return this.menorPreco;
	}

	public String getNome() {
		return this.nome;
	}

	private void validaAtualizaItem(String atributo, String novoValor) {
		if (atributo == null || atributo.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao pode ser vazio ou nulo.");
		} else if (novoValor == null || novoValor.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro na atualizacao de item: novo valor de atributo nao pode ser vazio ou nulo.");
		}
	}

	private void validaAdicionaPreco(String localDeCompra, double preco) {
		if (localDeCompra == null || localDeCompra.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro no cadastro de preco: local de compra nao pode ser vazio ou nulo.");
		} else if (preco <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de preco: preco de item invalido.");
		}
	}

	private void validaItem(String nome, String categoria, String localDeCompra, double preco) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de item: nome nao pode ser vazio ou nulo.");
		} else if (categoria == null || categoria.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de item: categoria nao pode ser vazia ou nula.");
		} else if (!categoria.equals("limpeza") && !categoria.equals("alimento industrializado")
				&& !categoria.equals("higiene pessoal") && !categoria.equals("alimento nao industrializado")) {
			throw new IllegalArgumentException("Erro no cadastro de item: categoria nao existe.");
		} else if (localDeCompra == null || localDeCompra.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de item: local de compra nao pode ser vazio ou nulo.");
		} else if (preco <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de item: preco de item invalido.");
		}
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
