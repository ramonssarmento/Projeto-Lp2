package fachada;

import Controllers.Controller;
import easyaccept.EasyAccept;

public class Facade {
	private Controller controller;

	public Facade() {
		this.controller = new Controller();
	}

	public int adicionaItemPorQtd(String nome, String categoria, int qtd, String unidadeDeMedida, String localDeCompra,
			double preco) {
		return controller.adicionaItemPorQtd(nome, categoria, qtd, unidadeDeMedida, localDeCompra, preco);

	}

	public int adicionaItemPorQuilo(String nome, String categoria, double kg, String localDeCompra, double preco) {
		return controller.adicionaItemPorQuilo(nome, categoria, kg, localDeCompra, preco);

	}

	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String localDeCompra, double preco) {
		return controller.adicionaItemPorUnidade(nome, categoria, unidade, localDeCompra, preco);

	}

	public String exibeItem(int id) {
		return controller.exibeItem(id);
	}

	public void atualizaItem(int id, String atributo, String novoValor) {
		controller.atualizaItem(id, atributo, novoValor);
	}

	public void adicionaPrecoItem(int id, String localDeCompra, double preco) {
		controller.adicionaPrecoItem(id, localDeCompra, preco);
	}

	public void deletaItem(int id) {
		controller.deletaItem(id);
	}

	public String getItem(int posicao) {
		return controller.getItem(posicao);
	}

	public String getItemPorCategoria(String categoria, int posicao) {
		return controller.getItemPorCategoria(categoria, posicao);
	}

	public String getItemPorMenorPreco(int posicao) {
		return controller.getItemPorMenorPreco(posicao);
	}

	public String getItemPorPesquisa(String strPesquisada, int posicao) {
		return controller.getItemPorPesquisa(strPesquisada, posicao);
	}

	public String adicionaListaDeCompras(String descritorLista) {
		return controller.adicionaListaDeCompras(descritorLista);
	}

	public void adicionaCompraALista(String descritorLista, int quantidade, int itemId) {
		controller.adicionaCompraALista(descritorLista, quantidade, itemId);
	}

	public String pesquisaListaDeCompras(String descritorLista) {
		return controller.pesquisaListaDeCompras(descritorLista);
	}

	public void finalizarListaDeCompras(String descritorLista, String localDaCompra, int valorFinalDaCompra) {
		controller.finalizarListaDeCompras(descritorLista, localDaCompra, valorFinalDaCompra);
	}

	public String pesquisaCompraEmLista(String descritorLista, int itemId) {
		return controller.pesquisaCompraEmLista(descritorLista, itemId);
	}

	public void atualizaCompraDeLista(String descritorLista, int itemId, String operacao, int quantidade) {
		controller.atualizaCompraDeLista(descritorLista, itemId, operacao, quantidade);
	}

	public String getItemLista(String descritorLista, int posicaoItem) {
		return controller.getItemLista(descritorLista, posicaoItem);
	}

	public void deletaCompraDeLista(String descritorLista, int itemId) {
		controller.deletaCompraDeLista(descritorLista, itemId);
	}

	public String imprimirListaDeCompras(String descritorLista) {
		return controller.imprimirListaDeCompras(descritorLista);
	}

	public String getItemListaPorData(String data, int posicaoLista) {
		return controller.getItemListaPorData(data, posicaoLista);
	}

	public String getItemListaPorItem(int id, int posicaoLista) {
		return controller.buscaListaPorItem(id, posicaoLista);
	}

	public String pesquisaListasDeComprasPorItem(int id) {
		return controller.buscaListaPorItem(id);
	}

	public String pesquisaListasDeComprasPorData(String data) {
		return controller.pesquisaListasDeComprasPorData(data);
	}

	public String dataAtual() {
		return controller.dataAtual();
	}

	public String geraAutomaticaUltimaLista() {
		return controller.geraAutomaticaUltimaLista();
	}

	public String geraAutomaticaItem(String descritorItem) {
		return controller.geraAutomaticaItem(descritorItem);
	}

	public String geraAutomaticaItensMaisPresentes() {
		return controller.geraAutomaticaItensMaisPresentes();
	}

	public static void main(String[] args) {
		args = new String[] { "fachada.Facade", "EasyAccept/use_case1.txt", "EasyAccept/use_case1_exception.txt",
				"EasyAccept/use_case2.txt", "EasyAccept/use_case2_exception.txt", "EasyAccept/use_case3.txt",
				"EasyAccept/use_case3_exception.txt", "EasyAccept/use_case4.txt", "EasyAccept/use_case4_exception.txt",
				"EasyAccept/use_case5.txt" };
		EasyAccept.main(args);
	}
}
