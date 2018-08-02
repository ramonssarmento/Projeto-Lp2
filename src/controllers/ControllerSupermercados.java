package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import classes.SuperMercado;
import classes.SuperMercadoComItens;
import interfaces.OrdenaSuperMercadoComItens;
import interfaces.SupermercadoComItensOrdenavel;

/**
 * Classe que controla e armazena os supermercados, faz ligacoes com o controller principal tambem.
 * @author Abel Antunes, Hercules Anselmo, Jose Adriao e Ramon Sarmento.
 *
 */

public class ControllerSupermercados {
	
	private HashMap<String, SuperMercado> superMercados;
	
	public ControllerSupermercados() {
		this.superMercados = new HashMap<String, SuperMercado>();
	}
	
	/**
	 * Verifica se determinado supermercado existe no sistema
	 * 
	 * @param nomeSupermercado,
	 *            nome do supermercado o qual deseja procurar @return, um boolenado
	 *            true se houver, false se não houver
	 */
	private boolean verificaPresencaDeSupermercado(String nomeSupermercado) {
		if (this.superMercados.containsKey(nomeSupermercado)) {
			return true;
		}

		return false;
	}
	
	/**
	 * Deleta item de um supermercado.
	 * A funcao principal e deletar um item, quando o mesmo e deletado do sistema.
	 * 
	 * @param id - Id do item.
	 */
	
	public void deletaItem(int id) {
		for (SuperMercado supermercado : this.superMercados.values()) {
			supermercado.deletaItem(id);
		}
	}
	
	/**
	 * Adiciona determinado produto ao supermercado
	 * 
	 * @param localDeCompra,
	 *            supermercado o qual o produto sera adicionado
	 * @param preco,
	 *            preco do produto
	 */
	public void adicionaItemNoSupermercado(String localDeCompra, double preco, int id) {
		if (verificaPresencaDeSupermercado(localDeCompra)) {
			this.superMercados.get(localDeCompra).adicionarItem(id, preco);
		}

		else {

			SuperMercado superMercado = new SuperMercado(localDeCompra);
			this.superMercados.put(localDeCompra, superMercado);
			this.superMercados.get(localDeCompra).adicionarItem(id, preco);
		}
	}
	
	/**
	 * Metodo que recebe um HashMap com ids e quantidades de itens.
	 * Percorre os supermercados cadastrados e verifica a existencia de cada item
	 * Se o item existir soma no valor de compra do supermercado o valor das quantidades desejadas
	 * do item.
	 * 
	 * Apos armazenar os supermercados que possuem os itens da lista, ordena-os pelo menor preco
	 * e depois retorna um ArrayList para o controller principal.
	 * 
	 * @param itensEQuantidades - HashMap com ids e quantidades dos itens desejados.
	 * 
	 * @return - ArrayList de supermercados com itens desejados, ordenado a partir dos valores de compras.
	 */
	
	public  ArrayList<SupermercadoComItensOrdenavel> retornaSuperMercadosComItens(HashMap<Integer, Integer> itensEQuantidades) {
		
		ArrayList<SupermercadoComItensOrdenavel> superMercadosComItens = new ArrayList<>();
		for (SuperMercado supermercado : this.superMercados.values()) {
			double valorTotal = 0;
			ArrayList<Integer> produtosNoSuperMercado = new ArrayList<>();
			for (Integer id : itensEQuantidades.keySet()) {
				
				if (supermercado.verificaExistProduto(id)) {
					produtosNoSuperMercado.add(id);
					valorTotal += supermercado.retornaValorDeItemParaLista(id, itensEQuantidades.get(id));
					
				}
			}
			
			if (produtosNoSuperMercado.size() > 0) {
				SuperMercadoComItens novoSuperMercadoComItens = new SuperMercadoComItens(supermercado.getNome(), valorTotal, produtosNoSuperMercado);
				superMercadosComItens.add(novoSuperMercadoComItens);
			}
			
		}
		
		//Significa que nao existe nenhum dos itens da lista.
		if(superMercadosComItens.size() == 0) {
			throw new IllegalArgumentException("Faltam dados para informar sobre preços em locais de compras.");
		}
		
		Collections.sort(superMercadosComItens, new OrdenaSuperMercadoComItens());
		return superMercadosComItens;
		
	}
	
	/**
	 * Metodo que retorna impresso os supermercados que possuem itens de uma lista de compras
	 * e os itens que cada um desses supermercados possui.
	 * 
	 * @param supermercadosComItens - ArrayList que vem do controller com os supermercados que possuem os itens
	 * esse ArrayList é formado por um trabalho em conjunto dos controllers de lista e supermercado ligados pelo controller geral.
	 * 
	 * @return - Saida de texto com os nomes dos supermercados e os itens que cada supermercado possui.
	 */
	
	public String sugereMelhorEstabelecimento(ArrayList<SupermercadoComItensOrdenavel> supermercadosComItens) {
		
		String saida = "";
		for (SupermercadoComItensOrdenavel supermercado : supermercadosComItens) {
			saida += supermercado.toString();
		}
		
		return saida;
	}
	
	/**
	 * Metodo que retorna a partir dos supermercados que possui itens de uma lista, um supermercado e um item
	 * em posicoes especificas, passadas como parametros.
	 * 
	 * Caso a posicao do item seja 0, e tratado na classe superMercadoComItens para retornar o nome do supermercado em questao.
	 * 
	 * 
	 * @param supermercadosComItens - ArrayList que vem do controller com os supermercados que possuem os itens
	 * esse ArrayList é formado por um trabalho em conjunto dos controllers de lista e supermercado ligados pelo controller geral.
	 * 
	 * 		- posicaoSupermercado - Posicao do supermercado desejado.
	 * 		- posicaoItem - Posicao do item desejado.
	 * 
	 * @return - Saida de texto com os nomes dos supermercados e os itens que cada supermercado possui.
	 */
	
	public String sugereMelhorEstabelecimento(ArrayList<SupermercadoComItensOrdenavel> supermercadosComItens, int posicaoSupermercado, int posicaoItem) {
		if (posicaoSupermercado >= 0 && posicaoSupermercado < supermercadosComItens.size()) {
			SupermercadoComItensOrdenavel supermercado = supermercadosComItens.get(posicaoSupermercado);
		
			return supermercado.getItemOrdenado(posicaoItem);
		}
		
		return "";
	}

}
