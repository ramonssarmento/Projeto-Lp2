package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.ItemQtd;
import classes.ItemQuilo;
import classes.ItemUnidade;
import classes.ListaDeCompras;

public class ListaDeComprasTest {

	private ListaDeCompras lista;
	private ItemQuilo item;
	private ItemQtd item2;
	private ItemQtd item3;
	private ItemQtd item4;
	private ItemQuilo item5;
	private ItemQuilo item6;
	private ItemUnidade item7;
	private ItemUnidade item8;
	private ItemUnidade item9;
	private ItemUnidade item10;
	
	@Before
	public void setUp() {
		
		// Criacao de itens.
		item = new ItemQuilo(10, "Carne de boi", "alimento nao industrializado", 2, "Hiper Bom Preco", 4.5);
		item2 = new ItemQtd(5, "Amendoim", "alimento industrializado", 3, "kg", "Hiper Bom Preco", 4.5);
		item3 = new ItemQtd(4, "Ervilhas", "alimento industrializado", 300, "g", "Hiper Bom Preco", 2.0);
		item4 = new ItemQtd(15, "Picles", "alimento industrializado", 500, "g", "Babilonia", 5);
		item5 = new ItemQuilo(12, "Carne de porco", "alimento nao industrializado", 2, "Atacadinho", 20.6);
		item6 = new ItemQuilo(19, "Carne de frango", "alimento nao industrializado", 1, "Atacadinho", 28.6);
		item7 = new ItemUnidade(14, "Papel higienico", "higiene pessoal", 2, "Atacadinho", 6);
		item8 = new ItemUnidade(17, "Creme dental", "higiene pessoal", 1, "Atacadinho", 1.20);
		item9 = new ItemUnidade(20, "Detergente", "limpeza", 1, "Atacadinho", 4.5);
		item10 = new ItemUnidade(3, "Esponja", "limpeza", 1, "Carrefulto", 2.0);
		
		// Criacao da lista.
		lista = new ListaDeCompras("Feira de sabado", "11/06/2018");
		lista.adicionaProdutoNaLista(item, 4);
		lista.adicionaProdutoNaLista(item2, 2);
		lista.adicionaProdutoNaLista(item3, 3);
		lista.adicionaProdutoNaLista(item4, 2);
		lista.adicionaProdutoNaLista(item5, 2);
		lista.adicionaProdutoNaLista(item6, 2);
		lista.adicionaProdutoNaLista(item7, 6);
		lista.adicionaProdutoNaLista(item8, 1);
		lista.adicionaProdutoNaLista(item9, 7);
		lista.adicionaProdutoNaLista(item10, 4);
	}
	
	@Test
	public void testToString() {
		assertEquals("1 Creme dental, higiene pessoal\n" + 
				"6 Papel higienico, higiene pessoal\n" + 
				"7 Detergente, limpeza\n" + 
				"4 Esponja, limpeza\n" + 
				"2 Amendoim, alimento industrializado, 3 kg\n" + 
				"3 Ervilhas, alimento industrializado, 300 g\n" + 
				"2 Picles, alimento industrializado, 500 g\n" + 
				"4 Carne de boi, alimento nao industrializado\n" + 
				"2 Carne de frango, alimento nao industrializado\n" + 
				"2 Carne de porco, alimento nao industrializado", lista.toString());
	}

}
