package interfaces;

import classes.ListaDeCompras;

public interface ListaOrdenavel {
	
	public String getData();
	
	public String getHora();
	
	public String getDescritor();

	public ListaDeCompras getClone(String descritor, String data, String hora);
}
