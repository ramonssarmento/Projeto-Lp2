package classes;

import classes.Item;

/**
 * Classe extendida de Item, implementa itens que seus precos sao calculados por
 * quantidade
 *
 */
public class ItemQtd extends Item {
	private int qtd;
	private String unidadeDeMedida;

	/**
	 * Construtor que inicializa os atributos da classe Item, os atributos da classe ItemQtd e
	 * verifica a excecao feita para itens que calcula preco por quantidade
	 * 
	 * @param id
	 * @param nome
	 * @param categoria
	 * @param qtd
	 * @param unidadeDeMedida
	 * @param localDeCompra
	 * @param preco
	 */
	public ItemQtd(int id, String nome, String categoria, int qtd, String unidadeDeMedida, String localDeCompra,
			double preco) {
		super(id, nome, categoria, localDeCompra, preco);
		validaItemQtd(qtd, unidadeDeMedida);
		this.qtd = qtd;
		this.unidadeDeMedida = unidadeDeMedida;
	}

	/**
	 * Atualiza o item passando novos atributos, e verifica se os novos
	 * atributos sao validos
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
		case "quantidade":
			this.qtd = verificaQtd(novoValor);
			break;
		case "unidade de medida":
			this.unidadeDeMedida = novoValor;
			break;
		default:
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao existe.");
		}
	}

	@Override
	public String toString() {
		return super.id + ". " + super.nome + ", " + super.categoria + ", " + this.qtd + " " + this.unidadeDeMedida
				+ ", Preco: " + super.toString();
	}

	/**
	 * Lanca exececoes para verificar se a quantidade de itens passados é valida
	 * 
	 * @param valor
	 * @return
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
	 * @param qtd
	 * @param unidadeDeMedida
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