package Controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JLabel;

import classes.Item;
import classes.ItemQtd;
import classes.ItemQuilo;
import classes.ItemUnidade;
import classes.ListaDeCompras;
import classes.SuperMercado;
import interfaces.ItemCompravel;
import interfaces.OrdenaItemMenorPreco;
import interfaces.OrdenaListaDescritorEData;

/**
 * Classe controladora do sistema, que permite adicionar os itens em suas
 * respectivas categorias, exibi-los, atualizar/deletar, recuperar informacoes.
 * Tambem e possivel a manipulacao de listas de compras
 *
 */
public class Controller {
	private int id;
	private HashMap<Integer, Item> itens;
	private HashMap<String, SuperMercado> superMercados;
	private HashMap<String, ListaDeCompras> listasDeCompras;

	/**
	 * Construtor inicializa o idenficador unico como zero, e o mapa que será
	 * armazenado os itens
	 */
	public Controller() {
		this.id = 0;
		this.itens = new HashMap<>();
		this.superMercados = new HashMap<>();
		this.listasDeCompras = new HashMap<>();
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
		
		ItemQtd novoItem = new ItemQtd(this.id+1, nome, categoria, qtd, unidadeDeMedida, localDeCompra, preco);
		if (this.itens.containsValue(novoItem)) {
			throw new IllegalArgumentException("Item ja adicionado");
		}
		this.id++;
		this.itens.put(this.id, novoItem);

		adicionaItemNoSupermercado(localDeCompra, preco);

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
		
		ItemQuilo novoItem = new ItemQuilo(this.id+1, nome, categoria, kg, localDeCompra, preco);
		if (this.itens.containsValue(novoItem)) {
			throw new IllegalArgumentException("Item ja adicionado");
		}
		this.id++;
		this.itens.put(this.id, novoItem);

		adicionaItemNoSupermercado(localDeCompra, preco);

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
		
		ItemUnidade novoItem = new ItemUnidade(this.id+1, nome, categoria, unidade, localDeCompra, preco);
		if (this.itens.containsValue(novoItem)) {
			throw new IllegalArgumentException("Item ja adicionado");
		}
		this.id++;
		this.itens.put(this.id, novoItem);

		adicionaItemNoSupermercado(localDeCompra, preco);

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
		validaListagem(id);
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
		validaAtualizacao(id);
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
		validaCadastroPreco(id);
		itens.get(id).adicionaPrecoItem(localDeCompra, preco);
	}

	/**
	 * Deleta um item presente no mapa de itens
	 * 
	 * @param id,
	 *            identificador unico
	 */
	public void deletaItem(int id) {
		// Futuramente colocar um validador de id (n�o possui esse caso)
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

	public String adicionaListaDeCompras(String descritorLista) {
		validaAdicionaLista(descritorLista);
		String data = adicionaDataFormatada();
		ListaDeCompras lista = new ListaDeCompras(descritorLista, data);

		this.listasDeCompras.put(descritorLista, lista);

		return descritorLista;
	}

	public void adicionaCompraALista(String descritorLista, int quantidade, int itemId) {
		validaCompraELista(descritorLista, itemId);
		
		Item item = this.itens.get(itemId);
		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);
		
		lista.criaProdutoLista(item, quantidade);
	}

	public void finalizarListaDeCompras(String descritorLista, String localDaCompra, int valorFinalDaCompra) {
		validaLista(descritorLista);
		
		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);
		
		lista.finalizarLista(localDaCompra, valorFinalDaCompra);
	}

	public String pesquisaCompraEmLista(String descritorLista, int itemId) {
		validaCompraELista(descritorLista, itemId);
		
		Item item = this.itens.get(itemId);
		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);
		
		return lista.pesquisaCompraEmLista(itemId);
		
	}

	public void atualizaCompraDeLista(String descritorLista, int itemId, String operacao, int quantidade) {
		validaCompraELista(descritorLista, itemId);
		
		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);
		
		lista.atualizaProduto(itemId, operacao, quantidade);
	}

	public String getItemLista(String descritorLista, int itemPosicao) {
		validaLista(descritorLista);
		
		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);
		
		return lista.retornaItemPosicao(itemPosicao);
		
	}

	public void deletaCompraDeLista(String descritorLista, int itemId) {
		validaCompraELista(descritorLista, itemId);
		
		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);
		
		lista.deletaProdutoLista(itemId);
	}

	public String imprimirListaDeCompras(String descritorLista) {
		validaLista(descritorLista);
		
		ListaDeCompras lista = this.listasDeCompras.get(descritorLista);
		
		return lista.toString();
	}

	public String buscaListaPorData(String data, int posicaoLista) {
		String saida = "";
		SortedSet<String> saidaOrdenada =  new TreeSet<String>();
		
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
	
	/**
	 * Esse método serve para pesquisar listas que possuem um determinado item.
	 * Essas listas são ordenadas primeiramente pelas datas, com as mais antigas vindo à frente
	 * Caso hajam datas iguais, as listas são ordenadas alfabéticamente.
	 *  
	 * @param itemId - Id do itém à ser pesquisado.
	 * 
	 * @return - Representação textual das datas e dos descritores das listas.
	 */
	
	public String buscaListaPorItem(int itemId) {
		List<ListaDeCompras> listaSaidaOrdenada = new LinkedList();
		
		for (ListaDeCompras lista : this.listasDeCompras.values()) {
			if (lista.getExistenciaDeItem(itemId)) {
				listaSaidaOrdenada.add(lista);
			}
		}
		
		if (listaSaidaOrdenada.size() == 0) {
			throw new IllegalArgumentException("Erro de busca: nao existem listas com esse item!");
		}
		
		Collections.sort(listaSaidaOrdenada, new OrdenaListaDescritorEData());
		
		String saida = "";
		for (ListaDeCompras lista : listaSaidaOrdenada) {
			saida += lista.getDescritorComData() + System.lineSeparator();
		}
		
		return saida.trim();
	}
	
	/**
	 * Esse método serve para pesquisar uma lista que contém um determinado item, pesquisando pela posição da lista.
	 * Essas listas são ordenadas primeiramente pelas datas, com as mais antigas vindo à frente
	 * Caso hajam datas iguais, as listas são ordenadas alfabéticamente.
	 *  
	 * @param itemId - Id do itém à ser pesquisado.
	 * 
	 * @param posicaoLista - Posição da lista desejada.
	 * 
	 * @return - Representação textual da data e do descritor da lista.
	 */
	
	public String buscaListaPorItem(int itemId, int posicaoLista) {
		List<ListaDeCompras> listaSaidaOrdenada = new LinkedList();
		
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
			throw new IllegalArgumentException("Erro de busca: posicao invalida ou inexistente entre as listas com esse item!");
		}
		
		return listaSaidaOrdenada.get(posicaoLista).getDescritorComData();
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
		} else if (!this.itens.containsKey(id)) {
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
		if (!this.itens.containsKey(id)) {
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
		} else if (!this.itens.containsKey(id)) {
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

	/**
	 * Verifica se determinado supermercado contém tal produto
	 * 
	 * @param nomeSupermercado,
	 *            nome do supermercado o qual deseja procurar @return, um boolenado
	 *            true se houver, false se não houver
	 */
	private boolean verificaPresencaDeSupermercado(String nomeSupermercado) {
		if (this.superMercados.containsKey(nomeSupermercado)) {
			return true;
		}

		return false;
	}

	/**
	 * Adiciona determinado produto ao supermercado
	 * 
	 * @param localDeCompra,
	 *            supermercado o qual o produto sera adicionado
	 * @param preco,
	 *            preco do produto
	 */
	private void adicionaItemNoSupermercado(String localDeCompra, double preco) {
		if (verificaPresencaDeSupermercado(localDeCompra)) {
			this.superMercados.get(localDeCompra).adicionarItem(this.id, preco);
		}

		else {

			SuperMercado superMercado = new SuperMercado(localDeCompra);
			this.superMercados.put(localDeCompra, superMercado);
			this.superMercados.get(localDeCompra).adicionarItem(this.id, preco);
		}
	}

	private void validaAdicionaLista(String descritorLista) {
		if (descritorLista == null || descritorLista.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro na criacao de lista de compras: descritor nao pode ser vazio ou nulo.");
		}
		if (this.listasDeCompras.containsKey(descritorLista)) {
			throw new IllegalArgumentException("Erro na criacao de lista de compras: Lista ja cadastrada");
		}
	}

	private void validaCompraELista(String descritorLista, int itemId) {
		if (descritorLista == null || descritorLista.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na compra de item: descritor nao pode ser vazio ou nulo.");
		}
		
		if (!this.itens.containsKey(itemId)) {
			throw new IllegalArgumentException("Erro na compra de item: item nao existe no sistema.");
		}
		
		if (!this.listasDeCompras.containsKey(descritorLista)) {
			throw new IllegalArgumentException("Erro na compra de item: Lista nao cadastrada");
		}

	}
	
	private void validaLista(String descritorLista){
		if (descritorLista == null || descritorLista.trim().isEmpty()) {
			throw new IllegalArgumentException("Erro na compra de item: descritor nao pode ser vazio ou nulo.");
		}
		
		if (!this.listasDeCompras.containsKey(descritorLista)) {
			throw new IllegalArgumentException("Erro na compra de item: Lista nao cadastrada");
		}
		
	}

	private String adicionaDataFormatada() {

		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

		return formatador.format(data);
	}
	
	// Apenas para testes
	public void setData(String nomeLista, String novaData) {
		this.listasDeCompras.get(nomeLista).setData(novaData);
	}
		
}
