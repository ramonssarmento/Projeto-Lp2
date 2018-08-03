package classes;

/**
 * Classe extendida de Item, implementa itens que seus precos sao calculados por
 * quantidade
 *
 */
public class ItemQtd extends Item {
	private int qtd;
	private String unidadeDeMedida;

	/**
	 * Construtor que inicializa os atributos da classe Item, os atributos da classe
	 * ItemQtd e verifica a excecao feita para itens que calcula preco por
	 * quantidade
	 * 
	 * @param id,
	 *            identificador unico do produto
	 * @param nome,
	 *            nome do produto
	 * @param categoria,
	 *            categoria a qual o produto pertence
	 * @param qtd,
	 *            quantidade que uma unidade do produto possui
	 * @param unidadeDeMedida,
	 *            unidade de medida padrão do produto
	 * @param localDeCompra,
	 *            mercado o qual o produto sera adicionado
	 * @param preco,
	 *            preco do produto
	 */
	public ItemQtd(int id, String nome, String categoria, int qtd, String unidadeDeMedida, String localDeCompra,
			double preco) {
		super(id, nome, categoria, localDeCompra, preco);
		validaItemQtd(qtd, unidadeDeMedida);
		this.qtd = qtd;
		this.unidadeDeMedida = unidadeDeMedida;
	}

	/**
	 * Atualiza o item passando novos atributos, e verifica se os novos atributos
	 * sao validos
	 */
	@Override
	public boolean atualizaItem(String atributo, String novoValor) {
		if (super.atualizaItem(atributo, novoValor)) {
			return true;
		}

		switch (atributo) {
		case "quantidade":
			this.qtd = verificaQtd(novoValor);
			return true;
		case "unidade de medida":
			this.unidadeDeMedida = novoValor;
			return true;
		default:
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao existe.");
		}
	}

	@Override
	public String getDescricao() {
		return super.getNome() + ", " + super.getCategoria() + ", " + this.qtd + " " + this.unidadeDeMedida;
	}

	@Override
	public String toString() {
		return super.toString() + ", " + this.qtd + " " + this.unidadeDeMedida + ", Preco: " + super.getPrecos();
	}

	/**
	 * Lanca exececoes para verificar se a quantidade de itens passados é valida
	 * 
	 * @param valor,
	 *            valor da quantidade passada
	 * @return quantidade do item
	 */
	private int verificaQtd(String valor) {
		int quantidade;
		try {
			quantidade = Integer.parseInt(valor);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Erro na atualizacao de item: quantidade nao inteira.");
		}

		if (quantidade < 0) {
			throw new IllegalArgumentException(
					"Erro na atualizacao de item: valor de quantidade nao pode ser menor que zero.");
		}
		return quantidade;

	}

	/**
	 * Lanca exececoes para verificar se a quantidade e unidade de medida é valida
	 * 
	 * @param qtd,
	 *            quantidade disponivel em uma unidade do produto
	 * @param unidadeDeMedida,
	 *            unidade de medida padrao do produto
	 */
	private void validaItemQtd(int qtd, String unidadeDeMedida) {
		if (qtd <= 0) {
			throw new IllegalArgumentException(
					"Erro no cadastro de item: valor de quantidade nao pode ser menor que zero.");
		} else if (unidadeDeMedida == null || unidadeDeMedida.trim().isEmpty()) {
			throw new IllegalArgumentException(
					"Erro no cadastro de item: unidade de medida nao pode ser vazia ou nula.");
		}

	}

}