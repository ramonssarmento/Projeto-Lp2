package interfaces;

public interface ItemCompravel extends Comparable<ItemCompravel>{

	public double getMenorPreco();

	public String getNome();

	public int getId();
	
	public String getCategoria();

	@Override
	public int compareTo(ItemCompravel o);
}
