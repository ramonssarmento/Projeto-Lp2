package classes;

import java.util.HashMap;

import interfaces.ItemCompravel;

/**
 * Classe abstrata que permite definir os atributos em comum de "item" e
 * repassar para suas extensões
 *
 */
public abstract class Item implements ItemCompravel {
	/**
	 * Mapa para o preco do item no supermercado
	 */
	private HashMap<String, Double> precos;

	private int id;
	private String nome;
	private String categoria;
	private double menorPreco;

	/**
	 * Construtor que inicializa os atributos de Item e verifica a excecao lancada
	 * para item
	 * 
	 * @param id, identificador unico do produto
	 * @param nome, nome do produto
	 * @param categoria, categoria ao qual o produto pertence
	 * @param localDeCompra, mercado em que o produto esta sendo registrado
	 * @param preco, preco do produto
	 */
	public Item(int id, String nome, String categoria, String localDeCompra, double preco) {
		validaItem(nome, categoria, localDeCompra, preco);
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.precos = new HashMap<>();
		this.precos.put(localDeCompra, preco);
		this.menorPreco = preco;
	}

	/**
	 * Adiciona preco do item no mercado passado
	 * 
	 * @param localDeCompra, mercado o qual o item vai ser adicionado
	 * @param preco, preco do item
	 */
	public void adicionaPrecoItem(String localDeCompra, double preco) {
		validaAdicionaPreco(localDeCompra, preco);
		this.precos.put(localDeCompra, preco);
		if (preco < this.menorPreco) {
			this.menorPreco = preco;
		}
	}

	/**
	 * Atualiza item passando novos atributo
	 * 
	 * @param atributo
	 * @param novoValor
	 */
	public boolean atualizaItem(String atributo, String novoValor) {
		validaAtualizaItem(atributo, novoValor);
		switch (atributo) {
		case "nome":
			this.nome = novoValor;
			return true;
		case "categoria":
			if (verificaCategoria(novoValor)) {
				this.categoria = novoValor;
				return true;
			}
			throw new IllegalArgumentException("Erro na atualizacao de item: categoria nao existe.");
		}

		return false;
	}

	/**
	 * Pega o menor preco do item comparando em todos os mercados que possuem tal
	 * item
	 */
	public double getMenorPreco() {
		return this.menorPreco;
	}

	/**
	 * Pega nome do item
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Pega identificador do item
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Pega a categoria do item
	 */
	public String getCategoria() {
		return this.categoria;
	}
	
	public abstract String getQuantidade();

	public String getPrecos() {
		String result = "";
		for (String supermercados : precos.keySet()) {
			result += String.format("%s, R$ %.2f;", supermercados, precos.get(supermercados));
		}
		return "<" + result + ">";
	}

	@Override
	public String toString() {
		return this.id + ". " + this.nome + ", " + this.categoria;
	}

	/**
	 * Lanca excecoes para atualizar o item
	 * 
	 * @param atributo
	 * @param novoValor
	 */
	protected void validaAtualizaItem(String atributo, String novoValor) {
		if (atributo == null || atributo.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao pode ser vazio ou nulo.");
		} else if (novoValor == null || novoValor.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro na atualizacao de item: novo valor de atributo nao pode ser vazio ou nulo.");
		}
	}

	/**
	 * Lanca excecoes para validar preco do item em determinado mercado
	 * 
	 * @param localDeCompra, supermercado o qual o produto sera adicionado
	 * @param preco, preco do produto
	 */
	private void validaAdicionaPreco(String localDeCompra, double preco) {
		if (localDeCompra == null || localDeCompra.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro no cadastro de preco: local de compra nao pode ser vazio ou nulo.");
		} else if (preco <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de preco: preco de item invalido.");
		}
	}

	/**
	 * Lanca exececoes para validar um item e poder ser cadastrado
	 * 
	 * @param nome, nome do produto
	 * @param categoria, categoria a qual o produto pertence
	 * @param localDeCompra, supermercado o qual o produto sera adicionado
	 * @param preco, preco do produto
	 */
	private void validaItem(String nome, String categoria, String localDeCompra, double preco) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de item: nome nao pode ser vazio ou nulo.");
		} else if (categoria == null || categoria.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de item: categoria nao pode ser vazia ou nula.");
		} else if (!verificaCategoria(categoria)) {
			throw new IllegalArgumentException("Erro no cadastro de item: categoria nao existe.");
		} else if (localDeCompra == null || localDeCompra.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro no cadastro de item: local de compra nao pode ser vazio ou nulo.");
		} else if (preco <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de item: preco de item invalido.");
		}
	}

	/**
	 * Lanca excecoes para validar a categoria do item
	 * 
	 * @param categoria, categoria a qual o produto pertence
	 * @return
	 */
	private boolean verificaCategoria(String categoria) {
		if (categoria.equals("limpeza") || categoria.equals("alimento industrializado")
				|| categoria.equals("higiene pessoal") || categoria.equals("alimento nao industrializado")) {
			return true;
		}
		return false;
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

	@Override
	public int compareTo(ItemCompravel o) {
		if (this.getNome().equals(o.getNome())) {
			return o.getId() - this.getId();
		}
		return this.getNome().compareTo(o.getNome());
	}

}
