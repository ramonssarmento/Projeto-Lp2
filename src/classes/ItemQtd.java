package classes;

import classes.Item;

public class ItemQtd extends Item {
	private int qtd;
	private String unidadeDeMedida;

	public ItemQtd(int id, String nome, String categoria, int qtd, String unidadeDeMedida, String localDeCompra,
			double preco) {
		super(id, nome, categoria, localDeCompra, preco);
		validaItemQtd(qtd, unidadeDeMedida);
		this.qtd = qtd;
		this.unidadeDeMedida = unidadeDeMedida;
	}
	
	@Override
	public void atualizaItem(String atributo, String novoValor) {
		super.validaAtualizaItem(atributo, novoValor);
		switch (atributo) {
		case "nome":
			super.setNome(novoValor);
			break;
		case "categoria":
			if (super.verificaCategoria(novoValor)) {
				super.setCategoria(novoValor);
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
		return super.toString() + ", " + this.qtd + " " + this.unidadeDeMedida
				+ ", Preco: " + super.getPrecos();
	}

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