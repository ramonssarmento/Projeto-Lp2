package classes;

import java.util.ArrayList;

public class ListaDeCompras {

	private String descritor, data;
	private ArrayList<ProdutoLista> produtosLista;

	public ListaDeCompras(String descritor, String data) {

		this.descritor = descritor;
		this.data = data;
		this.produtosLista = new ArrayList<>();

	}

	public void criaProdutoLista(Item item, int quantidade) {

		ProdutoLista produto = new ProdutoLista(item, quantidade);
		adicionaProdutoNaLista(produto);

	}

	private void adicionaProdutoNaLista(ProdutoLista produto) {

		if (!verificaPresencaNaListaProduto(produto)) {
			this.produtosLista.add(produto);
		}

		else {
			throw new IllegalArgumentException("Esse produto ja foi adicionado!");
		}
	}

	public String pesquisaCompraEmLista(int itemId) {
		return retornaProdutoPorId(itemId).toString();
	}

	private boolean verificaPresencaNaListaProduto(ProdutoLista produto) {
		if (this.produtosLista.contains(produto)) {
			return true;
		}

		return false;
	}

	private boolean verificaPresencaNaListaId(int id) {

		if (getIdsProdutos().contains(id)) {
			return true;
		}

		return false;
	}

	private ProdutoLista retornaProdutoPorId(int id) {

		for (ProdutoLista produto : this.produtosLista) {

			if (id == produto.getId()) {
				return produto;
			}
		}

		throw new IllegalAccessError("Esse item nao esta presente na lista!");
	}

	public ArrayList<Integer> getIdsProdutos() {

		ArrayList<Integer> ids = new ArrayList<Integer>();

		for (ProdutoLista produto : this.produtosLista) {

			ids.add(produto.getId());
		}

		return ids;
	}

}
