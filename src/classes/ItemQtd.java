package classes;

import classes.Item;

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
		String result = "";
		for(String supermercados : super.precos.keySet()) {
			result += supermercados + ", " + super.precos.get(supermercados) + ";";
		}	
		return super.ID + "." + super.nome + ", " + super.categoria + ", " + this.qtd + " " + this.unidadeDeMedida + ", Preco: <" + result + ">";
	}

}