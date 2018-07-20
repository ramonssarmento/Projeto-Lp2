package classes;

import interfaces.ProdutoListaOrdenavel;

public class ProdutoLista implements ProdutoListaOrdenavel {

	private int id;
	private Item produto;
	private int quantidade;
	private String nome, categoria;

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
