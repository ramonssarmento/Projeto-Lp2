package classes;

import java.util.ArrayList;

public class ListaDeCompras {
	
	private String descritor, data;
	private ArrayList<ProdutoLista> produtosLista;
	
	public ListaDeCompras(String descritor, String data) {
		
		this.descritor = descritor;
		this.data = data;
		this.produtosLista = new ArrayList<ProdutoLista>();
		
	}
	
	public void adicionaProdutoNaLista(ProdutoLista produto) {
		
		if (!this.produtosLista.contains(produto)) {
			this.produtosLista.add(produto);
		}
		
		else {
			throw new IllegalArgumentException("Esse produto já foi adicionado!");
		}
	}
	
	public String pesquisaCompraEmLista(int itemId) {
		if () {
			throw
		}
	}
	
	public ArrayList<Integer> getIdsProdutos(){
		
		ArrayList<Integer> ids = new ArrayList<Integer>();
		
		for(ProdutoLista produto : this.produtosLista) {
			
			ids.add(produto.getId());
		}
		
		return ids;
	}
	
	
	
}
