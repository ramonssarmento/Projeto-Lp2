package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.ItemQuilo;

public class ItemQuiloTest {

	private ItemQuilo item;

	@Before
	public void inicializaItem() {
		item = new ItemQuilo(1, "Peito de frango", "alimento nao industrializado", 0.6, "O Frangao", 6.00);
	}

	@Test
	public void testToString() {
		assertEquals("1. Peito de frango, alimento nao industrializado, Preco por quilo: <O Frangao, R$ 6,00;>",
				item.toString());
	}

	@Test
	public void testEqualsItensIguais() {
		ItemQuilo item1 = new ItemQuilo(1, "pernil", "alimento nao industrializado", 0.75, "O Frangao", 6.78);
		ItemQuilo item2 = new ItemQuilo(1, "pernil", "alimento nao industrializado", 1, "Braza", 8.00);
		assertEquals(true, item1.equals(item2));
	}

	@Test
	public void testEqualsItensDiferentes() {
		ItemQuilo item1 = new ItemQuilo(1, "perna de boi", "alimento nao industrializado", 1, "O Frangao", 8.00);
		ItemQuilo item2 = new ItemQuilo(1, "pernil", "alimento nao industrializado", 1, "Braza", 8.00);
		assertEquals(false, item1.equals(item2));
	}

	// Testes para construtor
	@Test(expected = RuntimeException.class)
	public void testNomeVazio() {
		item = new ItemQuilo(1, "", "alimento nao industrializado", 0.6, "O Frangao", 6.00);
	}

	@Test(expected = RuntimeException.class)
	public void testNomeNull() {
		item = new ItemQuilo(1, null, "alimento nao industrializado", 0.6, "O Frangao", 6.00);
	}

	@Test(expected = RuntimeException.class)
	public void testCategoriaVazia() {
		item = new ItemQuilo(1, "Peito de frango", "", 0.6, "O Frangao", 6.00);
	}

	@Test(expected = RuntimeException.class)
	public void testCategoriaNull() {
		item = new ItemQuilo(1, "Peito de frango", null, 0.6, "O Frangao", 6.00);
	}

	@Test(expected = RuntimeException.class)
	public void testCategoriaInvalida() {
		item = new ItemQuilo(1, "Peito de frango", "Carnes", 0.6, "O Frangao", 6.00);
	}

	@Test(expected = RuntimeException.class)
	public void testKiloNegativo() {
		item = new ItemQuilo(1, "Peito de frango", "alimento nao industrializado", -1, "O Frangao", 6.00);
	}

	@Test(expected = RuntimeException.class)
	public void testSuperMercadoVazio() {
		item = new ItemQuilo(1, "Peito de frango", "alimento nao industrializado", 0.6, "", 6.00);
	}

	@Test(expected = RuntimeException.class)
	public void testSuperMercadoNull() {
		item = new ItemQuilo(1, "Peito de frango", "alimento nao industrializado", 0.6, null, 6.00);
	}

	@Test(expected = RuntimeException.class)
	public void testPrecoNegativo() {
		item = new ItemQuilo(1, "Peito de frango", "alimento nao industrializado", 0.6, "O Frangao", -2.00);
	}

	// Testes para metodo Atualiza
	@Test(expected = RuntimeException.class)
	public void testAtualizaAtributoVazio() {
		item.atualizaItem("", "peixe");
	}

	@Test(expected = RuntimeException.class)
	public void testAtualizaAtributoNull() {
		item.atualizaItem(null, "peixe");
	}

	@Test(expected = RuntimeException.class)
	public void testAtualizaNovoValorVazio() {
		item.atualizaItem("nome", "");
	}

	@Test(expected = RuntimeException.class)
	public void testAtualizaNovoValorNull() {
		item.atualizaItem("nome", null);
	}

	@Test(expected = RuntimeException.class)
	public void testAtualizaCategoriaValorInvalido() {
		item.atualizaItem("categoria", "xula");
	}

	@Test(expected = RuntimeException.class)
	public void testAtualizaQuiloNegativo() {
		item.atualizaItem("kg", "-0.6");
	}

	@Test(expected = RuntimeException.class)
	public void testAtualizaQuiloInvalido() {
		item.atualizaItem("kg", "Dois");
	}
	// Testes para AdicionaPrecoItem

	@Test(expected = RuntimeException.class)
	public void testadicionaPrecoItemLocalDeCompraVazio() {
		item.adicionaPrecoItem("", 6);
	}

	@Test(expected = RuntimeException.class)
	public void testadicionaPrecoItemLocalDeCompraNull() {
		item.adicionaPrecoItem(null, 6);
	}

	@Test(expected = RuntimeException.class)
	public void testadicionaPrecoItemNegativo() {
		item.adicionaPrecoItem("Mercadao", -6);
	}

}
