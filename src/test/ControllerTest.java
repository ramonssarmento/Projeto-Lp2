package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import classes.Controller;

public class ControllerTest {
	private Controller controle;

	@Before
	public void setUp() {
		this.controle = new Controller();
		this.controle.adicionaItemPorQtd("Papel Higienico", "higiene pessoal", 6, "rolos", "Preco HiperBom", 7.85);
		this.controle.adicionaItemPorUnidade("Refrigerante Cola-Coca", "alimento industrializado", 1, "Atacadinho",
				3.5);
		this.controle.adicionaItemPorQuilo("Bisteca suina", "alimento nao industrializado", 0.5, "Pentagrama", 10.89);
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

}
