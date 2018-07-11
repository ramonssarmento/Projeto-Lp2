package classes;

public class ItemQuilo extends Item {

	private double kg;

	public ItemQuilo(int id, String nome, String categoria, double kg, String localDeCompra, double preco) {
		super(id, nome, categoria, localDeCompra, preco);
		validaItemQuilo(kg);
		this.kg = kg;
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
		case "kg":
			this.kg = verificaQuilo(novoValor);
			break;
		default:
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao existe.");
		}
	}

	@Override
	public String toString() {
		return super.id + ". " + super.nome + ", " + super.categoria + ", Preco por quilo: " + super.toString();
	}

	private double verificaQuilo(String valor) {
		double quantidade;
		try {
			quantidade = Double.parseDouble(valor);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Erro na atualizacao de item: quilo nao numerica.");
		}

		if (quantidade < 0) {
			throw new IllegalArgumentException(
					"Erro na atualizacao de item: valor de quilos nao pode ser menor que zero.");
		}
		return quantidade;

	}

	private void validaItemQuilo(double kg) {
		if (kg <= 0) {
			throw new IllegalArgumentException(
					"Erro no cadastro de item: valor de quilos nao pode ser menor que zero.");
		}
	}
}
