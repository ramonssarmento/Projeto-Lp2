package classes;

public class ProdutoLista {

	private int id;
	private Item produto;
	private int quantidade;

	public ProdutoLista(Item produto, int quantidade) {

		this.produto = produto;
		this.id = produto.getId();
		this.quantidade = verificaQuantidade(quantidade);

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
