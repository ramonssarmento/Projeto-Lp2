package classes;

public class ProdutoLista {
	
	private int id;
	private Item produto;
	private int quantidade;
	
	
	public ProdutoLista(Item produto, int quantidade) {
		
		this.produto = produto;
		this.id = produto.getId();
		this.quantidade = quantidade;
		
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
	
	public toString() {
	
		return String.format("%i %s, %s, %s", (this.quantidade, this.produto.getNome(), this.produto.getCategoria(), this.produto.getQuantidade()));
	}
	
}
