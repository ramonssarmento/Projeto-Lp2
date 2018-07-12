package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.ItemQuilo;

public class ItemQuiloTest {
	
	private ItemQuilo item;
	
	@Before 
	public void inicializaItem() {
		item = new ItemQuilo(1, "Peito de frango", "alimentos nao industrializados", 0.6, "O Frangao", 6.00);
	}
	
	@Test
	public void testToString() {
		assertEquals("1. Peito de frango, alimentos nao industrializados, Preco por quilo: <O Frangao, R$ 6,00;>", item.toString());
	}

}
