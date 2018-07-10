package classes;

import classes.Item;

public class ItemQtd extends Item {
	private int qtd;
	private String unidadeDeMedida;

	public ItemQtd(int ID, String nome, String categoria, int qtd, String unidadeDeMedida, String localDeCompra,
			double preco) {
		super(ID, nome, categoria, localDeCompra, preco);
		this.qtd = qtd;
		this.unidadeDeMedida = unidadeDeMedida;
	}

	@Override
	public void atualizaItem(String atributo, String novoValor) {
		super.validaAtualizaItem(atributo, novoValor);
		switch (atributo) {
		case "nome":
			this.nome = novoValor;
			break;
		case "categoria":
			if (verificaCategoria(novoValor)) {
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

	private int verificaQtd(String valor) {
		int quantidade;
		try {
			quantidade = Integer.parseInt(valor);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}

		if (quantidade < 0) {
			throw new IllegalArgumentException(
					"Erro na atualizacao de item: valor de quantidade nao pode ser menor que zero.");
		}
		return quantidade;

	}

	@Override
	public String toString() {
		return super.ID + "." + super.nome + ", " + super.categoria + ", " + this.qtd + " " + this.unidadeDeMedida
				+ super.toString();
	}

}