package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.ItemQtd;

public class ItemQtdTest {
	
	private ItemQtd item;
	
	@Before
	public void inicializarItem() {
		item = new ItemQtd(1, "Papel Higienico limpa mais", "higiene pessoal", 10, "Unidades", "MercadaoTopaTudo", 2.75);
	}
	
	@Test
	public void testToString() {
		assertEquals(item.toString(),"1. Papel Higienico limpa mais, higiene pessoal, 10 Unidades, Preco: <MercadaoTopaTudo, R$ 2,75;>");
	}
	
	
	//Esses são testes para o construtor
	
	@Test(expected = RuntimeException.class )
	public void cadastraItemNomeVazio(){
		item = new ItemQtd(1, "", "higiene pessoal", 10, "Unidades", "MercadaoTopaTudo", 2.75);
	}
	@Test(expected = RuntimeException.class )
	public void cadastraItemNomeNull(){
		item = new ItemQtd(1, null, "higiene pessoal", 10, "Unidades", "MercadaoTopaTudo", 2.75);
	}
	
	@Test(expected = RuntimeException.class)
	public void cadastraItemCategoriaVazia(){
		item = new ItemQtd(1, "Papel Higienico limpa mais", "", 10, "Unidades", "MercadaoTopaTudo", 2.75);
	}
	
	@Test(expected = RuntimeException.class)
	public void cadastraItemCategoriaNull(){
		item = new ItemQtd(1, "Papel Higienico limpa mais", null, 10, "Unidades", "MercadaoTopaTudo", 2.75);
	}
	
	@Test(expected = RuntimeException.class)
	public void cadastraItemCategoriaInvalida(){
		item = new ItemQtd(1, "Papel Higienico limpa mais", "Higiene", 10, "Unidades", "MercadaoTopaTudo", 2.75);
	}
	
	@Test(expected = RuntimeException.class)
	public void cadastraItemQtdNegativa(){
		item = new ItemQtd(1, "Papel Higienico limpa mais", "higiene pessoal", -1, "Unidades", "MercadaoTopaTudo", 2.75);
	}
	
	@Test(expected = RuntimeException.class)
	public void cadastraItemUnidMedidaVazia(){
		item = new ItemQtd(1, "Papel Higienico limpa mais", "higiene pessoal", 10, "", "MercadaoTopaTudo", 2.75);
	}
	
	@Test(expected = RuntimeException.class)
	public void cadastraItemUniMedidaNula(){
		item = new ItemQtd(1, "Papel Higienico limpa mais", "higiene pessoal", 10, null, "MercadaoTopaTudo", 2.75);
	}
	
	@Test(expected = RuntimeException.class)
	public void cadastraItemLocalCompraVazio(){
		item = new ItemQtd(1, "Papel Higienico limpa mais", "higiene pessoal", -1, "Unidades", "", 2.75);
	}
	
	@Test(expected = RuntimeException.class)
	public void cadastraItemLocalCompraNulo(){
		item = new ItemQtd(1, "Papel Higienico limpa mais", "higiene pessoal", -1, "Unidades", null, 2.75);
	}

	@Test(expected = RuntimeException.class)
	public void cadastraItemPrecoNegativo(){
		item = new ItemQtd(1, "Papel Higienico limpa mais", "higiene pessoal", 10, "Unidades", "MercadaoTopaTudo", -10);
	}
	
	//Testes para o metodo atualizaItem()
	
	@Test(expected = RuntimeException.class)
	public void atualizaItemAtributoVazio(){
		item.atualizaItem("", "12");
	}
	
	@Test(expected = RuntimeException.class)
	public void atualizaItemAtributoNull(){
		item.atualizaItem(null, "12");
	}
	
	@Test(expected = RuntimeException.class)
	public void atualizaItemNovoValorVazio(){
		item.atualizaItem("nome", "");
	}
	
	@Test(expected = RuntimeException.class)
	public void atualizaItemNovoValorNull(){
		item.atualizaItem("nome", null);
	}
	
	@Test(expected = RuntimeException.class)
	public void atualizaItemCategoriaInvalida(){
		item.atualizaItem("categoria", "Produtos Nutella");
	}
	
}
