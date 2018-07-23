package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import classes.ItemUnidade;

public class ItemUnidadeTest {

	private ItemUnidade item;

	@Before
	public void inicializaItem() {
		item = new ItemUnidade(1, "Papel Higienico limpa mais", "higiene pessoal", 10, "MercadaoTopaTudo", 2.75);
	}

	// Testes para construtor

	@Test(expected = RuntimeException.class)
	public void testNomeVazio() {
		item = new ItemUnidade(1, "", "higiene pessoal", 10, "MercadaoTopaTudo", 2.75);
	}

	@Test(expected = RuntimeException.class)
	public void testNomeNull() {
		item = new ItemUnidade(1, null, "higiene pessoal", 10, "MercadaoTopaTudo", 2.75);
	}

	@Test(expected = RuntimeException.class)
	public void testCategoriaVazia() {
		item = new ItemUnidade(1, "detergente", "", 9, "Lipao", 1.50);
	}

	@Test(expected = RuntimeException.class)
	public void testCategoriaNull() {
		item = new ItemUnidade(1, "detergente", null, 9, "Lipao", 1.50);
	}

	@Test(expected = RuntimeException.class)
	public void testCategoriaInvalida() {
		item = new ItemUnidade(1, "detergente", "Produto de limpeza", 9, "Lipao", 1.50);
	}

	@Test(expected = RuntimeException.class)
	public void testUnidadeNegativa() {
		item = new ItemUnidade(1, "detergente", "limpeza", -1, "Lipao", 1.50);
	}

	@Test(expected = RuntimeException.class)
	public void testUnidadeIgualZero() {
		item = new ItemUnidade(1, "detergente", "limpeza", 0, "Lipao", 1.50);
	}

	@Test(expected = RuntimeException.class)
	public void testLocalVazio() {
		item = new ItemUnidade(1, "detergente", "limpeza", 1, "", 1.50);
	}

	@Test(expected = RuntimeException.class)
	public void testLocalNull() {
		item = new ItemUnidade(1, "detergente", "limpeza", 1, null, 1.50);
	}

	@Test(expected = RuntimeException.class)
	public void testPrecoNegativo() {
		item = new ItemUnidade(1, "detergente", "limpeza", 1, "Lipao", -2);
	}

	// Teste para o atualiza item

	@Test(expected = RuntimeException.class)
	public void atutalizaItemAtributoVazio() {
		item.atualizaItem("", "Po de osso");
	}

	@Test(expected = RuntimeException.class)
	public void atutalizaItemAtributoNull() {
		item.atualizaItem(null, "Po de osso");
	}

	@Test(expected = RuntimeException.class)
	public void atutalizaItemNovoValorVazio() {
		item.atualizaItem("nome", "");
	}

	@Test(expected = RuntimeException.class)
	public void atutalizaItemNovoValorNull() {
		item.atualizaItem("nome", null);
	}

	@Test(expected = RuntimeException.class)
	public void atutalizaItemCategoriaInvalida() {
		item.atualizaItem("categoria", "Produtos de higiene pessoal");
	}

	// teste equals

	@Test
	public void testEquals() {
		ItemUnidade item2 = new ItemUnidade(2, "Papel Higienico limpa mais", "higiene pessoal", 2, "MercadoTop", 2.50);
		assertEquals(true, this.item.equals(item2));
	}

	@Test
	public void testEqualsNomeDiferente() {
		ItemUnidade item2 = new ItemUnidade(2, "Papel higienico", "higiene pessoal", 2, "MercadoTop", 2.50);
		assertEquals(false, this.item.equals(item2));
	}

	@Test
	public void testEqualsCategoriaDiferente() {
		ItemUnidade item2 = new ItemUnidade(2, "Papel Higienico limpa mais", "limpeza", 2, "MercadoTop", 2.50);
		assertEquals(false, this.item.equals(item2));
	}

	// Teste para adicionar preco em item

	@Test(expected = RuntimeException.class)
	public void testAdicionaPrecoNegativo() {
		item.adicionaPrecoItem("MercadoTop", -2);
	}

	@Test(expected = RuntimeException.class)
	public void testAdicionaSuperMercadoVazio() {
		item.adicionaPrecoItem("", 2);
	}

	@Test(expected = RuntimeException.class)
	public void testAdicionaSuperMercadoNull() {
		item.adicionaPrecoItem(null, 2);
	}

	// Teste para o toString
	@Test
	public void testToString() {
		assertEquals("1. Papel Higienico limpa mais, higiene pessoal, Preco: <MercadaoTopaTudo, R$ 2,75;>",
				item.toString());
	}

}
