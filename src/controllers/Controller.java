package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import classes.Item;
import empacotamento.Empacotamento;
import interfaces.SupermercadoComItensOrdenavel;

/**
 * Classe que controla e delega todo o sistema.
 * 
 * @author Abel Antunes, Hercules Anselmo, Jose Adriao e Ramon Sarmento.
 *
 */
public class Controller {

	private ControllerLista controleListas;
	private ControllerItem controleItem;
	private ControllerSupermercados controleSuperMercados;

	/**
	 * Construtor da classe
	 */
	public Controller() {
		this.controleListas = new ControllerLista();
		this.controleItem = new ControllerItem();
		this.controleSuperMercados = new ControllerSupermercados();
	}

	/**
	 * Carrega os dados de arquivos criados anteriormente (persistencia)
	 */
	public void iniciarSistema() {
		try {
			this.controleItem = (ControllerItem) Empacotamento.lerObjetos("DadosDoSistema/DadosControllerItens.bin");
			this.controleListas = (ControllerLista) Empacotamento
					.lerObjetos("DadosDoSistema/DadosControllerListas.bin");
			this.controleSuperMercados = (ControllerSupermercados) Empacotamento
					.lerObjetos("DadosDoSistema/DadosControllerSupermercados.bin");
		} catch (FileNotFoundException e) {
			this.controleItem = new ControllerItem();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Salva os dados em arquivos (persistencia)
	 */
	public void fecharSistema() {
		try {
			Empacotamento.salvarObjeto(this.controleItem, "DadosDoSistema/DadosControllerItens.bin");
			Empacotamento.salvarObjeto(this.controleListas, "DadosDoSistema/DadosControllerListas.bin");
			Empacotamento.salvarObjeto(this.controleSuperMercados, "DadosDoSistema/DadosControllerSupermercados.bin");
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo.");
			e.printStackTrace();
		}

		this.controleListas = null;
		this.controleItem = null;
		this.controleSuperMercados = null;
	}

	/**
	 * Encerra o programa
	 */
	public void quit() {
		System.exit(0);
	}

	/**
	 * Adiciona um item que tem seu preco calculado por quantidade ao mapa de itens,
	 * depois de ter verifacado as excecoes, para saber se a aquisicao do novo item
	 * era valida, e em seguida lhe e dado um identificador unico
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

		int id = controleItem.adicionaItemPorQtd(nome, categoria, qtd, unidadeDeMedida, localDeCompra, preco);

		controleSuperMercados.adicionaItemNoSupermercado(localDeCompra, preco, id);

		return id;
	}

	/**
	 * Adiciona um item que tem seu preco calculado por quilo ao mapa de itens,
	 * depois de ter verifacado as excecoes, para saber se a aquisicao do novo item
	 * era valida, e em seguida lhe e dado um identificador unico
	 * 
	 * @param nome,
	 *            nome do produto a ser adicionado
	 * @param categoria,
	 *            categoria do produto que vai ser adicionado
	 * @param kg,
	 *            unidade de medida do produto, que sera usada no calculo do valor
	 * @param localDeCompra,
	 *            supermercado o qual o produto sera cadastrado
	 * @param preco,
	 *            preco do produto no determinado supermercado
	 * @return identificador unico gerado para o item
	 */
	public int adicionaItemPorQuilo(String nome, String categoria, double kg, String localDeCompra, double preco) {

		int id = controleItem.adicionaItemPorQuilo(nome, categoria, kg, localDeCompra, preco);

		controleSuperMercados.adicionaItemNoSupermercado(localDeCompra, preco, id);

		return id;
	}

	/**
	 * Adiciona um item que tem seu preco calculado por unidade ao mapa, depois de
	 * ter verifacado as excecoes, para saber se a aquisicao do novo item era
	 * valida, e em seguida lhe e dado um identificador unico
	 * 
	 * @param nome,
	 *            nome do produto a ser adicionado
	 * @param categoria,
	 *            categoria do produto que vai ser adicionado
	 * @param unidade,
	 *            produto e comprado por unidade
	 * @param localDeCompra,
	 *            supermercado onde o produto e comprado
	 * @param preco,
	 *            preco do produto em determinado supermercado
	 * @return identificador unico gerado para o item
	 */
	public int adicionaItemPorUnidade(String nome, String categoria, int unidade, String localDeCompra, double preco) {
		int id = controleItem.adicionaItemPorUnidade(nome, categoria, unidade, localDeCompra, preco);

		controleSuperMercados.adicionaItemNoSupermercado(localDeCompra, preco, id);

		return id;
	}

	/**
	 * Exibe um item a partir de seu identificador
	 * 
	 * @param id
	 *            identificador unico do produto
	 * @return representacao do item em string
	 */
	public String exibeItem(int id) {
		validaListagem(id);

		return controleItem.exibeItem(id);
	}

	/**
	 * Altera os atributos de um item, e verifica a excecao para saber se a
	 * atualizacao e valida
	 * 
	 * @param id,
	 *            identificador unico do item
	 * @param atributo,
	 *            atributo do item que sera mudado
	 * @param novoValor,
	 *            novo valor do atributo que sera passado
	 */
	public void atualizaItem(int id, String atributo, String novoValor) {
		validaAtualizacao(id);

		controleItem.atualizaItem(id, atributo, novoValor);
	}

	/**
	 * Adiciona preco de item a lista de produtos de um supermercado, verificando a
	 * excecao para saber se aquisicao e valida
	 * 
	 * @param id,
	 *            identificador unico do item
	 * @param localDeCompra,
	 *            supermercado que o item esta presente
	 * @param preco,
	 *            preco do item em determinado supermercado
	 */
	public void adicionaPrecoItem(int id, String localDeCompra, double preco) {
		validaCadastroPreco(id, localDeCompra, preco);

		controleSuperMercados.adicionaItemNoSupermercado(localDeCompra, preco, id);
		controleItem.adicionaPrecoItem(id, localDeCompra, preco);
	}

	/**
	 * Deleta um item presente no mapa de itens
	 * 
	 * @param id
	 *            identificador unico
	 */
	public void deletaItem(int id) {
		validaDeletaItem(id);

		controleItem.deletaItem(id);
		controleSuperMercados.deletaItem(id);
	}

	/**
	 * Recupera um item presente no mapa de itens
	 * 
	 * @param posicao
	 *            posicao do identificador
	 * @return representacao em string do item
	 */
	public String getItem(int posicao) {
		return controleItem.getItem(posicao);
	}

	/**
	 * Recupera uma lista de todos itens cadastrados de um determinada categoria
	 * 
	 * @param categoria
	 *            categoria do produto
	 * @param posicao
	 *            posicao do identificador
	 * @return a representacao dessa lista em string
	 */
	public String getItemPorCategoria(String categoria, int posicao) {
		verificaCategoria(categoria);

		return controleItem.getItemPorCategoria(categoria, posicao);
	}

	/**
	 * Recupera uma lista com os itens de menor precos disponiveis no mapa de itens
	 * 
	 * @param posicao
	 *            posicao do identificador
	 * @return a representacao dessa lista em string
	 */
	public String getItemPorMenorPreco(int posicao) {
		return controleItem.getItemPorMenorPreco(posicao);
	}

	/**
	 * Permite recuperar um produto disponivel no mapa de itens a partir de seu nome
	 * 
	 * @param strPesquisada
	 *            nome do produto que vai ser pesquisado
	 * @param posicao
	 *            posicao do identificador unico
	 * @return a representacao desse item em string
	 */
	public String getItemPorPesquisa(String strPesquisada, int posicao) {
		return controleItem.getItemPorPesquisa(strPesquisada, posicao);
	}

	/**
	 * Cria uma lista de compras
	 * 
	 * @param descritorLista
	 *            da lista
	 * @return descritor da lista
	 */
	public String adicionaListaDeCompras(String descritorLista) {
		validaAdicionaLista(descritorLista);
		String data = dataAtual();
		String hora = horaAtual();

		return controleListas.adicionaListaDeCompras(descritorLista, data, hora);
	}

	/**
	 * Pesquisa uma lista de compras a partir do seu descritor
	 * 
	 * @param descritorLista
	 *            da lista
	 * @return descritor da lista
	 */
	public String pesquisaListaDeCompras(String descritorLista) {
		validaPesquisaListaDeCompras(descritorLista);

		return controleListas.pesquisaListaDeCompras(descritorLista);
	}

	/**
	 * Adiciona um item na lista
	 * 
	 * @param descritorLista
	 *            descritor da lista
	 * @param quantidade
	 *            do item
	 * @param itemId
	 *            id do item
	 */
	public void adicionaCompraALista(String descritorLista, int quantidade, int itemId) {
		validaCompraDeItem(descritorLista, itemId);

		Item item = controleItem.retornaItem(itemId);
		controleListas.adicionaCompraALista(descritorLista, quantidade, item);
	}

	/**
	 * Fecha uma lista.
	 * 
	 * @param descritorLista
	 *            descritor da lista.
	 * @param localDaCompra
	 *            onde foi comprado os produtos da lista.
	 * @param valorFinalDaCompra
	 *            quanto foi pago.
	 */
	public void finalizarListaDeCompras(String descritorLista, String localDaCompra, int valorFinalDaCompra) {
		validaFinalizaLista(descritorLista);

		controleListas.finalizarListaDeCompras(descritorLista, localDaCompra, valorFinalDaCompra);
	}

	/**
	 * Busca um item na lista pelo seu id.
	 * 
	 * @param descritorLista
	 *            descritor da lista.
	 * @param itemId
	 *            do item.
	 * @return representacao textual de um produto da lista.
	 */
	public String pesquisaCompraEmLista(String descritorLista, int itemId) {
		validaItemPesquisaCompraLista(itemId, descritorLista);

		return controleListas.pesquisaCompraEmLista(descritorLista, itemId);
	}

	/**
	 * Altera a quantidade do produto na lista.
	 * 
	 * @param descritorLista
	 *            descritor da lista.
	 * @param itemId
	 *            do item.
	 * @param operacao
	 *            a ser realizada(aumenta e diminui).
	 * @param quantidade
	 *            valor da mudanca.
	 */
	public void atualizaCompraDeLista(String descritorLista, int itemId, String operacao, int quantidade) {
		validaAtualizaCompraDeLista(descritorLista, itemId);

		controleListas.atualizaCompraDeLista(descritorLista, itemId, operacao, quantidade);
	}

	/**
	 * Representacao de um item da lista.
	 * 
	 * @param descritorLista
	 *            descritor da lista.
	 * @param itemPosicao
	 *            do item.
	 * @return representacao textual do produto.
	 */

	public String getItemLista(String descritorLista, int itemPosicao) {
		return controleListas.getItemLista(descritorLista, itemPosicao);
	}

	/**
	 * Remove um item da lista.
	 * 
	 * @param descritorLista
	 *            descritor da lista.
	 * @param itemId
	 *            id do item.
	 */
	public void deletaCompraDeLista(String descritorLista, int itemId) {
		validaExcluirItemDaLista(descritorLista, itemId);

		controleListas.deletaCompraDeLista(descritorLista, itemId);
	}

	/**
	 * Representacao textual da lista.
	 * 
	 * @param descritorLista
	 *            descritor da lista.
	 * @return representacao da lista.
	 */
	public String imprimirListaDeCompras(String descritorLista) {
		return controleListas.imprimirListaDeCompras(descritorLista);
	}

	/**
	 * Filtra a lista por data e seleciona a que esta na posicao indicada.
	 * 
	 * @param data
	 *            de criacao da lista.
	 * @param posicaoLista
	 *            posicao da lista (em relacao as listas que foram criadas naquela
	 *            data em ordem alfabetica).
	 * @return descritor da lista.
	 */
	public String getListaPorData(String data, int posicaoLista) {
		verificaData(data);

		return controleListas.getListaPorData(data, posicaoLista);
	}

	/**
	 * Esse metodo serve para pesquisar listas que possuem um determinado item.
	 * Essas listas sao ordenadas primeiramente pelas datas, com as mais antigas
	 * vindo a  frente Caso hajam datas iguais, as listas sao ordenadas
	 * alfabeticamente.
	 * 
	 * @param itemId
	 *            Id do item a  ser pesquisado.
	 * 
	 * @return Representacao textual das datas e dos descritores das listas.
	 */
	public String buscaListasPorItem(int itemId) {
		return controleListas.buscaListasPorItem(itemId);
	}

	/**
	 * Esse metodo serve para pesquisar uma lista que contem um determinado item,
	 * pesquisando pela posicao da lista. Essas listas sao ordenadas primeiramente
	 * pelas datas, com as mais antigas vindo a  frente Caso hajam datas iguais, as
	 * listas sao ordenadas alfabeticamente.
	 * 
	 * @param itemId
	 *            Id do item a  ser pesquisado.
	 * 
	 * @param posicaoLista
	 *            Posicao da lista desejada.
	 * 
	 * @return Representacao textual da data e do descritor da lista.
	 */
	public String buscaListaPorItem(int itemId, int posicaoLista) {
		return controleListas.buscaListaPorItem(itemId, posicaoLista);
	}

	/**
	 * Esse metodo serve para pesquisar uma lista que foram cridas em determinada
	 * data.
	 * 
	 * @param data
	 *            de criacao das lista.
	 * 
	 * @return descritores das listas cridas nesta data.
	 */
	public String pesquisaListasDeComprasPorData(String data) {
		verificaData(data);

		return controleListas.pesquisaListasDeComprasPorData(data);
	}

	/**
	 * Gera lista automaticamente com os mesmo itens da ultima lista criada.
	 * 
	 * @return decritor da nova lista.
	 */
	public String geraAutomaticaUltimaLista() {
		return controleListas.geraAutomaticaUltimaLista(dataAtual(), horaAtual());
	}

	/**
	 * Gera lista automaticamente com os mesmo itens da ultima lista criada que
	 * contem um determinado item.
	 * 
	 * @param descritorItem
	 *            item que ira ser procurado para criar a nova lista.
	 * @return decritor da nova lista.
	 */
	public String geraAutomaticaItem(String descritorItem) {
		return controleListas.geraAutomaticaItem(descritorItem, dataAtual(), horaAtual());
	}

	/**
	 * Gera lista automaticamente uma lista com oa itens que apareceram em 50% das
	 * listas.
	 * 
	 * 
	 * @return decritor da nova lista.
	 */
	public String geraAutomaticaItensMaisPresentes() {
		HashMap<Integer, Integer> idsEQuantidades = controleListas
				.geraListaDeIdsEQuantidadesItensMaisRecorrentes(controleItem.getIds());
		HashMap<Integer, Item> idsEItensParaListaAutomatica = controleItem
				.getItensParaListaAutomatica(idsEQuantidades.keySet());

		return controleListas.geraAutomaticaItensMaisPresentes(dataAtual(), horaAtual(), idsEItensParaListaAutomatica,
				idsEQuantidades);
	}

	/**
	 * Metodo que retorna impresso os supermercados que possuem itens de uma lista
	 * de compras e os itens que cada um desses supermercados possui.
	 * 
	 * @param descritorLista
	 *            Lista na qual se deseja verificar o melhor preco.
	 * 
	 * @return Saida de texto com os nomes dos supermercados e os itens que cada
	 *         supermercado possui.
	 */
	public String sugereMelhorEstabelecimento(String descritorLista) {
		ArrayList<SupermercadoComItensOrdenavel> supermercadosComItens = ordenaSuperMercados(descritorLista);

		return controleSuperMercados.sugereMelhorEstabelecimento(supermercadosComItens);
	}

	/**
	 * Metodo que retorna a partir dos supermercados que possui itens de uma lista,
	 * um supermercado e um item em posicoes especificas, passadas como parametros.
	 * 
	 * Caso a posicao do item seja 0, e tratado na classe superMercadoComItens para
	 * retornar o nome do supermercado em questao.
	 * 
	 * @param descritorLista
	 *            decritor da lista de compras.
	 * @param posicaoSupermercado
	 *            Posicao do supermercado desejado.
	 * @param posicaoItem
	 *            Posicao do item desejado.
	 * @return Saida de texto com os nomes dos supermercados e os itens que cada
	 *         supermercado possui.
	 */
	public String sugereMelhorEstabelecimento(String descritorLista, int posicaoSupermercado, int posicaoItem) {
		ArrayList<SupermercadoComItensOrdenavel> supermercadosComItens = ordenaSuperMercados(descritorLista);

		return controleSuperMercados.sugereMelhorEstabelecimento(supermercadosComItens, posicaoSupermercado,
				posicaoItem);
	}

	/**
	 * Metodo que retorna a data atual no formato dd/MM/yyyy.
	 * 
	 * @return data atual.
	 */
	public String dataAtual() {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		return formatador.format(data);
	}

	private String horaAtual() {
		Date hora = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("hh:mm:ss:S");

		return formatador.format(hora);
	}

	private ArrayList<SupermercadoComItensOrdenavel> ordenaSuperMercados(String descritorLista) {
		HashMap<Integer, Integer> itensEQuantidades = controleListas.retornaItensEQuantidadesDeUmaLista(descritorLista);
		ArrayList<SupermercadoComItensOrdenavel> supermercadosComItens;

		supermercadosComItens = controleSuperMercados.retornaSuperMercadosComItens(itensEQuantidades);

		for (SupermercadoComItensOrdenavel supermercado : supermercadosComItens) {
			ArrayList<String> saidaTextoSuperMercado;
			saidaTextoSuperMercado = controleListas.ordenaProdutosParaSuperMercado(supermercado.getIds(),
					descritorLista);
			supermercado.setSaidaTexto(saidaTextoSuperMercado);
		}

		return supermercadosComItens;

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
	 * Assim como para: valores invalidos e locais de compras vazios ou nulos.
	 * 
	 * @param id
	 *            - identificador unico do produto
	 */
	private void validaCadastroPreco(int id, String localDeCompras, double valor) {
		if (id <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de preco: id de item invalido.");
		} else if (!controleItem.verificaPresencaItem(id)) {
			throw new IllegalArgumentException("Erro no cadastro de preco: item nao existe.");
		}

		if (localDeCompras == null || localDeCompras.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro no cadastro de preco: local de compra nao pode ser vazio ou nulo.");
		}

		if (valor <= 0) {
			throw new IllegalArgumentException("Erro no cadastro de preco: preco de item invalido.");
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
		if (descritorLista.trim().isEmpty() || descritorLista == null) {
			throw new IllegalArgumentException("Erro na pesquisa de compra: descritor nao pode ser vazio ou nulo.");
		}

		if (!controleListas.verificaPresencaDeLista(descritorLista)) {
			throw new IllegalArgumentException("Erro na pesquisa de compra: lista de compras nao existe.");
		}
	}

	private void validaDeletaItem(int id) {
		if (!controleItem.verificaPresencaItem(id)) {
			throw new IllegalArgumentException("Erro ao deletar item: esse item nao existe");
		}
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
