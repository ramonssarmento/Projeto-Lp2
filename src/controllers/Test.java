package controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;

import classes.SuperMercado;

public class Test {
	
	private ControllerLista controleListas;
	private ControllerItem controleItem;
	private ControllerSupermercados controleSuperMercados;
	
	public Test() {
		this.controleListas = new ControllerLista();
		this.controleItem = new ControllerItem();
		this.controleSuperMercados = new ControllerSupermercados();
	}
	
	public int adicionaItemPorQtd(String nome, String categoria, int qtd, String unidadeDeMedida, String localDeCompra,
			double preco) {
		
		int id = controleItem.adicionaItemPorQtd(nome, categoria, qtd, unidadeDeMedida, localDeCompra, preco);
		
		controleSuperMercados.adicionaItemNoSupermercado(localDeCompra, preco, id);
		
		return id;
	}
	
	/**
	 * Lanca exececoes para verificar identificador unico do item
	 * 
	 * @param id,
	 *            identificador unico do produto
	 */
	private void validaListagem(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Erro na listagem de item: id invalido.");
		} else if (!controleItem.verificaPresencaItem(id)) {
			throw new IllegalArgumentException("Erro na listagem de item: item nao existe.");
		}
	}

	/**
	 * Lanca excecoes para verificar se o identificador unico ja foi gerado e esta
	 * na no mapa
	 * 
	 * @param id,
	 *            identificador unico do produto
	 */
	private void validaAtualizacao(int id) {
		if (!controleItem.verificaPresencaItem(id)) {
			throw new IllegalArgumentException("Erro na atualizacao de item: item nao existe.");
		}
	}

	/**
	 * Lanca excecoes para verificar se o item com determinado id existe no mapa
	 * 
	 * @param id,
	 *            identificador unico do produto
	 */
	private void validaCadastroPreco(int id) {
		if (id <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de preco: id de item invalido.");
		} else if (!controleItem.verificaPresencaItem(id)) {
			throw new IllegalArgumentException("Erro no cadastro de preco: item nao existe.");
		}
	}

	/**
	 * Lanca excecao para verificar a categoria que o item pertence, e verificar se
	 * a mesma e valida
	 * 
	 * @param categoria,
	 *            categoria que o produto pertence
	 * @return um booleando caso a categoria seja valida
	 */
	private boolean verificaCategoria(String categoria) {
		if (categoria.equals("limpeza") || categoria.equals("alimento industrializado")
				|| categoria.equals("higiene pessoal") || categoria.equals("alimento nao industrializado")) {
			return true;
		}
		throw new IllegalArgumentException("Erro na listagem de item: categoria nao existe.");
	}

	private void validaAdicionaLista(String descritorLista) {
		if (descritorLista == null || descritorLista.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro na criacao de lista de compras: descritor nao pode ser vazio ou nulo.");
		}
	}

	private void validaCompraDeItem(String descritorLista, int itemId) {
		if (descritorLista == null || descritorLista.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na compra de item: descritor nao pode ser vazio ou nulo.");
		}

		if (!controleItem.verificaPresencaItem(itemId)) {
			throw new IllegalArgumentException("Erro na compra de item: item nao existe no sistema.");
		}

		if (!controleListas.verificaPresencaDeLista(descritorLista)) {
			throw new IllegalArgumentException("Erro na compra de item: Lista nao cadastrada");
		}

	}

	private void validaItemPesquisaCompraLista(int itemId, String descritorLista) {

		if (!controleItem.verificaPresencaItem(itemId)) {
			throw new IllegalArgumentException("Erro na pesquisa de compra: item id invalido.");
		}

		if (descritorLista == null || descritorLista.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na pesquisa de compra: descritor nao pode ser vazio ou nulo.");
		}

		if (!controleListas.verificaPresencaDeLista(descritorLista)) {
			throw new IllegalArgumentException("Erro na compra de item: Lista nao cadastrada");
		}

	}

	private void validaExcluirItemDaLista(String descritorLista, int itemId) {
		if (descritorLista == null || descritorLista.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na exclusao de compra: descritor nao pode ser vazio ou nulo.");
		}

		if (!controleListas.verificaPresencaDeLista(descritorLista)) {
			throw new IllegalArgumentException("Erro na exclusao de compra: item nao existe no sistema.");
		}

		if (!controleItem.verificaPresencaItem(itemId)) {
			throw new IllegalArgumentException("Erro na exclusao de compra: item nao existe no sistema.");
		}
	}

	private void validaAtualizaCompraDeLista(String descritorLista, int itemId) {
		if (descritorLista == null || descritorLista.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na exclusao de compra: descritor nao pode ser vazio ou nulo.");
		}

		if (!controleListas.verificaPresencaDeLista(descritorLista)) {
			throw new IllegalArgumentException("Erro na exclusao de compra: item nao existe no sistema.");
		}
	}

	private void validaFinalizaLista(String descritorLista) {
		if (descritorLista == null || descritorLista.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro na finalizacao de lista de compras: descritor nao pode ser vazio ou nulo.");
		}
	}

	private void validaPesquisaListaDeCompras(String descritorLista) {
		if (descritorLista.equals("") || descritorLista == null) {
			throw new IllegalArgumentException("Erro na pesquisa de compra: descritor nao pode ser vazio ou nulo.");
		}

		if (!controleListas.verificaPresencaDeLista(descritorLista)) {
			throw new IllegalArgumentException("Erro na pesquisa de compra: lista de compras nao existe.");
		}
	}

	public String dataAtual() {

		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		return formatador.format(data);
	}

	private void verificaData(String data) {

		if (data == null || data.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na pesquisa de compra: data nao pode ser vazia ou nula.");
		}

		String[] dataTest = data.split("/");

		if (dataTest.length != 3 || data.length() != 10) {

			throw new IllegalArgumentException(
					"Erro na pesquisa de compra: data em formato invalido, tente dd/MM/yyyy");
		}

		try {
			int dia = Integer.parseInt(dataTest[0]);
			int mes = Integer.parseInt(dataTest[1]);
			int ano = Integer.parseInt(dataTest[2]);

			if (dataTest[2].length() != 4) {
				throw new IllegalArgumentException();
			}

			if (dia > 31 || dia < 1 || mes < 1 || mes > 12) {
				throw new IllegalArgumentException();
			}
		}

		catch (Exception e) {
			throw new IllegalArgumentException(
					"Erro na pesquisa de compra: data em formato invalido, tente dd/MM/yyyy");
		}
	}

}
