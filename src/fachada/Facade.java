package fachada;

import com.sun.org.glassfish.external.probe.provider.annotations.Probe;

public class Facade {

	public Facade() {
		// TODO Auto-generated method stub
	}

	public int adicionaItemPorQtd(String nome, String categoria, String unidadeDeMedida, String localDeCompra,
			double preco) {
		// TODO Auto-generated method stub
		return 0;

	}

	public int adicionaItemPorQuilo(String nome, String categoria, double kg, String localDeCompra, double preco) {
		// TODO Auto-generated method stub
		return 0;

	}

	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String localDeCompra, double preco) {
		// TODO Auto-generated method stub
		return 0;

	}

	public String exibeItem(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void atualizaItem(int id, String atributo, String novoValor) {
		// TODO Auto-generated method stub
	}

	public void adicionaPrecoItem(int id, String localDeCompra, double preco) {
		// TODO Auto-generated method stub
	}

	public void deletaItem(int id) {
		// TODO Auto-generated method stub
	}

	public String getItem(int posicao) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getItemPorCategoria(String categoria, int posicao) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getItemPorMenorPreco(int posicao) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getItemPorPesquisa(String strPesquisada, int posicao) {
		// TODO Auto-generated method stub
		return null;
	}

	public String adicionaListaDeCompras(String descritorLista) {
		// TODO Auto-generated method stub
		return null;
	}

	@Probe // pode ser adicionado double,devo sobrecarregar o metodo?
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
}
