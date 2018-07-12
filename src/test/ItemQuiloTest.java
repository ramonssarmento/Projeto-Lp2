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
		assertEquals("1. Peito de frango, alimento nao industrializado, Preco por quilo: <O Frangao, R$ 6,00;>", item.toString());
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
	


}
