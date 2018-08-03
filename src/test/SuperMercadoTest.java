package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import classes.ItemQtd;
import classes.ItemQuilo;
import classes.ItemUnidade;
import classes.SuperMercado;

public class SuperMercadoTest {

	private SuperMercado mercado;
	private ItemQtd item, item2, item3;
	private ItemUnidade item4,item5,item6;
	private ItemQuilo item7,item8,item9;
	

	@Before
	public void start() {
		mercado = new SuperMercado("Hiper");
		item = new ItemQtd(1, "Caixa de ovos", "alimento nao industrializado", 12, "unidade", "Hiper", 2.00);
		item2 = new ItemQtd(102, "Cacho de banana", "alimento nao industrializado", 6, "unidade", "Hiper", 1.05);
		item3 = new ItemQtd(542, "Sabao Homo", "limpeza", 12, "pacote", "Hiper", 4.78);
		
		item4 = new ItemUnidade(2, "Abacaxi", "alimento nao industrializado", 1, "Hiper", 1.50);
		item5 = new ItemUnidade(658, "Leite em po", "alimento industrializado", 1, "Hiper", 4.50);
		item6 = new ItemUnidade(879, "Caixa de chocolate Nestler", "alimento industrializado", 1, "Hiper", 9.99);
	
		item7 = new ItemQuilo(82, "Carne de Sol", "alimento nao industrializado", 2, "Hiper", 5.75);
		item8 = new ItemQuilo(344, "Carne de porco", "alimento nao industrializado", 0.58, "Hiper", 3.00);
		item9 = new ItemQuilo(003, "Linguica de galinha caipira", "alimento nao industrializado", 8, "Hiper", 25.00);
		
		mercado.adicionarItem(item.getId(), 2.00);
		mercado.adicionarItem(item2.getId(), 1.05);
		mercado.adicionarItem(item3.getId(), 4.78);
		mercado.adicionarItem(item4.getId(), 1.50);
		mercado.adicionarItem(item5.getId(), 4.50);
		mercado.adicionarItem(item6.getId(), 9.99);
		mercado.adicionarItem(item7.getId(), 5.75);
		mercado.adicionarItem(item8.getId(), 3.00);
		mercado.adicionarItem(item9.getId(), 25.00);
		
	}
	/**
	 * Testando deletar um item do super mercado
	 * 
	 */
	@Test	
	public void testDeletarItem() {
		mercado.deletaItem(1);
		
		assertEquals(false, mercado.verificaExistProduto(1));
	}
	
	/**
	 * Testando precos para os produtos recebidos
	 */
	@Test
	public void testPrecoProdutos() {
		ArrayList<Integer> ids = new ArrayList<>();
		ids.add(2);
		ids.add(344);
		ids.add(003);
		
		assertEquals(29.50 ,mercado.calculaPrecoCompras(ids),0.1);
	}
	
	/**
	 * testando metodo que retorna o Preco do item vezes a quantidade dele em uma lista
	 */
	
	@Test
	public void testRetornoValorDoItemParaLista() {
		assertEquals(50, mercado.retornaValorDeItemParaLista(003, 2),0.0);
	}
	
	
}
