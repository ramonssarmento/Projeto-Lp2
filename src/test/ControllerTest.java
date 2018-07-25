package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import classes.ItemQtd;
import classes.ItemQuilo;
import classes.ListaDeCompras;
import controllers.Controller;

public class ControllerTest {
	private Controller controle;
	private ListaDeCompras lista;
	private ItemQuilo item;
	private ItemQtd item2;
	private ItemQtd item3;
	
	@Before
	public void setUp() {
		this.controle = new Controller();
		this.controle.adicionaItemPorQtd("Papel Higienico", "higiene pessoal", 6, "rolos", "Preco HiperBom", 7.85);
		this.controle.adicionaItemPorUnidade("Refrigerante Cola-Coca", "alimento industrializado", 1, "Atacadinho",
				3.5);
		this.controle.adicionaItemPorQuilo("Bisteca suina", "alimento nao industrializado", 0.5, "Pentagrama", 10.89);
		this.controle.adicionaListaDeCompras("Cachaca de Domingo");
		this.controle.adicionaListaDeCompras("Compras da semana");
		this.controle.adicionaCompraALista("Compras da semana", 2, 1);
		this.controle.adicionaCompraALista("Compras da semana", 3, 2);
		this.controle.adicionaCompraALista("Cachaca de Domingo", 4, 2);
	}

	@Test
	public void testIdentificadorDeItem() {
		assertEquals(4, this.controle.adicionaItemPorQtd("Pao Ingles", "alimento industrializado", 1, "duzia",
				"Padaria meu pao", 5.0));
	}

	@Test
	public void testExibeItem() {
		assertEquals("1. Papel Higienico, higiene pessoal, 6 rolos, Preco: <Preco HiperBom, R$ 7,85;>",
				this.controle.exibeItem(1));
		assertEquals("2. Refrigerante Cola-Coca, alimento industrializado, Preco: <Atacadinho, R$ 3,50;>",
				this.controle.exibeItem(2));
		assertEquals("3. Bisteca suina, alimento nao industrializado, Preco por quilo: <Pentagrama, R$ 10,89;>",
				this.controle.exibeItem(3));
	}

	@Test
	public void testAtualizaItem() {
		this.controle.atualizaItem(1, "quantidade", "2");
		this.controle.atualizaItem(1, "unidade de medida", "pacotes");
		assertEquals("1. Papel Higienico, higiene pessoal, 2 pacotes, Preco: <Preco HiperBom, R$ 7,85;>",
				this.controle.exibeItem(1));
	}

	@Test
	public void testAdicionaPrecoItem() {
		this.controle.adicionaPrecoItem(1, "Pentagrama", 6.80);
		this.controle.adicionaPrecoItem(1, "CamPharm", 7.20);
		assertEquals(
				"1. Papel Higienico, higiene pessoal, 6 rolos, Preco: <CamPharm, R$ 7,20;Pentagrama, R$ 6,80;Preco HiperBom, R$ 7,85;>",
				this.controle.exibeItem(1));

	}

	@Test
	public void testDeletaItem() {
		this.controle.deletaItem(2);
		assertEquals("1. Papel Higienico, higiene pessoal, 6 rolos, Preco: <Preco HiperBom, R$ 7,85;>",
				this.controle.exibeItem(1));
		assertEquals("3. Bisteca suina, alimento nao industrializado, Preco por quilo: <Pentagrama, R$ 10,89;>",
				this.controle.exibeItem(3));
		try {
			this.controle.exibeItem(2);
			fail("Deveria ter falhado");
		} catch (IllegalArgumentException e) {
			assertEquals("Erro na listagem de item: item nao existe.", e.getMessage());
		}

	}

	@Test
	public void testeGetItem() {
		String itemInexistente = this.controle.getItem(6);
		String primeiroItem = this.controle.getItem(1);
		assertEquals(itemInexistente, "");
		assertEquals(primeiroItem, "1. Papel Higienico, higiene pessoal, 6 rolos, Preco: <Preco HiperBom, R$ 7,85;>");
	}

	@Test
	public void testeGetItemPorCategoria() {
		String primeiroItemDaCategoria = this.controle.getItemPorCategoria("higiene pessoal", 0);
		String itemInexistente = this.controle.getItemPorCategoria("higiene pessoal", 3);
		assertEquals(primeiroItemDaCategoria,
				"1. Papel Higienico, higiene pessoal, 6 rolos, Preco: <Preco HiperBom, R$ 7,85;>");
		assertEquals(itemInexistente, "");
	}

	@Test
	public void testeGetItemPorMenorPreco() {
		String itemDeMenorPreco = this.controle.getItemPorMenorPreco(0);
		String itemInexistente = this.controle.getItemPorMenorPreco(6);
		assertEquals(itemDeMenorPreco,
				"2. Refrigerante Cola-Coca, alimento industrializado, Preco: <Atacadinho, R$ 3,50;>");
		assertEquals(itemInexistente, "");
	}

	@Test
	public void testeGetItemPorPesquisa() {
		String itemComNomePesquisado = this.controle.getItemPorPesquisa("Refrigerante Cola-Coca", 0);
		String itemInexistente = this.controle.getItemPorPesquisa("Refrigerante Cola-Coca", 8);
		assertEquals(itemComNomePesquisado,
				"2. Refrigerante Cola-Coca, alimento industrializado, Preco: <Atacadinho, R$ 3,50;>");
		assertEquals(itemInexistente, "");
	}
	@Test
	public void testeAdicionaListaDeCompras() {
		String descricaoDaLista = this.controle.adicionaListaDeCompras("Ressaca de segunda");
		assertEquals(descricaoDaLista, "Ressaca de segunda");
	}
	
	//Mudar nome
	//Testando coisas especificas
	//Eu sei que as palavras deviam ter acentos
	//mas...
	@Test
	public void testAbel() {
		assertEquals("", controle.horaAtual());
	}
}