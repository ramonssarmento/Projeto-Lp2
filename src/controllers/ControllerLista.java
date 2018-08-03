package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import classes.Item;
import classes.ListaDeCompras;
import interfaces.OrdenaListaDescritorEData;
import interfaces.OrdenaStrings;

public class ControllerLista implements Serializable {

	private LinkedHashMap<String, ListaDeCompras> listasDeCompras;

	public ControllerLista() {
		this.listasDeCompras = new LinkedHashMap<>();
	}

	/**
	 * Cria uma lista de compras
	 * 
	 * @param descritorLista
	 *            da lista
	 * @param data
	 *            atual
	 * @param hora
	 *            atual
	 * @return descritor da lista
	 */
	public String adicionaListaDeCompras(String descritorLista, String data, String hora) {

		ListaDeCompras lista = new ListaDeCompras(descritorLista, data, hora);

		this.listasDeCompras.put(descritorLista, lista);

		return descritorLista;
	}

	/**
	 * Adiciona uma lista de compras a partir de outra ja existente (lista gerada
	 * automaticamente)
	 * 
	 * @param lista
	 *            a ser adicionada
	 * @return descritor da lista
	 */
	public String adicionaListaDeCompras(ListaDeCompras lista) {

		this.listasDeCompras.put(lista.getDescritor(), lista);

		return lista.getDescritor();
	}

	/**
	 * Pesquisa uma lista de compras a partir do seu descritor
	 * 
	 * @param descritorLista
	 *            da lista
	 * @return descritor da lista
	 */
	public String pesquisaListaDeCompras(String descritorLista) {

		return descritorLista;
	}

	/**
	 * Adiciona um item na lista
	 * 
	 * @param descritorLista
	 *            descritor da lista
	 * @param quantidade
	 *            do item
	 * @param item
	 *            id do item
	 */
	public void adicionaCompraALista(String descritorLista, int quantidade, Item item) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		lista.adicionaProdutoNaLista(item, quantidade);
	}

	/**
	 * Fecha uma lista
	 * 
	 * @param descritorLista
	 *            descritor da lista
	 * @param localDaCompra
	 *            onde foi comprado os produtos da lista
	 * @param valorFinalDaCompra
	 *            quanto foi pago
	 */
	public void finalizarListaDeCompras(String descritorLista, String localDaCompra, int valorFinalDaCompra) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		lista.finalizarLista(localDaCompra, valorFinalDaCompra);
	}

	/**
	 * Busca um item na lista pelo seu id
	 * 
	 * @param descritorLista
	 *            descritor da lista
	 * @param itemId
	 *            do item
	 * @return representacao textual de um produto da lista
	 */
	public String pesquisaCompraEmLista(String descritorLista, int itemId) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		return lista.pesquisaCompraEmLista(itemId);

	}

	/**
	 * Altera a quantidade do produto na lista
	 * 
	 * @param descritorLista
	 *            descritor da lista
	 * @param itemId
	 *            do item
	 * @param operacao
	 *            a ser realizada(aumenta e diminui)
	 * @param quantidade
	 *            valor da mudanca
	 */
	public void atualizaCompraDeLista(String descritorLista, int itemId, String operacao, int quantidade) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		lista.atualizaProduto(itemId, operacao, quantidade);
	}

	/**
	 * Representacao de um item da lista
	 * 
	 * @param descritorLista
	 *            descritor da lista
	 * @param itemPosicao
	 *            do item
	 * @return representacao textual do produto
	 */
	public String getItemLista(String descritorLista, int itemPosicao) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		return lista.retornaItemPosicao(itemPosicao);

	}

	/**
	 * Remove um item da lista
	 * 
	 * @param descritorLista
	 *            descritor da lista
	 * @param itemId
	 *            id do item
	 */
	public void deletaCompraDeLista(String descritorLista, int itemId) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		lista.deletaProdutoLista(itemId);
	}

	/**
	 * Representacao textual da lista
	 * 
	 * @param descritorLista
	 *            descritor da lista
	 * @return representacao da lista
	 */
	public String imprimirListaDeCompras(String descritorLista) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		return lista.toString();
	}

	/**
	 * Filtra a lista por data e seleciona a que esta na posicao indicada
	 * 
	 * @param data
	 *            de criacao da lista
	 * @param posicaoLista
	 *            posicao da lista (em relacao as listas que foram criadas naquela
	 *            data em ordem alfabetica)
	 * @return descritor da lista
	 */
	public String getListaPorData(String data, int posicaoLista) {

		String saida = "";
		List<String> saidaOrdenada = new ArrayList<>();

		for (ListaDeCompras lista : this.listasDeCompras.values()) {
			if (data.equals(lista.getData())) {
				saidaOrdenada.add(lista.getDescritor());
			}
		}

		Collections.sort(saidaOrdenada, new OrdenaStrings());

		if (posicaoLista < 0 || posicaoLista > saidaOrdenada.size()) {
			throw new IllegalArgumentException("Erro na busca: nao existem listas criadas na posicao informada!");
		}

		saida = saidaOrdenada.get(posicaoLista);
		return saida;
	}

	/**
	 * Esse metodo serve para pesquisar listas que possuem um determinado item.
	 * Essas listas sao ordenadas primeiramente pelas datas, com as mais antigas
	 * vindo a  frente Caso hajam datas iguais, as listas sao ordenadas
	 * alfabeticamente.
	 * 
	 * @param itemId
	 *            - Id do item a  ser pesquisado.
	 * 
	 * @return - Representacao textual das datas e dos descritores das listas.
	 */

	public String buscaListasPorItem(int itemId) {
		List<ListaDeCompras> listaSaidaOrdenada = new LinkedList<ListaDeCompras>();

		for (ListaDeCompras lista : this.listasDeCompras.values()) {
			if (lista.getExistenciaDeItem(itemId)) {
				listaSaidaOrdenada.add(lista);
			}
		}

		if (listaSaidaOrdenada.size() == 0) {
			throw new IllegalArgumentException("Erro na pesquisa de compra: compra nao encontrada na lista.");
		}

		Collections.sort(listaSaidaOrdenada, new OrdenaListaDescritorEData());

		String saida = "";
		for (ListaDeCompras lista : listaSaidaOrdenada) {
			saida += lista.getDescritorComData() + System.lineSeparator();
		}

		return saida.trim();
	}

	/**
	 * Esse metodo serve para pesquisar uma lista que contem um determinado item,
	 * pesquisando pela posicao da lista. Essas listas sao ordenadas primeiramente
	 * pelas datas, com as mais antigas vindo a  frente Caso hajam datas iguais, as
	 * listas sao ordenadas alfabeticamente.
	 * 
	 * @param itemId
	 *            - Id do item a  ser pesquisado.
	 * 
	 * @param posicaoLista
	 *            - Posicao da lista desejada.
	 * 
	 * @return - Representacao textual da data e do descritor da lista.
	 */

	public String buscaListaPorItem(int itemId, int posicaoLista) {
		List<ListaDeCompras> listaSaidaOrdenada = new LinkedList<ListaDeCompras>();

		for (ListaDeCompras lista : this.listasDeCompras.values()) {
			if (lista.getExistenciaDeItem(itemId)) {
				listaSaidaOrdenada.add(lista);
			}
		}

		if (listaSaidaOrdenada.size() == 0) {
			throw new IllegalArgumentException("Erro de busca: nao existem listas com esse item!");
		}

		Collections.sort(listaSaidaOrdenada, new OrdenaListaDescritorEData());

		if (posicaoLista < 0 || posicaoLista > listaSaidaOrdenada.size()) {
			throw new IllegalArgumentException("Erro na pesquisa de compra: compra nao encontrada na lista.");
		}

		return listaSaidaOrdenada.get(posicaoLista).getDescritorComData();
	}

	/**
	 * Esse metodo serve para pesquisar uma lista que foram cridas em determinada
	 * data
	 * 
	 * @param data
	 *            de criacao das lista
	 * 
	 * @return descritores das listas cridas nesta data
	 */
	public String pesquisaListasDeComprasPorData(String data) {

		String saida = "";
		SortedSet<String> saidaOrdenada = new TreeSet<String>();

		for (ListaDeCompras lista : this.listasDeCompras.values()) {
			if (data.equals(lista.getData())) {
				saidaOrdenada.add(lista.getDescritor());
			}
		}

		for (String descritor : saidaOrdenada) {
			saida += descritor + System.lineSeparator();
		}

		if (saida.equals("")) {
			throw new IllegalArgumentException("Erro na busca: nao existem listas criadas na data informada!");
		}

		return saida.trim();
	}

	public String geraAutomaticaUltimaLista(String data, String hora) {

		Set<String> chaves = this.listasDeCompras.keySet();
		Iterator<String> it = chaves.iterator();

		String key = null;
		while (it.hasNext()) {
			key = it.next();
		}

		String descritor = "Lista automatica 1 " + data;
		ListaDeCompras lista = this.listasDeCompras.get(key).getClone(descritor, data, hora);

		this.adicionaListaDeCompras(lista);

		return lista.getDescritor();

	}

	public String geraAutomaticaItem(String descritorItem, String data, String hora) {
		Set<String> chaves = this.listasDeCompras.keySet();
		Iterator<String> it = chaves.iterator();
		String key = null;
		ListaDeCompras ultimaListaComItem = null;

		while (it.hasNext()) {
			key = it.next();
			ListaDeCompras lista = this.listasDeCompras.get(key);

			if (lista.getExistenciaDeItem(descritorItem)) {
				ultimaListaComItem = lista;
			}
		}

		if (ultimaListaComItem == null) {
			throw new IllegalArgumentException(
					"Erro na geracao de lista automatica por item: nao ha compras cadastradas com o item desejado.");
		}

		String descritor = "Lista automatica 2 " + data;
		ListaDeCompras listaSaida = ultimaListaComItem.getClone(descritor, data, hora);

		this.adicionaListaDeCompras(listaSaida);

		return listaSaida.getDescritor();
	}

	public String geraAutomaticaItensMaisPresentes(String data, String hora, HashMap<Integer, Item> IdsEItens,
			HashMap<Integer, Integer> idsEQuantidades) {
		ListaDeCompras lista = new ListaDeCompras("Lista automatica 3 " + data, data, hora);

		for (int id : idsEQuantidades.keySet()) {
			lista.adicionaProdutoNaLista(IdsEItens.get(id), idsEQuantidades.get(id));
		}

		this.adicionaListaDeCompras(lista);

		return lista.getDescritor();

	}

	public HashMap<Integer, Integer> geraListaDeIdsEQuantidadesItensMaisRecorrentes(ArrayList<Integer> idsItens) {

		HashMap<Integer, Integer> idsParaAdicionar = new HashMap<Integer, Integer>();
		for (int id : idsItens) {
			int cont = 0;
			int meta = (this.listasDeCompras.size() / 2);
			int quantidade = 0;

			for (ListaDeCompras listaCompras : this.listasDeCompras.values()) {
				if (listaCompras.getExistenciaDeItem(id)) {
					cont += 1;
					quantidade += listaCompras.getQuantidadeItem(id);
				}
			}

			quantidade = quantidade > 0 ? Math.floorDiv(quantidade, cont) : 0;
			if (cont >= meta) {
				idsParaAdicionar.put(id, quantidade);
			}
		}

		return idsParaAdicionar;

	}

	public boolean verificaPresencaDeLista(String descritorLista) {

		if (this.listasDeCompras.containsKey(descritorLista)) {
			return true;
		}

		return false;
	}

	public HashMap<Integer, Integer> retornaItensEQuantidadesDeUmaLista(String descritorLista) {
		verificaRetornaQuantidadesEitens(descritorLista);
		return this.listasDeCompras.get(descritorLista).retornaItensEQuantidades();
	}

	public ArrayList<String> ordenaProdutosParaSuperMercado(ArrayList<Integer> ids, String descritorLista) {
		return this.listasDeCompras.get(descritorLista).ordenaProdutosParaSupermercado(ids);

	}

	private void verificaRetornaQuantidadesEitens(String descritorLista) {
		if (!this.listasDeCompras.containsKey(descritorLista)) {
			throw new IllegalArgumentException("Erro: essa lista de compras nao existe no sistema.");
		}
	}

}
