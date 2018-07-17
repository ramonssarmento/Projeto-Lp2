package classes;

import java.util.HashMap;

public class ListaDeCompras {
	
	private String descritor, data;
	private HashMap<Integer, ProdutoLista> produtosLista;
	
	public ListaDeCompras(String descritor, String data) {
		
		this.descritor = descritor;
		this.data = data;
		this.produtosLista = new HashMap<>();
		
	}
	
	public void adicionaProdutoNaLista(ProdutoLista produto) {
		
		int id = produto.getId();
		if (!this.produtosLista.containsValue(produto)) {
			this.produtosLista.put(id, produto);
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
