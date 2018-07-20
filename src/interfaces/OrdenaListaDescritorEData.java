package interfaces;

import java.util.Comparator;

public class OrdenaListaDescritorEData implements Comparator<ListaOrdenavel> {

	@Override
	public int compare(ListaOrdenavel lista1, ListaOrdenavel lista2) {
		
		String[] data1 = lista1.getData().split("/");
		String[] data2 = lista2.getData().split("/");
		
		String descritor1 = lista1.getDescritor();
		String descritor2 = lista2.getDescritor();
		
		int dia1 = Integer.parseInt(data1[0]);
		int dia2 = Integer.parseInt(data2[0]);
		int mes1 = Integer.parseInt(data1[1]);
		int mes2 = Integer.parseInt(data2[1]);
		int ano1 = Integer.parseInt(data1[2]);
		int ano2 = Integer.parseInt(data2[2]);
		
		return ano1 < ano2 ? -1 : ano1 > ano2 ? 1 : mes1 < mes2 ? -1 : mes1 > mes2 ? 1 : dia1 < dia2 ? -1 : dia1 > dia2 ? 1 : descritor1.toLowerCase().compareTo(descritor2.toLowerCase());
	}

}
