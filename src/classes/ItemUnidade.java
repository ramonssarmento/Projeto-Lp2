package classes;

/**
 * Classe extendida de Item, que implementa itens que seus precos sao calculados
 * atraves da quantidade(unidade) passada
 *
 */
public class ItemUnidade extends Item {
	int unidade;

	/**
	 * Construtor que inicializa os atributos da classe Item, os atributos da classe
	 * ItemUnidade e verifica as exececoes lancadas para ItemUnidade é valido
	 * 
	 * @param id,
	 *            identificador unico do produto
	 * @param nome,
	 *            nome do produto
	 * @param categoria,
	 *            categoria a qual o produto pertence
	 * @param unidade,
	 *            unidade do produto
	 * @param localDeCompra,
	 *            supermercado o qual o produto sera adicionado
	 * @param preco,
	 *            preco do produto
	 */
	public ItemUnidade(int id, String nome, String categoria, int unidade, String localDeCompra, double preco) {
		super(id, nome, categoria, localDeCompra, preco);
		validaItemUnidade(unidade);
		this.unidade = unidade;
	}

	/**
	 * Atualiza item passando novos atributos, e verifica se os novos atributos
	 * passados sao validos
	 */
	@Override
	public boolean atualizaItem(String atributo, String novoValor) {
		if (super.atualizaItem(atributo, novoValor)) {
			return true;
		}
		switch (atributo) {
		case "unidade":
			this.unidade = verificaUnidades(novoValor);
			return true;
		default:
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao existe.");
		}
	}

	@Override
	public String getDescricao() {
		return super.getNome() + ", " + super.getCategoria();
	}

	@Override
	public String toString() {
		return super.toString() + ", Preco: " + super.getPrecos();
	}

	/**
	 * Lanca excecoes para verificar se as unidades passadas sao validas
	 * 
	 * @param valor,
	 *            valor das unidades
	 * @return a quantidade de Unidades
	 */
	private int verificaUnidades(String valor) {
		int unidades;
		try {
			unidades = Integer.parseInt(valor);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Erro na atualizacao de item: unidade nao inteira.");
		}

		if (unidades < 0) {
			throw new IllegalArgumentException(
					"Erro na atualizacao de item: valor de unidade nao pode ser menor que zero.");
		}
		return unidades;

	}

	/**
	 * Lanca excecoes para verificar se a quantidade de unidades e valida
	 * 
	 * @param unidade,
	 *            unidade passada
	 */
	private void validaItemUnidade(int unidade) {
		if (unidade <= 0) {
			throw new IllegalArgumentException(
					"Erro no cadastro de item: valor de unidade nao pode ser menor que zero.");
		}

	}
}
