package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import classes.SuperMercadoComItens;

public class SuperMercadoComItensTest {
	
	private SuperMercadoComItens supermercado;
	ArrayList<String> saida;
	
	@Before
	public void setUp() {
		ArrayList<Integer> produtosId = new ArrayList();
		produtosId.add(1);
		produtosId.add(2);
		produtosId.add(3);
		
		saida = new ArrayList();
		saida.add("- 1 Algodão Clemer, higiene pessoal, 300 gramas");
		saida.add("- 2 Sabão líquido Pomo, limpeza, 1 litro");
		
		this.supermercado = new SuperMercadoComItens("Atacadinho", 3000, produtosId);
		supermercado.setSaidaTexto(saida);
	}
	
	@Test
	public void testGetNome() {
		assertEquals("Atacadinho: R$ 3000,00", supermercado.getNome());
	}
	
	@Test
	public void testGetValorTotal() {
		assertEquals(3000, supermercado.getValorTotal(), 0);
	}
	
	@Test
	public void testGetItemOrdenado() {
		assertEquals("Atacadinho: R$ 3000,00" , supermercado.getItemOrdenado(0));
		assertEquals("- 1 Algodão Clemer, higiene pessoal, 300 gramas" , supermercado.getItemOrdenado(1));
		assertEquals("- 2 Sabão líquido Pomo, limpeza, 1 litro" , supermercado.getItemOrdenado(2));
	}
	
	@Test
	public void testToString() {
		assertEquals("Atacadinho: R$ 3000,00\n" + 
				"    - 1 Algodão Clemer, higiene pessoal, 300 gramas\n" + 
				"    - 2 Sabão líquido Pomo, limpeza, 1 litro", supermercado.toString());
	}

}
