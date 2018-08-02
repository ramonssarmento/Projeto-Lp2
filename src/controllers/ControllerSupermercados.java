package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import classes.SuperMercado;
import classes.SuperMercadoComItens;
import interfaces.OrdenaSuperMercadoComItens;
import interfaces.SupermercadoComItensOrdenavel;

public class ControllerSupermercados {
	
	private HashMap<String, SuperMercado> superMercados;
	
	public ControllerSupermercados() {
		this.superMercados = new HashMap<String, SuperMercado>();
	}
	
	/**
	 * Verifica se determinado supermercado contém tal produto
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
		
		Collections.sort(superMercadosComItens, new OrdenaSuperMercadoComItens());
		return superMercadosComItens;
		
	}
	
	public String sugereMelhorEstabelecimento(ArrayList<SupermercadoComItensOrdenavel> supermercadosComItens) {
		
		String saida = "";
		for (SupermercadoComItensOrdenavel supermercado : supermercadosComItens) {
			saida += supermercado.toString();
		}
		
		return saida;
	}
	
	public String sugereMelhorEstabelecimento(ArrayList<SupermercadoComItensOrdenavel> supermercadosComItens, int posicaoSupermercado, int posicaoItem) {
		if (posicaoSupermercado >= 0 && posicaoSupermercado < supermercadosComItens.size()) {
			SupermercadoComItensOrdenavel supermercado = supermercadosComItens.get(posicaoSupermercado);
		
			return supermercado.getItemOrdenado(posicaoItem);
		}
		
		return "";
	}

}
