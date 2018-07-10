package classes;

public class ItemQtd extends Item {
	private int qtd;
	private String unidadeDeMedida;
	
	public ItemQtd(int ID, String nome, String categoria, int qtd, String unidadeDeMedida, String localDeCompra, double preco) {
		super(ID, nome, categoria, localDeCompra, preco);
		this.qtd = qtd;
		this.unidadeDeMedida = unidadeDeMedida;
	}
	
	@Override
	public String toString() {
		return super.ID + "." + super.nome + ", " + super.categoria
	}

}
