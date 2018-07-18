package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class ListaDeCompras {

	private String descritor, data;
	private HashMap<Integer, ProdutoLista> produtosLista;

	public ListaDeCompras(String descritor, String data) {

		this.descritor = descritor;
		this.data = data;
		this.produtosLista = new HashMap<>();

	}

	public void criaProdutoLista(Item item, int quantidade) {

		ProdutoLista produto = new ProdutoLista(item, quantidade);
		adicionaProdutoNaLista(produto);

	}

	private void adicionaProdutoNaLista(ProdutoLista produto) {

		int id = produto.getId();
		if (!verificaPresencaNaLista(id)) {
			this.produtosLista.put(id, produto);
		}

		else {
			throw new IllegalArgumentException("Esse produto ja foi adicionado!");
		}
	}
	
	public void deletaProdutoLista(int id) {
		
		if (!verificaPresencaNaLista(id)) {
			throw new IllegalAccessError("Esse produto não está na lista!");
		}
		
		this.produtosLista.remove(id);
	}
	
	public void atualizaProduto(int itemId, String operacao, int quantidade) {
		
		if (operacao.equals("adiciona")) {
			int novaQuantidade = (this.produtosLista.get(itemId).getQuantidade()) + quantidade;
			this.produtosLista.get(itemId).setQuantidade(novaQuantidade);
		}
		
		else if (operacao.equals("diminui")) {
			int novaQuantidade = (this.produtosLista.get(itemId).getQuantidade()) + quantidade;
			
			if(novaQuantidade <= 0) {
				this.produtosLista.remove(itemId);
			}
			
			else {
				this.produtosLista.get(itemId).setQuantidade(novaQuantidade);
			}
		}
		
		else {
			throw new IllegalArgumentException("Operacao invalida!");
		}
	}
	
	public String pesquisaCompraEmLista(int itemId) {
		
		if (verificaPresencaNaLista(itemId)) {
			return this.produtosLista.get(itemId).toString();
		}
		
		throw new IllegalAccessError("Esse Item nao esta na lista!");
	}

	private boolean verificaPresencaNaLista(int id) {
		if (this.produtosLista.containsKey(id)) {
			return true;
		}

		return false;
	}


	public Set<Integer> getIdsProdutos() {

		return this.produtosLista.keySet();
	}

	public String toString() {
		
		String saida = "";
		
		for (ProdutoLista produto : this.produtosLista) {
			
		}
	}
}
