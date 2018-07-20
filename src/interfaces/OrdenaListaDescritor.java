package interfaces;

import java.util.Comparator;

public class OrdenaListaDescritor implements Comparator<ListaOrdenavel>{

	@Override
	public int compare(ListaOrdenavel o1, ListaOrdenavel o2) {
		return o1.getDescritor().toLowerCase().compareTo(o2.getDescritor().toLowerCase());
	}

}
