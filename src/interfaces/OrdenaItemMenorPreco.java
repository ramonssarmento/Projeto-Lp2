package interfaces;

import java.util.Comparator;

public class OrdenaItemMenorPreco implements Comparator<ItemCompravel> {

	@Override
	public int compare(ItemCompravel arg0, ItemCompravel arg1) {
		if (arg0.getMenorPreco() == arg1.getMenorPreco()) {
			return  arg1.getId() - arg0.getId();
		}
		return arg0.getMenorPreco() < arg1.getMenorPreco() ? -1 : 1;
	}

}
