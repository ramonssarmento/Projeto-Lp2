package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.Item;
import classes.ItemQtd;
import classes.ItemQuilo;
import classes.ItemUnidade;
import classes.ProdutoLista;

public class ProdutoListaTest {
	private Item produto1, produto2, produto3;
	private ProdutoLista novoProdutoLista, novoProdutoLista2, novoProdutoLista3;
	
	@Before
	public void setUp() {
		produto1 = new ItemQtd(0, "Algodao", "higiene pessoal", 2, "gramas", "Seu Olavo", 500);
		produto2 = new ItemQuilo(1, "Carne de jacare", "alimento nao industrializado", 3, "Acude velho", 300);
		produto3 = new ItemUnidade(2, "Pneu de caminhao", "alimento industrializado", 1, "Cagepa", 2);
		novoProdutoLista = new ProdutoLista(produto1, 2);
		novoProdutoLista2 = new ProdutoLista(produto2, 5);
		novoProdutoLista3 = new ProdutoLista(produto3, 1);
	}
	

	@Test
	public void testSetQuantidade() {
		novoProdutoLista.setQuantidade(6);
		assertEquals(6, novoProdutoLista.getQuantidade());
	}

	@Test
	public void testGetId() {
		int identificador = 1;
		assertEquals(identificador, novoProdutoLista2.getId());
	}

	@Test
	public void testGetQuantidade() {
		int quantidade = 2;
		assertEquals(quantidade, novoProdutoLista.getQuantidade());
	}

	@Test
	public void testToString() {
		String representacaoTextual = "1 Pneu de caminhao, alimento industrializado";
		assertEquals(representacaoTextual, novoProdutoLista3.toString());
	}
	
	@Test(expected = RuntimeException.class)
	public void testQuantidade0() {
		novoProdutoLista = new ProdutoLista(produto1, 0); 
	}
	
	@Test(expected = RuntimeException.class)
	public void testQuantidadeNegativa() {
		novoProdutoLista = new ProdutoLista(produto1, -1); 
	}

}
