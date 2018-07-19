package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.ItemQtd;
import classes.ItemQuilo;
import classes.ListaDeCompras;

public class ListaDeComprasTest {

	ListaDeCompras lista;
	ItemQuilo item;
	ItemQtd item2;
	
	@Before
	public void setUp() {
		item = new ItemQuilo(10, "Carne de boi", "alimento nao industrializado", 2, "Hiper Bom Preco", 4.5);
		item2 = new ItemQtd(5, "Amendoim", "alimento industrializado", 3, "kg", "Hiper Bom Preco", 4.5);
		lista = new ListaDeCompras("Feira de sabado", "11/06/2018");
		lista.criaProdutoLista(item, 2);
		lista.criaProdutoLista(item2, 4);
	}
	
	@Test
	public void test() {
		assertEquals("4 Amendoim, alimento industrializado, 3 kg\n" + 
				"2 Carne de boi, alimento nao industrializado", lista.toString());
	}

}
