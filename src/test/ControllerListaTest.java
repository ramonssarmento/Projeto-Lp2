package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import classes.ItemQtd;
import classes.ItemQuilo;
import classes.ListaDeCompras;
import controllers.ControllerLista;

public class ControllerListaTest {
	private ControllerLista controle;
	private ItemQuilo item;
	private ItemQtd item2;
	private ItemQtd item3;
	private HashMap<String, ListaDeCompras> listasDeCompras;

	@Before
	public void setUP() {
		controle = new ControllerLista();
		controle.adicionaListaDeCompras("Mini Box da Ana", "14/10/2018", "02:12:12:3600");
		controle.adicionaListaDeCompras("Hiperzinho", "14/10/2018", "02:12:12:3601");

		item = new ItemQuilo(10, "Carne de boi", "alimento nao industrializado", 2, "Hiper Bom Preco", 4.5);
		item2 = new ItemQtd(5, "Amendoim", "alimento industrializado", 3, "kg", "Hiper Bom Preco", 4.5);
		item3 = new ItemQtd(4, "Ervilhas", "alimento industrializado", 300, "g", "Hiper Bom Preco", 2.0);

		controle.adicionaCompraALista("Hiperzinho", 3, item);
		controle.adicionaCompraALista("Mini Box da Ana", 2, item);
		controle.adicionaCompraALista("Mini Box da Ana", 2, item2);
		controle.adicionaCompraALista("Mini Box da Ana", 2, item3);
	}

	@Test
	public void testaCriaUltimaListaAutomatica() {
		controle.geraAutomaticaUltimaLista("15/10/2018", "08:12:12:3699");

		assertEquals("", controle.getItemLista("Lista automatica 1 15/10/2018", 1));
	}

	@Test
	public void testAdicionaListaDeCompras() {
		String descritorDaLista = controle.adicionaListaDeCompras("Mini Box da Ana", "14/10/2018", "02:12:12:3600");
		assertEquals("Mini Box da Ana", descritorDaLista);
	}
	@Test
	public void testPesquisaCompraEmLista() {
		String representacaoTextualDoItem = controle.pesquisaCompraEmLista("Mini Box da Ana", 10);
		assertEquals("2 Carne de boi, alimento nao industrializado", representacaoTextualDoItem);
	}
	@Test
	public void testGetItemLista() {
		String posicaoInexistente = controle.getItemLista("Mini Box da Ana", 10);
		String representacaoTextualDoItem = controle.getItemLista("Mini Box da Ana", 0);
		assertEquals("", posicaoInexistente);
		assertEquals("2 Amendoim, alimento industrializado, 3 kg", representacaoTextualDoItem);
	}
	@Test
	public void testImprimirListaDeCompras() {
		String representacaoTextualDaLista = controle.imprimirListaDeCompras("Mini Box da Ana");
		assertEquals("2 Amendoim, alimento industrializado, 3 kg\n2 Ervilhas, alimento industrializado, 300 g\n2 Carne de boi, alimento nao industrializado", representacaoTextualDaLista);
	}
	@Test
	public void testGetListaPorData() {
		String representacaoTextualDaLista = controle.getListaPorData("14/10/2018", 0);
		assertEquals("Hiperzinho", representacaoTextualDaLista);
	}
	
}
