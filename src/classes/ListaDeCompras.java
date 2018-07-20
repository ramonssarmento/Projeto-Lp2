package classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

import interfaces.OrdenaItemLista;
import javafx.collections.transformation.SortedList;


public class ListaDeCompras {

	private String descritor;
	private String data;
	private HashMap<Integer, ProdutoLista> produtosLista;
	private ArrayList<String> saidaOrdenada;

	public ListaDeCompras(String descritor, String data) {

		this.descritor = descritor;
		this.data = data;
		this.produtosLista = new HashMap<>();
		this.saidaOrdenada = new ArrayList();
		
	}

	public void criaProdutoLista(Item item, int quantidade) {

		ProdutoLista produto = new ProdutoLista(item, quantidade);
		adicionaProdutoNaLista(produto);
	}

	private void adicionaProdutoNaLista(ProdutoLista produto) {

		int id = produto.getId();
		if (!this.produtosLista.containsKey(id)) {
			this.produtosLista.put(id, produto);
		}
		
		else {
			throw new IllegalArgumentException("Esse produto ja foi adicionado!");
		}
		
		ordenaSaida();
	}
	
	public void deletaProdutoLista(int itemId) {
		
		verificaPresencaNaLista(itemId);
		
		this.produtosLista.remove(itemId);
		ordenaSaida();
	}
	
	public void atualizaProduto(int itemId, String operacao, int quantidade) {
		
		verificaPresencaNaLista(itemId);
		
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
		
		ordenaSaida();
	}
	
	public String pesquisaCompraEmLista(int itemId) {
		
		verificaPresencaNaLista(itemId);
		
		return this.produtosLista.get(itemId).toString();
		
	}
	
	public String retornaItemPosicao(int itemPosicao) {
		verificaPosicaoItem(itemPosicao);
		
		return this.saidaOrdenada.get(itemPosicao);
	}

	private void verificaPresencaNaLista(int id) {
		if (this.produtosLista.containsKey(id)) {
			throw new IllegalArgumentException("Esse Item nao esta na lista!");
		}
	}
	
	private void verificaPosicaoItem(int posicao) {
		if (posicao < 0 || posicao >= this.saidaOrdenada.size()) {
			throw new IllegalArgumentException("Posicao invalida");
		}
	}
	
	private void ordenaSaida() {
		
		this.saidaOrdenada.clear();
		List<ProdutoLista> produtos = new LinkedList<>();
		produtos.addAll(this.produtosLista.values());

		Collections.sort(produtos, new OrdenaItemLista());
		
		for (ProdutoLista produto : produtos) {
			this.saidaOrdenada.add(produto.toString());
		}
		
	}

	public Set<Integer> getIdsProdutos() {

		return this.produtosLista.keySet();
	}

	public String toString() {
		
		String saida = "";
		
		for (String saidaProduto : this.saidaOrdenada) {
			saida += saidaProduto + System.lineSeparator();
		}
		
		return saida.trim();
	}
	
}
