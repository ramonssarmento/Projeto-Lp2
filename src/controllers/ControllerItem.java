package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import classes.Item;
import classes.ItemQtd;
import classes.ItemQuilo;
import classes.ItemUnidade;
import interfaces.ItemCompravel;
import interfaces.OrdenaItemMenorPreco;

/**
 * Classe controladora dos itens, permite adicionar os itens em suas respectivas
 * categorias, exibi-los, atualizar/deletar, recuperar informacoes.
 */

public class ControllerItem implements Serializable{
	private int id;
	private HashMap<Integer, Item> itens;

	/**
	 * Construtor inicializa o idenficador unico como zero, e o mapa que será
	 * armazenado os itens
	 */
	public ControllerItem() {
		this.id = 0;
		this.itens = new HashMap<>();
	}

	/**
	 * Adiciona um item que tem seu preco calculado por quantidade ao mapa de itens,
	 * depois de ter verifacado as excecoes, para saber se a aquisição do novo item
	 * era valida, e em seguida lhe é dado um identificador unico
	 * 
	 * @param nome,
	 *            representa nome do item
	 * @param categoria,
	 *            representa categoria do item
	 * @param qtd,
	 *            quantidade do item
	 * @param unidadeDeMedida,
	 *            unidade de medida do item (kg, g, mg)
	 * @param localDeCompra,
	 *            supermercado o qual o item vai ser cadastrad
	 * @param preco,
	 *            preco do produto em determinado supermercado
	 * @return identificador unico gerado para o item
	 */
	public int adicionaItemPorQtd(String nome, String categoria, int qtd, String unidadeDeMedida, String localDeCompra,
			double preco) {

		ItemQtd novoItem = new ItemQtd(this.id + 1, nome, categoria, qtd, unidadeDeMedida, localDeCompra, preco);
		if (this.itens.containsValue(novoItem)) {
			throw new IllegalArgumentException("Erro no cadastro de item: item ja cadastrado no sistema.");
		}
		this.id++;
		this.itens.put(this.id, novoItem);

		return this.id;

	}

	/**
	 * Adiciona um item que tem seu preco calculado por quilo ao mapa de itens,
	 * depois de ter verifacado as excecoes, para saber se a aquisição do novo item
	 * era valida, e em seguida lhe é dado um identificador unico
	 * 
	 * @param nome,
	 *            nome do produto a ser adicionado
	 * @param categoria,
	 *            categoria do produto que vai ser adicionado
	 * @param kg,
	 *            unidade de medida do produto, que será usada no calculo do valor
	 * @param localDeCompra,
	 *            supermercado o qual o produto sera cadastrado
	 * @param preco,
	 *            preco do produto no determinado supermercado
	 * @return identificador unico gerado para o item
	 */
	public int adicionaItemPorQuilo(String nome, String categoria, double kg, String localDeCompra, double preco) {

		ItemQuilo novoItem = new ItemQuilo(this.id + 1, nome, categoria, kg, localDeCompra, preco);
		if (this.itens.containsValue(novoItem)) {
			throw new IllegalArgumentException("Erro no cadastro de item: item ja cadastrado no sistema.");
		}
		this.id++;
		this.itens.put(this.id, novoItem);

		return this.id;

	}

	/**
	 * Adiciona um item que tem seu preco calculado por unidade ao mapa, depois de
	 * ter verifacado as excecoes, para saber se a aquisição do novo item era
	 * valida, e em seguida lhe é dado um identificador unico
	 * 
	 * @param nome,
	 *            nome do produto a ser adicionado
	 * @param categoria,
	 *            categoria do produto que vai ser adicionado
	 * @param unidade,
	 *            produto é comprado por unidade
	 * @param localDeCompra,
	 *            supermercado onde o produto é comprado
	 * @param preco,
	 *            preco do produto em determinado supermercado
	 * @return identificador unico gerado para o item
	 */
	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String localDeCompra, double preco) {

		ItemUnidade novoItem = new ItemUnidade(this.id + 1, nome, categoria, unidade, localDeCompra, preco);
		if (this.itens.containsValue(novoItem)) {
			throw new IllegalArgumentException("Erro no cadastro de item: item ja cadastrado no sistema.");
		}
		this.id++;
		this.itens.put(this.id, novoItem);

		return this.id;

	}

	/**
	 * Exibe um item a partir de seu identificador
	 * 
	 * @param id,
	 *            identificador unico do produto
	 * @return representacao do item em string
	 */
	public String exibeItem(int id) {
		return itens.get(id).toString();
	}

	/**
	 * Altera os atributos de um item, e verifica a excecao para saber se a
	 * atualizacao é valida
	 * 
	 * @param id,
	 *            identificador unico do item
	 * @param atributo,
	 *            atributo do item que sera mudado
	 * @param novoValor,
	 *            novo valor do atributo que sera passado
	 */
	public void atualizaItem(int id, String atributo, String novoValor) {
		itens.get(id).atualizaItem(atributo, novoValor);
	}

	/**
	 * Adiciona preco de item a lista de produtos de um supermercado, verificando a
	 * excecao para saber se aquisicao é valida
	 * 
	 * @param id,
	 *            identificador unico do item
	 * @param localDeCompra,
	 *            supermercado que o item esta presente
	 * @param preco,
	 *            preco do item em determinado supermercado
	 */
	public void adicionaPrecoItem(int id, String localDeCompra, double preco) {
		itens.get(id).adicionaPrecoItem(localDeCompra, preco);
	}

	/**
	 * Deleta um item presente no mapa de itens
	 * 
	 * @param id,
	 *            identificador unico
	 */
	public void deletaItem(int id) {

		itens.remove(id);
	}

	/**
	 * Recupera um item presente no mapa de itens
	 * 
	 * @param posicao,
	 *            posicao do identificador
	 * @return representaçao em string do item
	 */
	public String getItem(int posicao) {

		if (posicao >= itens.size() || posicao < 0) {
			return "";
		}
		List<ItemCompravel> novaLista = new ArrayList<>(this.itens.values());
		Collections.sort(novaLista);
		return novaLista.get(posicao).toString();
	}

	/**
	 * Recupera uma lista de todos itens cadastrados de um determinada categoria
	 * 
	 * @param categoria,
	 *            categoria do produto
	 * @param posicao,
	 *            posicao do identificador
	 * @return a representacao dessa lista em string
	 */
	public String getItemPorCategoria(String categoria, int posicao) {

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

	/**
	 * Recupera uma lista com os itens de menor precos disponiveis no mapa de itens
	 * 
	 * @param posicao,
	 *            posicao do identificador
	 * @return a representacao dessa lista em string
	 */
	public String getItemPorMenorPreco(int posicao) {

		if (posicao >= itens.size() || posicao < 0) {
			return "";
		}
		List<ItemCompravel> novaLista = new ArrayList<>(this.itens.values());
		Collections.sort(novaLista, new OrdenaItemMenorPreco());
		return novaLista.get(posicao).toString();
	}

	/**
	 * Permite recuperar um produto disponivel no mapa de itens a partir de seu nome
	 * 
	 * @param strPesquisada,
	 *            nome do produto que vai ser pesquisado
	 * @param posicao,
	 *            posicao do identificador unico
	 * @return a representacao desse item em string
	 */
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

	public boolean verificaPresencaItem(Item item) {

		if (this.itens.containsValue(item)) {
			return true;
		}

		return false;
	}

	public boolean verificaPresencaItem(int id) {

		if (this.itens.containsKey(id)) {
			return true;
		}

		return false;
	}

	public Item retornaItem(int id) {

		return this.itens.get(id);
	}

	public ArrayList<Integer> getIds() {

		ArrayList<Integer> ids = new ArrayList<Integer>(this.itens.keySet());

		return ids;

	}

	public HashMap<Integer, Item> getItensParaListaAutomatica(Set<Integer> ids) {
		HashMap<Integer, Item> itensListaAutomatica = new HashMap<Integer, Item>();

		for (int id : ids) {
			if (!this.verificaPresencaItem(id)) {
				throw new IllegalArgumentException("Erro: Esse Item nao Existe.");
			}

			itensListaAutomatica.put(id, this.itens.get(id));
		}

		return itensListaAutomatica;

	}

}
