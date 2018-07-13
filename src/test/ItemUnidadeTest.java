package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.ItemQtd;
import classes.ItemUnidade;

public class ItemUnidadeTest {
	
	private ItemUnidade item;
	
	
	@Before
	public void inicializaItem() {
		item = new ItemUnidade(1, "Papel Higienico limpa mais", "higiene pessoal", 1, "MercadaoTopaTudo", 2.75);
	}

}
