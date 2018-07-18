package classes;

/**
 * Classe extendida de Item, implementa itens que seus precos são calculados a
 * partir do peso do kg passado como parametro
 *
 */
public class ItemQuilo extends Item {

	private double kg;

	/**
	 * Construtor que inicializa os atributos da classe Item, os atributos da classe
	 * ItemQuilo e verifica a excecao feita para validar itens que calculam preco
	 * por kg
	 * 
	 * @param id,
	 *            identificador unico do item
	 * @param nome,
	 *            nome do item
	 * @param categoria,
	 *            categoria a qual o item pertence
	 * @param kg,
	 *            quantidade de kg
	 * @param localDeCompra,
	 *            supermercado o qual o item sera adicionado
	 * @param preco,
	 *            preco do item
	 */
	public ItemQuilo(int id, String nome, String categoria, double kg, String localDeCompra, double preco) {
		super(id, nome, categoria, localDeCompra, preco);
		validaItemQuilo(kg);
		this.kg = kg;
	}

	/**
	 * Atualiza o item passando novos atributo e verifica se os novos atributos sao
	 * validos
	 */
	@Override
	public boolean atualizaItem(String atributo, String novoValor) {

		if (super.atualizaItem(atributo, novoValor)) {
			return true;
		}

		switch (atributo) {
		case "kg":
			this.kg = verificaQuilo(novoValor);
			return true;
		default:
			throw new IllegalArgumentException("Erro na atualizacao de item: atributo nao existe.");
		}
	}

	@Override
	public String toString() {
		return super.toString() + ", Preco por quilo: " + super.getPrecos();
	}

	@Override
	public String getDescricao() {
		return super.getNome() + ", " + super.getCategoria();
	}

	/**
	 * Lanca excecao para verificar se os paramentros passados sao validos
	 * 
	 * @param valor,
	 *            valor da quantidade passado
	 * @return a quantidade do item
	 */
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

	/**
	 * Lanca exececao para verificar se a quantidade de kg passado como parametro
	 * pode ser usada
	 * 
	 * @param kg,
	 *            quantidade de kg
	 */
	private void validaItemQuilo(double kg) {
		if (kg <= 0) {
			throw new IllegalArgumentException(
					"Erro no cadastro de item: valor de quilos nao pode ser menor que zero.");
		}
	}
}
