package interfaces;

public interface ItemCompravel extends Comparable<ItemCompravel> {

	public double getMenorPreco();

	public String getNome();
	
	public int getId();

	@Override
	public default int compareTo(ItemCompravel o) {
		if (this.getNome().equals(o.getNome())) {
			return o.getId() - this.getId();
		}
		return this.getNome().compareTo(o.getNome());
	}
}
