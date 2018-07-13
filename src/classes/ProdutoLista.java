package classes;

public class ProdutoLista {
	
	private int id;
	private String nome, categoria, unidadeMedida;
	private double quantidade;
	
	
	public ProdutoLista(int id, String nome, String categoria, double quantidade, String unidadeMedida) {
		
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.quantidade = quantidade;
		this.unidadeMedida = unidadeMedida;
	}
	
	public void setQuantidade(double novaQuantidade) {
		
		this.quantidade = novaQuantidade;
	}
	
	public int getId() {
		
		return this.id;
	
	}
}
