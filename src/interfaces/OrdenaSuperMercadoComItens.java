package interfaces;

import java.util.Comparator;

public class OrdenaSuperMercadoComItens implements Comparator<SupermercadoComItensOrdenavel>{

	@Override
	public int compare(SupermercadoComItensOrdenavel supermercado1, SupermercadoComItensOrdenavel supermercado2) {
		
		return supermercado1.getValorTotal() > supermercado2.getValorTotal() ? 1 : 
			supermercado1.getValorTotal() < supermercado2.getValorTotal() ? -1 : 
				supermercado1.getNome().toLowerCase().compareTo(supermercado2.getNome().toLowerCase());
	}
	
	
	

}
