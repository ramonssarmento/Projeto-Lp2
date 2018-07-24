package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import classes.Item;
import classes.ListaDeCompras;
import interfaces.OrdenaListaDescritorEData;
import interfaces.OrdenaStrings;

public class ControllerLista {
	
	private HashMap<String, ListaDeCompras> listasDeCompras;
	
	public ControllerLista() {
		this.listasDeCompras = listasDeCompras;
	}
	
	public String adicionaListaDeCompras(String descritorLista, String data) {
	
		ListaDeCompras lista = new ListaDeCompras(descritorLista, data);

		this.listasDeCompras.put(descritorLista, lista);

		return descritorLista;
	}

	public String pesquisaListaDeCompras(String descritorLista) {

		return descritorLista;
	}

	public void adicionaCompraALista(String descritorLista, int quantidade, Item item) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		lista.adicionaProdutoNaLista(item, quantidade);
	}

	public void finalizarListaDeCompras(String descritorLista, String localDaCompra, int valorFinalDaCompra) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		lista.finalizarLista(localDaCompra, valorFinalDaCompra);
	}

	public String pesquisaCompraEmLista(String descritorLista, int itemId) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		return lista.pesquisaCompraEmLista(itemId);

	}

	public void atualizaCompraDeLista(String descritorLista, int itemId, String operacao, int quantidade) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		lista.atualizaProduto(itemId, operacao, quantidade);
	}

	public String getItemLista(String descritorLista, int itemPosicao) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		return lista.retornaItemPosicao(itemPosicao);

	}

	public void deletaCompraDeLista(String descritorLista, int itemId) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		lista.deletaProdutoLista(itemId);
	}

	public String imprimirListaDeCompras(String descritorLista) {

		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);

		return lista.toString();
	}

	public String getItemListaPorData(String data, int posicaoLista) {

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
	 * Esse método serve para pesquisar listas que possuem um determinado item.
	 * Essas listas são ordenadas primeiramente pelas datas, com as mais antigas
	 * vindo à frente Caso hajam datas iguais, as listas são ordenadas
	 * alfabéticamente.
	 * 
	 * @param itemId
	 *            - Id do itém à ser pesquisado.
	 * 
	 * @return - Representação textual das datas e dos descritores das listas.
	 */

	public String buscaListaPorItem(int itemId) {
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
	 * Esse método serve para pesquisar uma lista que contém um determinado item,
	 * pesquisando pela posição da lista. Essas listas são ordenadas primeiramente
	 * pelas datas, com as mais antigas vindo à frente Caso hajam datas iguais, as
	 * listas são ordenadas alfabéticamente.
	 * 
	 * @param itemId
	 *            - Id do itém à ser pesquisado.
	 * 
	 * @param posicaoLista
	 *            - Posição da lista desejada.
	 * 
	 * @return - Representação textual da data e do descritor da lista.
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

	public String geraAutomaticaUltimaLista() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean verificaPresencaDeLista(String descritorLista) {
		
		if (this.listasDeCompras.containsKey(descritorLista)) {
			return true;
		}
		
		return false;
	}


}
