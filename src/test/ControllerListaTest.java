package test;

import static org.junit.Assert.assertEquals;

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
}
