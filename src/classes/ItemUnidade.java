package classes;

public class ItemUnidade extends Item {
	int unidade;

	public ItemUnidade(int id, String nome, String categoria, int unidade, String localDeCompra, double preco) {
		super(id, nome, categoria, localDeCompra, preco);
		validaItemUnidade(unidade);
		this.unidade = unidade;
	}

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

	private void validaItemUnidade(int unidade) {
		if (unidade <= 0) {
			throw new IllegalArgumentException(
					"Erro no cadastro de item: valor de unidade nao pode ser menor que zero.");
		}

	}
}
