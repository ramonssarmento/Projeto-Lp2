package classes;

import java.io.Serializable;

import interfaces.ProdutoListaOrdenavel;

public class ProdutoLista implements ProdutoListaOrdenavel, Serializable {

	private int id;
	private Item produto;
	private int quantidade;
	private String nome, categoria;

	/**
	 * Construtor da classe
	 * 
	 * @param produto
	 *            item do produto lista
	 * @param quantidade
	 *            do item
	 */
	public ProdutoLista(Item produto, int quantidade) {

		this.produto = produto;
		this.id = produto.getId();
		this.quantidade = verificaQuantidade(quantidade);
		this.nome = produto.getNome();
		this.categoria = produto.getCategoria();
	}

	public void setQuantidade(int novaQuantidade) {

		this.quantidade = novaQuantidade;
	}

	public int getId() {

		return this.id;

	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public String toString() {

		return String.format("%d %s", this.quantidade, this.produto.getDescricao());
	}

	private int verificaQuantidade(int quantidade) {
		if (quantidade <= 0) {
			throw new IllegalArgumentException("Quantidade invalida!");
		}

		return quantidade;
	}

}
