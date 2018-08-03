package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import controllers.Controller;

public class ControllerTest {
	private Controller controle;
	private String data;
	
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
		this.data = controle.dataAtual();
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
		try {
			this.controle.exibeItem(-1);
			fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Erro na listagem de item: id invalido.", e.getMessage());
		}
	}

	@Test
	public void testAtualizaItem() {
		this.controle.atualizaItem(1, "quantidade", "2");
		this.controle.atualizaItem(1, "unidade de medida", "pacotes");
		assertEquals("1. Papel Higienico, higiene pessoal, 2 pacotes, Preco: <Preco HiperBom, R$ 7,85;>",
				this.controle.exibeItem(1));

		try {
			this.controle.atualizaItem(10, "quantidade", "2");
			fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Erro na atualizacao de item: item nao existe.", e.getMessage());
		}
	}

	@Test
	public void testAdicionaPrecoItem() {
		this.controle.adicionaPrecoItem(1, "Pentagrama", 6.80);
		this.controle.adicionaPrecoItem(1, "CamPharm", 7.20);
		assertEquals(
				"1. Papel Higienico, higiene pessoal, 6 rolos, Preco: <CamPharm, R$ 7,20;Pentagrama, R$ 6,80;Preco HiperBom, R$ 7,85;>",
				this.controle.exibeItem(1));

		try {
			this.controle.adicionaPrecoItem(-5, "Pentagrama", 6.80);
			fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Erro no cadastro de preco: id de item invalido.", e.getMessage());
		}

		try {
			this.controle.adicionaPrecoItem(10, "Pentagrama", 6.80);
			fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Erro no cadastro de preco: item nao existe.", e.getMessage());
		}

		try {
			this.controle.adicionaPrecoItem(2, "     ", 6.80);
			fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Erro no cadastro de preco: local de compra nao pode ser vazio ou nulo.", e.getMessage());
		}

		try {
			this.controle.adicionaPrecoItem(2, null, 6.80);
			fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Erro no cadastro de preco: local de compra nao pode ser vazio ou nulo.", e.getMessage());
		}

		try {
			this.controle.adicionaPrecoItem(2, "Pentagrama", -5);
			fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Erro no cadastro de preco: preco de item invalido.", e.getMessage());
		}

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
			fail("Deveria ter lancado excecao");
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
		try {
			this.controle.getItemPorCategoria("Tempo", 2);
			fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Erro na listagem de item: categoria nao existe.", e.getMessage());
		}
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
		try {
			this.controle.adicionaListaDeCompras("  ");
			fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Erro na criacao de lista de compras: descritor nao pode ser vazio ou nulo.", e.getMessage());
		}

		try {
			this.controle.adicionaListaDeCompras(null);
			fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Erro na criacao de lista de compras: descritor nao pode ser vazio ou nulo.", e.getMessage());
		}
	}

	@Test
	public void testPesquisaListaDeCompras() {
		assertEquals("Compras da semana", controle.pesquisaListaDeCompras("Compras da semana"));
	}

	@Test
	public void testFinalizarListaDeCompras() {
		controle.finalizarListaDeCompras("Compras da semana", "Preco HiperBom", 25);
	}

	@Test
	public void testPesquisaCompraEmLista() {
		assertEquals("3 Refrigerante Cola-Coca, alimento industrializado",
				controle.pesquisaCompraEmLista("Compras da semana", 2));
	}

	@Test
	public void testAtualizaCompraDeLista() {
		controle.atualizaCompraDeLista("Compras da semana", 2, "adiciona", 4);
		controle.atualizaCompraDeLista("Compras da semana", 2, "diminui", 1);
		assertEquals("6 Refrigerante Cola-Coca, alimento industrializado",
				controle.getItemLista("Compras da semana", 1));
	}

	@Test
	public void testGetItemLista() {
		assertEquals("3 Refrigerante Cola-Coca, alimento industrializado",
				controle.getItemLista("Compras da semana", 1));
	}

	@Test
	public void testDeletaCompraDeLista() {
		controle.deletaCompraDeLista("Compras da semana", 2);
	}

	@Test
	public void testImprimirListaDeCompras() {
		assertEquals(
				"2 Papel Higienico, higiene pessoal, 6 rolos\n" + "3 Refrigerante Cola-Coca, alimento industrializado",
				controle.imprimirListaDeCompras("Compras da semana"));
	}

	@Test
	public void testGetListaPorData() {
		assertEquals("Compras da semana", controle.getListaPorData(controle.dataAtual(), 1));
		assertEquals("Cachaca de Domingo\n" + "Compras da semana",
				controle.pesquisaListasDeComprasPorData(controle.dataAtual()));
	}

	@Test
	public void testBuscaListasPorItem() {
		assertEquals(this.data + " - Cachaca de Domingo\n" + this.data + " - Compras da semana",
				controle.buscaListasPorItem(2));
		assertEquals(this.data + " - Compras da semana", controle.buscaListaPorItem(2, 1));
	}

	@Test
	public void testGeraAutomaticaUltimaLista() {
		assertEquals("Lista automatica 1 " + this.data, controle.geraAutomaticaUltimaLista());
	}

	@Test
	public void testGeraAutomaticaItem() {
		assertEquals("Lista automatica 2 " + this.data, controle.geraAutomaticaItem("Refrigerante Cola-Coca"));
	}

	@Test
	public void testGeraAutomaticaItensMaisPresentes() {
		assertEquals("Lista automatica 3 " + this.data, controle.geraAutomaticaItensMaisPresentes());
	}

	@Test
	public void testSugereMelhorEstabelecimentos() {
		assertEquals("- 2 Papel Higienico, higiene pessoal, 6 rolos",
				controle.sugereMelhorEstabelecimento("Compras da semana", 1, 1));
	}
	
	@Test
	public void testSugereMelhorEstabelecimentoSemPosicao() {
		assertEquals("Atacadinho: R$ 10,50\n" + 
				"    - 3 Refrigerante Cola-Coca, alimento industrializadoPreco HiperBom: R$ 15,70\n" + 
				"    - 2 Papel Higienico, higiene pessoal, 6 rolos",
				controle.sugereMelhorEstabelecimento("Compras da semana"));
	}

}