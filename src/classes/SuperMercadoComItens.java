package classes;

import java.util.ArrayList;

import interfaces.SupermercadoComItensOrdenavel;

public class SuperMercadoComItens implements SupermercadoComItensOrdenavel {

	private String nomeSupermercado;
	private double valorTotal;
	private ArrayList<Integer> produtosIds;
	private ArrayList<String> saidaTexto;

	public SuperMercadoComItens(String nomeSupermercado, double valorTotal, ArrayList<Integer> produtosIds) {
		this.nomeSupermercado = nomeSupermercado;
		this.valorTotal = valorTotal;
		this.produtosIds = produtosIds;
	}

	@Override
	public String getNome() {
		return String.format("%s: R$ %.2f", this.nomeSupermercado, this.valorTotal);
	}

	@Override
	public double getValorTotal() {
		return this.valorTotal;
	}

	public ArrayList<Integer> getIds() {
		return produtosIds;
	}

	public String getItemOrdenado(int posicaoItem) {
		if (posicaoItem >= 0 && posicaoItem <= this.saidaTexto.size()) {

			if (posicaoItem == 0) {
				return this.getNome();
			}

			return this.saidaTexto.get(posicaoItem - 1);
		}

		return "";

	}

	public void setSaidaTexto(ArrayList<String> saidaTexto) {
		this.saidaTexto = saidaTexto;
	}

	@Override
	public String toString() {
		String saida = this.getNome() + System.lineSeparator();

		for (String saidaTexto : this.saidaTexto) {
			saida += "    " + saidaTexto + System.lineSeparator();
		}

		return saida.trim();
	}
}
