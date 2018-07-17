package interfaces;

import java.util.Comparator;

public class OrdenaItemLista implements Comparator<ItemCompravel> {

	@Override
	public int compare(ItemCompravel o1, ItemCompravel o2) {
		int v1, v2;
		switch (o1.getCategoria()) {
		case "higiene pessoal":
			v1 = 1;
			break;
		case "limpeza":
			v1 = 2;
			break;
		case "alimento industrializado":
			v1 = 3;
			break;

		default:
			v1 = 4;
			break;
		}

		switch (o2.getCategoria()) {
		case "higiene pessoal":
			v2 = 1;
			break;
		case "limpeza":
			v2 = 2;
			break;
		case "alimento industrializado":
			v2 = 3;
			break;

		default:
			v2 = 4;
			break;
		}

		return v1 < v2 ? 1 : v1 > v2 ? 1 : o1.getNome().compareTo(o2.getNome());

	}

}
