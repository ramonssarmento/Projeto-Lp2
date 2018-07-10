package fachada;

import com.sun.org.glassfish.external.probe.provider.annotations.Probe;

import classes.Controller;

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

	@Probe // pode ser adicionado quantidade double,devo sobrecarregar o metodo?
	public void adicionaCompraALista(String descritorLista, int quantidade, int itemId) {
		controller.adicionaCompraALista(descritorLista, quantidade, itemId);
	}

	public void finalizarListaDeCompras(String descritorLista, String localDaCompra, int valorFinalDaCompra) {
		controller.finalizarListaDeCompras(descritorLista, localDaCompra, valorFinalDaCompra);
	}

	public String pesquisaCompraEmLista(String descritorLista, int itemId) {
		return controller.pesquisaCompraEmLista(descritorLista, itemId);
	}

	public void atualizaCompraDeLista(String descritorLista, int itemId, int quantidade) {
		controller.atualizaCompraDeLista(descritorLista, itemId, quantidade);
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

	public String getItemListaPorItem(int id, String posicaoLista) {
		return controller.getItemListaPorItem(id, posicaoLista);
	}
}
