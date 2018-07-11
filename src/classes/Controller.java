package classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import interfaces.ItemCompravel;
import interfaces.OrdenaItemMenorPreco;

public class Controller {
	private int id;
	private HashMap<Integer, Item> itens;

	public Controller() {
		this.id = 0;
		this.itens = new HashMap<>();
	}

	public int adicionaItemPorQtd(String nome, String categoria, int qtd, String unidadeDeMedida, String localDeCompra,
			double preco) {
		this.id++;
		ItemQtd novoItem = new ItemQtd(this.id, nome, categoria, qtd, unidadeDeMedida, localDeCompra, preco);
		if (this.itens.containsValue(novoItem)) {
			throw new IllegalArgumentException("Item ja adicionado");
		}
		this.itens.put(this.id, novoItem);
		return this.id;

	}

	public int adicionaItemPorQuilo(String nome, String categoria, double kg, String localDeCompra, double preco) {
		this.id++;
		ItemQuilo novoItem = new ItemQuilo(this.id, nome, categoria, kg, localDeCompra, preco);
		if (this.itens.containsValue(novoItem)) {
			throw new IllegalArgumentException("Item ja adicionado");
		}
		this.itens.put(this.id, novoItem);
		return this.id;

	}

	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String localDeCompra, double preco) {
		this.id++;
		ItemUnidade novoItem = new ItemUnidade(this.id, nome, categoria, unidade, localDeCompra, preco);
		if (this.itens.containsValue(novoItem)) {
			throw new IllegalArgumentException("Item ja adicionado");
		}
		this.itens.put(this.id, novoItem);
		return this.id;

	}

	public String exibeItem(int id) {
		validaListagem(id);
		return itens.get(id).toString();
	}

	public void atualizaItem(int id, String atributo, String novoValor) {
		validaAtualizacao(id);
		itens.get(id).atualizaItem(atributo, novoValor);
	}

	public void adicionaPrecoItem(int id, String localDeCompra, double preco) {
		validaCadastroPreco(id);
		itens.get(id).adicionaPrecoItem(localDeCompra, preco);
	}

	public void deletaItem(int id) {
		// Futuramente colocar um validador de id (n�o possui esse caso)
		itens.remove(id);
	}

	public String getItem(int posicao) {
		if (posicao >= itens.size() || posicao < 0) {
			return "";
		}
		List<ItemCompravel> novaLista = new ArrayList<>(this.itens.values());
		Collections.sort(novaLista);
		return novaLista.get(posicao).toString();
	}

	public String getItemPorCategoria(String categoria, int posicao) {
		verificaCategoria(categoria);
		List<ItemCompravel> novaLista = new ArrayList<>();
		for (int id : this.itens.keySet()) {
			if (this.itens.get(id).getCategoria().equals(categoria)) {
				novaLista.add(this.itens.get(id));
			}
		}

		if (posicao >= novaLista.size() || posicao < 0) {
			return "";
		}
		Collections.sort(novaLista);
		return novaLista.get(posicao).toString();
	}

	public String getItemPorMenorPreco(int posicao) {
		if (posicao >= itens.size() || posicao < 0) {
			return "";
		}
		List<ItemCompravel> novaLista = new ArrayList<>(this.itens.values());
		Collections.sort(novaLista, new OrdenaItemMenorPreco());
		return novaLista.get(posicao).toString();
	}

	public String getItemPorPesquisa(String strPesquisada, int posicao) {
		List<ItemCompravel> novaLista = new ArrayList<>();
		for (int id : this.itens.keySet()) {
			if (this.itens.get(id).getNome().toLowerCase().contains(strPesquisada.toLowerCase())) {
				novaLista.add(this.itens.get(id));
			}
		}

		if (posicao >= novaLista.size() || posicao < 0) {
			return "";
		}
		Collections.sort(novaLista);
		return novaLista.get(posicao).toString();
	}

	public String adicionaListaDeCompras(String descritorLista) {
		// TODO Auto-generated method stub
		return null;
	}

	// pode ser adicionado quantidade double,devo sobrecarregar o metodo?
	public void adicionaCompraALista(String descritorLista, int quantidade, int itemId) {
		// TODO Auto-generated method stub
	}

	public void finalizarListaDeCompras(String descritorLista, String localDaCompra, int valorFinalDaCompra) {
		// TODO Auto-generated method stub
	}

	public String pesquisaCompraEmLista(String descritorLista, int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizaCompraDeLista(String descritorLista, int itemId, int quantidade) {
		// TODO Auto-generated method stub
	}

	public String getItemLista(String descritorLista, int posicaoItem) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deletaCompraDeLista(String descritorLista, int itemId) {
		// TODO Auto-generated method stub
	}

	public String imprimirListaDeCompras(String descritorLista) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getItemListaPorData(String data, int posicaoLista) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getItemListaPorItem(int id, String posicaoLista) {
		// TODO Auto-generated method stub
		return null;
	}

	private void validaListagem(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Erro na listagem de item: id invalido.");
		} else if (!this.itens.containsKey(id)) {
			throw new IllegalArgumentException("Erro na listagem de item: item nao existe.");
		}
	}

	private void validaAtualizacao(int id) {
		if (!this.itens.containsKey(id)) {
			throw new IllegalArgumentException("Erro na atualizacao de item: item nao existe.");
		}
	}

	private void validaCadastroPreco(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de preco: id de item invalido.");
		} else if (!this.itens.containsKey(id)) {
			throw new IllegalArgumentException("Erro no cadastro de preco: item nao existe.");
		}
	}

	private boolean verificaCategoria(String categoria) {
		if (categoria.equals("limpeza") || categoria.equals("alimento industrializado")
				|| categoria.equals("higiene pessoal") || categoria.equals("alimento nao industrializado")) {
			return true;
		}
		throw new IllegalArgumentException("Erro na listagem de item: categoria nao existe.");
	}
}
