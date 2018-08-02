package interfaces;

import java.util.ArrayList;

public interface SupermercadoComItensOrdenavel {
	
	public double getValorTotal();
	
	public String getNome();

	public ArrayList<Integer> getIds();

	public void setSaidaTexto(ArrayList<String> saidaTextoSuperMercado);

	public String getItemOrdenado(int posicaoItem);
}
