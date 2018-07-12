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
	 * @param id
	 * @param nome
	 * @param categoria
	 * @param unidade
	 * @param localDeCompra
	 * @param preco
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
	public void atualizaItem(String atributo, String novoValor) {
		super.validaAtualizaItem(atributo, novoValor);
		switch (atributo) {
		case "nome":
			this.nome = novoValor;
			break;
		case "categoria":
			if (super.verificaCategoria(novoValor)) {
				this.categoria = novoValor;
				break;
			}
			throw new IllegalArgumentException("Erro na atualizacao de item: categoria nao existe.");
		case "unidade":
			this.unidade = verificaUnidades(novoValor);
			break;
		default:
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao existe.");
		}
	}

	@Override
	public String toString() {
		return super.id + ". " + super.nome + ", " + super.categoria + ", Preco: " + super.toString();
	}

	/**
	 * Lanca excecoes para verificar se as unidades passadas sao validas
	 * 
	 * @param valor
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
	 * @param unidade
	 */
	private void validaItemUnidade(int unidade) {
		if (unidade <= 0) {
			throw new IllegalArgumentException(
					"Erro no cadastro de item: valor de unidade nao pode ser menor que zero.");
		}

	}
}
