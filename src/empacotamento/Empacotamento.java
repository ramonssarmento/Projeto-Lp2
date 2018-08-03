package empacotamento;

import java.io.
BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * Codigo desenvolvido para aulas de P2-computacao@ufcg
 * Usado como prova de conceito, podendo ser melhorado.
 * Assuntos: Arquivos.
 * 18/07/2018
 * @author Lívia
 *
 */
public class Empacotamento {
	
	public static void salvarObjeto(Object objeto, String nomeArquivo) throws FileNotFoundException, IOException{
		ObjectOutputStream arqObjectos = null;
		
		try{			
			arqObjectos = new ObjectOutputStream(new FileOutputStream(nomeArquivo));
			arqObjectos.writeObject(objeto);
		}finally{
			if(arqObjectos != null)
				arqObjectos.close();
		}
	}
	
	public static Object lerObjetos(String nomeArquivo) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream arqObjectos = null;
		
		try{
			arqObjectos = new ObjectInputStream(new FileInputStream(nomeArquivo));
			return arqObjectos.readObject();
		}finally{
			if(arqObjectos != null)
				arqObjectos.close();
		}
	}
}
