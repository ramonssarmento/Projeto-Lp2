package interfaces;

import java.util.Comparator;

public class OrdenaDataEHora implements Comparator <ListaOrdenavel>{
	
	@Override
	public int compare(ListaOrdenavel lista1, ListaOrdenavel lista2) {
		
		String[] data1 = lista1.getData().split("/");
		String[] data2 = lista2.getData().split("/");
		
		String[] hora1 = lista1.getHora().split(":");
		String[] hora2 = lista2.getHora().split(":");
		
		int dia1 = Integer.parseInt(data1[0]);
		int dia2 = Integer.parseInt(data2[0]);
		int mes1 = Integer.parseInt(data1[1]);
		int mes2 = Integer.parseInt(data2[1]);
		int ano1 = Integer.parseInt(data1[2]);
		int ano2 = Integer.parseInt(data2[2]);
		int horas1 = Integer.parseInt(hora1[0]);
		int horas2 = Integer.parseInt(hora2[0]);
		int minutos1 = Integer.parseInt(hora1[1]);
		int minutos2 = Integer.parseInt(hora2[1]);
		int segundos1 = Integer.parseInt(hora1[2]);
		int segundos2 = Integer.parseInt(hora2[2]);
		int milesimos1 = Integer.parseInt(hora1[3]);
		int milesimos2 = Integer.parseInt(hora2[3]);
		
		
		return ano1 > ano2 ? -1 : ano1 < ano2 ? 1 : mes1 > mes2 ? -1 : 
			mes1 < mes2 ? 1 : dia1 > dia2 ? -1 : dia1 < dia2 ? 1 :
			horas1 > horas2 ? -1 : horas1 < horas2 ? 1 : minutos1 > minutos2 ? -1 : 
				minutos1 < minutos2 ? 1 : segundos1 > segundos2 ? -1 : segundos1 < segundos2 ? 1 : 
					milesimos1 > milesimos2 ? -1 : 1;
	}
	
	

}
